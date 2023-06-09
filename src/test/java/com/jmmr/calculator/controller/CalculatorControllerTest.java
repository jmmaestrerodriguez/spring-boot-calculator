package com.jmmr.calculator.controller;

import com.jmmr.calculator.controller.impl.CalculatorController;
import com.jmmr.calculator.dto.OperationDTO;
import com.jmmr.calculator.dto.OperationResultDTO;
import com.jmmr.calculator.enums.OperationType;
import com.jmmr.calculator.log.Logger;
import com.jmmr.calculator.mapper.OperationMapper;
import com.jmmr.calculator.model.Operation;
import com.jmmr.calculator.service.CalculatorService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import static org.mockito.Mockito.when;

@DisplayName("Calculator Controller Tests")
public class CalculatorControllerTest {

    @Mock
    private CalculatorService calculatorService;

    @Mock
    private Logger logger;

    @Mock
    private OperationMapper operationMapper;

    @InjectMocks
    private CalculatorController calculatorController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Calculate Addition Operation")
    void testCalculateAdditionOperation() {
        // Given
        OperationDTO operationDTO = new OperationDTO(BigDecimal.valueOf(5), BigDecimal.valueOf(10), OperationType.ADDITION);
        Operation operation = operationMapper.toOperation(operationDTO);
        BigDecimal expectedResult = BigDecimal.valueOf(15);
        when(calculatorService.performOperation(operation)).thenReturn(expectedResult);

        // When
        ResponseEntity<OperationResultDTO> result = calculatorController.calculate(operationDTO);

        // Then
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    @DisplayName("Calculate Subtraction Operation")
    void testCalculateSubtractionOperation() {
        // Given
        OperationDTO operationDTO = new OperationDTO(BigDecimal.valueOf(10), BigDecimal.valueOf(5), OperationType.SUBTRACTION);
        Operation operation = operationMapper.toOperation(operationDTO);
        BigDecimal expectedResult = BigDecimal.valueOf(5);
        when(calculatorService.performOperation(operation)).thenReturn(expectedResult);

        // When
        ResponseEntity<OperationResultDTO> result = calculatorController.calculate(operationDTO);

        // Then
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}

