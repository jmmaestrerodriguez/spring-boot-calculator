package com.jmmr.calculator.service;

import com.jmmr.calculator.log.Logger;
import com.jmmr.calculator.model.Addition;
import com.jmmr.calculator.model.Operation;
import com.jmmr.calculator.model.Subtraction;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;


@DisplayName("Calculator Service Tests")
public class CalculatorServiceTest {

    @Mock
    private Logger logger;

    @InjectMocks
    private CalculatorService calculatorService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Perform Addition operation")
    public void testPerformOperationAddition() {
        // Given
        Operation operation = new Addition(BigDecimal.valueOf(5), BigDecimal.valueOf(10));
        BigDecimal expectedResult = BigDecimal.valueOf(15);

        // When
        BigDecimal result = calculatorService.performOperation(operation);

        // Then
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Perform Subtraction operation")
    public void testPerformOperationSubtraction() {
        // Given
        Operation operation = new Subtraction(BigDecimal.valueOf(10), BigDecimal.valueOf(5));
        BigDecimal expectedResult = BigDecimal.valueOf(5);

        // When
        BigDecimal result = calculatorService.performOperation(operation);

        // Then
        Assertions.assertEquals(expectedResult, result);
    }
}

