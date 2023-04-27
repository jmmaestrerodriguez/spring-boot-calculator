package com.jmmr.calculator.service;

import com.jmmr.calculator.dto.OperationDTO;
import com.jmmr.calculator.model.Operation;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;


@Service
public class CalculatorService {

    public OperationDTO performOperation(OperationDTO operationDTO) {
        Operation operation = operationDTO.toOperation();
        BigDecimal result = operation.calculate();
        operationDTO.setResult(result);
        return operationDTO;
    }

}