package com.jmmr.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
public class OperationResultDTO {

    private BigDecimal result;

}
