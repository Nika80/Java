package ru.example.divider.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.example.divider.entity.OperationEntity;

import java.util.UUID;

@Repository
public interface OperationsRepository extends CrudRepository<OperationEntity, UUID> {
}
