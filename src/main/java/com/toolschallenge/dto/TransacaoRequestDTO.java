package com.toolschallenge.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransacaoRequestDTO {

    @NotBlank
    private String cartao;

    @NotNull
    @Valid
    private FormaPagamentoDTO formaPagamento;

    @NotNull
    @Valid
    private DescricaoDTO descricao;
}
