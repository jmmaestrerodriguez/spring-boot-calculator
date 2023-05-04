package com.jmmr.calculator.mapper;

import com.jmmr.calculator.dto.OperationDTO;
import com.jmmr.calculator.model.Addition;
import com.jmmr.calculator.model.Operation;
import com.jmmr.calculator.model.Subtraction;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface OperationMapper {


    Operation toOperation(OperationDTO operationDTO);

    @ObjectFactory
    default Operation createOperation(OperationDTO operationDTO) {
        if (operationDTO.getOperationType() == null) {
            throw new UnsupportedOperationException("You must specify a valid operation type");
        }
        switch (operationDTO.getOperationType()) {
            case ADDITION:
                return new Addition(operationDTO.getFirstOperand(), operationDTO.getSecondOperand());
            case SUBTRACTION:
                return new Subtraction(operationDTO.getFirstOperand(), operationDTO.getSecondOperand());
            default:
                throw new UnsupportedOperationException("Unsupported operation type: " + operationDTO.getOperationType());
        }
    }


}
