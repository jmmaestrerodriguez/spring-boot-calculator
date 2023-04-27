package com.jmmr.calculator.controller;

import com.jmmr.calculator.dto.OperationDTO;
import com.jmmr.calculator.dto.OperationResultDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController("/calculator")
public interface ICalculatorController {
    @ApiOperation(value = "Calculates the result of an operation.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request made successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/operation")
    ResponseEntity<OperationResultDTO> calculate(
            @RequestBody OperationDTO operationDTO);

}
