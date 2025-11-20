package com.toolschallenge.domain;

import com.toolschallenge.domain.enums.StatusTransacao;
import jakarta.persistence.*;
import lombok.Data;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Data
@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numeroCartao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTransacao status;

    @Embedded
    private Descricao descricao;

    @Embedded
    private FormaPagamento formaPagamento;

    @Column(nullable = false)
    private boolean estornado = false;

    @PrePersist
    public void prePersist() {
        this.descricao.setDataHora(LocalDateTime.now());
        SecureRandom RANDOM = new SecureRandom();
        this.descricao.setNsu(String.format("%010d", RANDOM.nextLong(1_000_000_0000L)));
        this.descricao.setCodigoAutorizacao(String.format("%06d", RANDOM.nextInt(1_000_000)));
    }
}
