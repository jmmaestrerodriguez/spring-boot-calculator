package com.jmmr.calculator.controller.impl;

import com.jmmr.calculator.controller.ICalculatorController;
import com.jmmr.calculator.dto.OperationDTO;
import com.jmmr.calculator.dto.OperationResultDTO;
import com.jmmr.calculator.mapper.OperationMapper;
import com.jmmr.calculator.model.Operation;
import com.jmmr.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;


@RestController
public class CalculatorController implements ICalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private OperationMapper operationMapper;

    @Override
    public ResponseEntity<OperationResultDTO> calculate(OperationDTO operationDTO) {
        Operation operation = operationMapper.toOperation(operationDTO);
        BigDecimal result = this.calculatorService.performOperation(operation);
        return new ResponseEntity<>(new OperationResultDTO(result), HttpStatus.OK);
    }
}
