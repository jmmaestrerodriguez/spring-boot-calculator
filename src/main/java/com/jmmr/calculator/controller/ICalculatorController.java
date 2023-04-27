package com.jmmr.calculator.controller;

import com.jmmr.calculator.dto.OperationDTO;
import com.jmmr.calculator.dto.OperationResultDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/calculator")
public interface ICalculatorController {

    @RequestMapping("/operation")
    ResponseEntity<OperationResultDTO> calculate(
            @RequestBody OperationDTO operationDTO);
}
