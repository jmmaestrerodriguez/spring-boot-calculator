package com.jmmr.calculator.model;

import com.jmmr.calculator.enums.OperationType;

import java.math.BigDecimal;


public class Subtraction extends Operation {

    public Subtraction(BigDecimal firstOperand, BigDecimal secondOperand) {
        super(firstOperand, secondOperand, OperationType.SUBTRACTION);
    }

    @Override
    public BigDecimal calculate() {
        return firstOperand.subtract(secondOperand);
    }
}
