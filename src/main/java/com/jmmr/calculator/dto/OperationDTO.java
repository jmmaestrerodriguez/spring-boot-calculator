package com.jmmr.calculator.dto;

import com.jmmr.calculator.enums.OperationType;
import com.jmmr.calculator.model.Addition;
import com.jmmr.calculator.model.Operation;
import com.jmmr.calculator.model.Subtraction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

    public OperationDTO(BigDecimal firstOperand, BigDecimal secondOperand, OperationType operationType) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.operationType = operationType;
    }

    public Operation toOperation() {
        if (operationType == null) {
            throw new UnsupportedOperationException("You must specify a valid operation type");
        }
        switch (operationType) {
            case ADDITION:
                return new Addition(firstOperand, secondOperand);
            case SUBTRACTION:
                return new Subtraction(firstOperand, secondOperand);
            default:
                throw new UnsupportedOperationException("Unsupported operation type: " + operationType);
        }
    }

}
