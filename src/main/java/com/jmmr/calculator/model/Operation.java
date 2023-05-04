package com.jmmr.calculator.model;

import com.jmmr.calculator.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Getter
@AllArgsConstructor
public abstract class Operation implements IOperation {

    @NotNull(message = "First operand must not be null")
    BigDecimal firstOperand;
    @NotNull(message = "Second operand must not be null")
    BigDecimal secondOperand;
    @NotBlank(message="Operation type must not be blank")
    OperationType operationType;

}
