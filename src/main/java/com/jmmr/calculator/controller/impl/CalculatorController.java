package com.jmmr.calculator.controller.impl;

import com.jmmr.calculator.controller.ICalculatorController;
import com.jmmr.calculator.dto.OperationDTO;
import com.jmmr.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CalculatorController implements ICalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @Override
    public ResponseEntity<OperationDTO> calculate(OperationDTO operationDTO) {
        OperationDTO result = this.calculatorService.performOperation(operationDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
