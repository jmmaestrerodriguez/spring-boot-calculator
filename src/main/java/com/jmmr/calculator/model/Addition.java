package com.jmmr.calculator.model;

import com.jmmr.calculator.enums.OperationType;

import java.math.BigDecimal;


public class Addition extends Operation {

    public Addition(BigDecimal firstOperand, BigDecimal secondOperand) {
        super(firstOperand, secondOperand, OperationType.ADDITION);
    }

    @Override
    public BigDecimal calculate(){
        return firstOperand.add(secondOperand);
    }
}
