package com.jmmr.calculator.exception;

import com.jmmr.calculator.controller.impl.CalculatorController;
import com.jmmr.calculator.log.Logger;
import com.jmmr.calculator.mapper.OperationMapper;
import com.jmmr.calculator.service.CalculatorService;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.print.attribute.standard.Media;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CalculatorController.class)
public class CalculatorExceptionHandlerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CalculatorService calculatorService;

    @Mock
    private Logger logger;

    @MockBean
    private OperationMapper operationMapper;

    @InjectMocks
    private CalculatorController calculatorController;


    @Test
    public void handleHttpMessageNotReadableTest() throws Exception{
        mvc.perform(post("/calculator/operation")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.description").value(Matchers.containsString("Required request body is missing")))
                .andExpect(jsonPath("$.errors").value("Bad Request"));
    }

    @Test
    public void HttpMediaTypeNotSupportedExceptionTest() throws Exception{
        mvc.perform(post("/calculator/operation")
                        .contentType(MediaType.APPLICATION_ATOM_XML))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(jsonPath("$.status").value("UNSUPPORTED_MEDIA_TYPE"))
                .andExpect(jsonPath("$.description").value(Matchers.containsString("not supported")))
                .andExpect(jsonPath("$.errors").exists());
    }

    @Test
    public void handleNoHandlerFoundExceptionTest() throws Exception {
        mvc.perform(post("/calculator/operation/foo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.description").value(Matchers.containsString("No handler found for")))
                .andExpect(jsonPath("$.errors").exists());
    }

    @Test
    public void handleMethodArgumentNotValidTest() throws Exception {
        String jsonRequest = "{\"number1\":\"\",\"operator\":\"add\"}";

        mvc.perform(post("/calculator/operation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.description").value(Matchers.containsString("Validation failed for argument")))
                .andExpect(jsonPath("$.errors").exists());
    }

    @Test
    public void handleHttpRequestMethodNotSupportedTest() throws Exception {
        mvc.perform(get("/calculator/operation")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(jsonPath("$.description").value(Matchers.containsString("Request method")))
                .andExpect(jsonPath("$.description").value(Matchers.containsString("not supported")))
                .andExpect(jsonPath("$.errors[0]").value(Matchers.containsString("method is not supported for this request. Supported methods are")));
    }
}
