package com.toolschallenge.dto;

import com.toolschallenge.domain.enums.TipoPagamento;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FormaPagamentoDTO {

    @NotNull
    private TipoPagamento tipo;

    @Min(1)
    private Integer parcelas;
}
