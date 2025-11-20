package com.toolschallenge.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.toolschallenge.domain.enums.StatusTransacao;

import lombok.Data;

@Data
public class DescricaoDTO {

    private BigDecimal valor;
    private LocalDateTime dataHora;
    private String estabelecimento;
    private String nsu;
    private String codigoAutorizacao;
    private StatusTransacao status;    
}
