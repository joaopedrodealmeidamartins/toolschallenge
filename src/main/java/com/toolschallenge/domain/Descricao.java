package com.toolschallenge.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Embeddable
public class Descricao {

    @NotNull
    private BigDecimal valor;

    @NotNull
    private LocalDateTime dataHora;

    @NotBlank
    private String estabelecimento;

    @NotBlank
    private String nsu;

    @NotBlank
    private String codigoAutorizacao;
}
