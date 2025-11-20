package com.toolschallenge.domain;

import com.toolschallenge.domain.enums.TipoPagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Embeddable
public class FormaPagamento {

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipo;

    @Min(1)
    private Integer parcelas;
}
