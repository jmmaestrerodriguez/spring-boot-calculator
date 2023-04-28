package com.jmmr.calculator.controller;

import com.jmmr.calculator.controller.impl.CalculatorController;
import com.jmmr.calculator.dto.OperationDTO;
import com.jmmr.calculator.dto.OperationResultDTO;
import com.jmmr.calculator.enums.OperationType;
import com.jmmr.calculator.log.Logger;
import com.jmmr.calculator.model.Operation;
import com.jmmr.calculator.service.CalculatorService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Calculator Controller Tests")
public class CalculatorControllerTest {

    @Mock
    private CalculatorService calculatorService;

    @Mock
    private Logger logger;

    @InjectMocks
    private CalculatorController calculatorController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Calculate Addition Operation")
    void testCalculateAdditionOperation() {
        // Arrange
        OperationDTO operationDTO = new OperationDTO(BigDecimal.valueOf(5), BigDecimal.valueOf(10), OperationType.ADDITION);
        Operation operation = operationDTO.toOperation();
        BigDecimal expectedResult = BigDecimal.valueOf(15);
        when(calculatorService.performOperation(operation)).thenReturn(expectedResult);

        // Act
        ResponseEntity<OperationResultDTO> result = calculatorController.calculate(operationDTO);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    @DisplayName("Calculate Subtraction Operation")
    void testCalculateSubtractionOperation() {
        // Arrange
        OperationDTO operationDTO = new OperationDTO(BigDecimal.valueOf(10), BigDecimal.valueOf(5), OperationType.SUBTRACTION);
        Operation operation = operationDTO.toOperation();
        BigDecimal expectedResult = BigDecimal.valueOf(5);
        when(calculatorService.performOperation(operation)).thenReturn(expectedResult);

        // Act
        ResponseEntity<OperationResultDTO> result = calculatorController.calculate(operationDTO);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    @DisplayName("Calculate Unknown Operation Type")
    void testCalculateUnknownOperationType() {
        // Arrange
        OperationDTO operationDTO = new OperationDTO(BigDecimal.valueOf(10), BigDecimal.valueOf(5), null);

        // Act and Assert
        UnsupportedOperationException exception = Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            calculatorController.calculate(operationDTO);
        });
    }

}

