package com.jmmr.calculator.service;

import com.jmmr.calculator.log.Logger;
import com.jmmr.calculator.model.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;


@Service
public class CalculatorService {

    @Autowired
    private Logger logger;

    public BigDecimal performOperation(Operation operation) {
        this.logger.log(String.format("[%s] Performing operation between operands %f and %f", operation.getOperationType(), operation.getFirstOperand(), operation.getSecondOperand()));
        return operation.calculate();
    }

}