package com.jmmr.calculator.dto;

import com.jmmr.calculator.enums.OperationType;
import com.jmmr.calculator.model.Addition;
import com.jmmr.calculator.model.Operation;
import com.jmmr.calculator.model.Subtraction;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Getter
@Setter
public class OperationDTO {

    @NotNull(message = "First operand must not be null")
    private BigDecimal firstOperand;
    @NotNull(message = "Second operand must not be null")
    private BigDecimal secondOperand;
    @NotNull(message = "Operation type must not be null")
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
