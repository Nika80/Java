package ru.example.subtractor.service;

import ru.example.subtractor.dto.OperationDto;
import ru.example.subtractor.entity.OperationEntity;
import ru.example.subtractor.repository.OperationsRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@org.springframework.stereotype.Service
public class Service {
    private final OperationsRepository operationsRepository;
    private final OperationMapper operationMapper;

    public Service(OperationsRepository operationsRepository, OperationMapper operationMapper) {
        this.operationsRepository = operationsRepository;
        this.operationMapper = operationMapper;
    }

    public OperationDto calculate(Double a, Double b) {
        Double result = a - b;
        OperationEntity operationEntity = new OperationEntity();
        operationEntity.setFirstArgument(a);
        operationEntity.setSecondArgument(b);
        operationEntity.setResult(result);
        operationEntity.setCalculatedAt(LocalDateTime.now());
        operationEntity = operationsRepository.save(operationEntity);
        return operationMapper.toOperationDto(operationEntity);
    }

    public void remove(UUID id) {
        operationsRepository.deleteById(id);
    }

    public OperationDto get(UUID id) {
        return operationMapper.toOperationDto(operationsRepository.findById(id).get());
    }
}
