package com.jmmr.calculator.model;

import lombok.Getter;
import java.math.BigDecimal;


@Getter
public abstract class Operation implements IOperation {

    BigDecimal firstOperand;
    BigDecimal secondOperand;

    public Operation(BigDecimal firstOperand, BigDecimal secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

}
