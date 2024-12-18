package ru.example.adder.service;

import org.springframework.stereotype.Service;
import ru.example.adder.dto.OperationDto;
import ru.example.adder.entity.OperationEntity;

@Service
public class OperationMapper {
    public OperationDto toOperationDto(OperationEntity operation) {
        return OperationDto.builder()
                .id(operation.getId())
                .firstArgument(operation.getFirstArgument())
                .secondArgument(operation.getSecondArgument())
                .result(operation.getResult())
                .calculatedAt(operation.getCalculatedAt())
                .build();
    }

    public OperationEntity toOperationEntity(OperationDto operationDto) {
        OperationEntity operationEntity = new OperationEntity();
        operationEntity.setId(operationDto.getId());
        operationEntity.setFirstArgument(operationDto.getFirstArgument());
        operationEntity.setSecondArgument(operationDto.getSecondArgument());
        operationEntity.setResult(operationDto.getResult());
        operationEntity.setCalculatedAt(operationDto.getCalculatedAt());
        return operationEntity;
    }
}
