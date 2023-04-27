package com.jmmr.calculator.dto;

import com.jmmr.calculator.enums.OperationType;
import com.jmmr.calculator.model.Addition;
import com.jmmr.calculator.model.Operation;
import com.jmmr.calculator.model.Subtraction;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;


@Getter
@Setter
public class OperationDTO {

    private BigDecimal firstOperand;
    private BigDecimal secondOperand;
    private OperationType operationType;

    public Operation toOperation(){
        switch (operationType){
            case ADDITION:
                return new Addition(firstOperand, secondOperand);
            case SUBTRACTION:
                return new Subtraction(firstOperand, secondOperand);
        }
        return null;
    }

}
