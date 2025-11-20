package com.toolschallenge.dto;

import lombok.Data;

@Data
public class TransacaoResponseDTO {

    private Long id;
    private String cartao;
    private DescricaoDTO descricao;
    private FormaPagamentoDTO formaPagamento;
}
