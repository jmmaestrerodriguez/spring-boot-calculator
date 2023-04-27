package com.jmmr.calculator.model;

import com.jmmr.calculator.enums.OperationType;
import lombok.Getter;
import java.math.BigDecimal;


@Getter
public abstract class Operation implements IOperation {

    BigDecimal firstOperand;
    BigDecimal secondOperand;
    OperationType operationType;

    public Operation(BigDecimal firstOperand, BigDecimal secondOperand, OperationType operationType) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.operationType = operationType;
    }

}
