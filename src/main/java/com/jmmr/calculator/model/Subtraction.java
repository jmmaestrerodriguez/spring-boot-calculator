package com.jmmr.calculator.model;

import java.math.BigDecimal;


public class Subtraction extends Operation {

    public Subtraction(BigDecimal firstOperand, BigDecimal secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public BigDecimal calculate() {
        return firstOperand.subtract(secondOperand);
    }
}
