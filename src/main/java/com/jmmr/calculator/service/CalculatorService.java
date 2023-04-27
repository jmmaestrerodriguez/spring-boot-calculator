package com.jmmr.calculator.service;

import com.jmmr.calculator.dto.OperationDTO;
import com.jmmr.calculator.model.Operation;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;


@Service
public class CalculatorService {

    public BigDecimal performOperation(Operation operation) {
        return operation.calculate();
    }

}