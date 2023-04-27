package com.jmmr.calculator.model;

import java.math.BigDecimal;


public class Addition extends Operation {

    public Addition(BigDecimal firstOperand, BigDecimal secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public BigDecimal calculate(){
        return firstOperand.add(secondOperand);
    }
}
