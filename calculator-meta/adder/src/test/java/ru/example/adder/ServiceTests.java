package ru.example.adder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.example.adder.entity.OperationEntity;
import ru.example.adder.repository.OperationsRepository;
import ru.example.adder.service.Service;
import ru.example.adder.service.OperationMapper;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {
    @Mock
    private OperationsRepository operationsRepository;

    public ServiceTests() {
    }

    @Test
    public void testGet() {
        Service service = new Service(operationsRepository, new OperationMapper());

        UUID id = UUID.randomUUID();

        when(operationsRepository.findById(id)).thenReturn(Optional.of(
                OperationEntity.builder()
                        .id(id)
                        .firstArgument(1.0)
                        .secondArgument(2.0)
                        .result(3.0)
                        .build()
        ));

        var result = service.get(id);
        Assertions.assertEquals(id, result.getId());
        Assertions.assertEquals(1.0, result.getFirstArgument());
        Assertions.assertEquals(2.0, result.getSecondArgument());
        Assertions.assertEquals(3.0, result.getResult());
    }

    @Test
    public void testRemove() {
        Service service = new Service(operationsRepository, new OperationMapper());

        UUID id = UUID.randomUUID();

        when(operationsRepository.findById(id)).thenReturn(Optional.of(
                OperationEntity.builder()
                        .id(id)
                        .firstArgument(1.0)
                        .secondArgument(2.0)
                        .result(3.0)
                        .build()
        ));

        var result = service.get(id);
        Assertions.assertEquals(id, result.getId());
        Assertions.assertEquals(1.0, result.getFirstArgument());
        Assertions.assertEquals(2.0, result.getSecondArgument());
        Assertions.assertEquals(3.0, result.getResult());

        service.remove(id);
        when(operationsRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> service.get(id));
    }

    @Test
    public void testCalculate() {
        Service service = new Service(operationsRepository, new OperationMapper());

        var entityToReturn =
                OperationEntity.builder()
                        .id(UUID.randomUUID())
                        .firstArgument(4.0)
                        .secondArgument(5.0)
                        .result(20.0)
                        .build();

        when(operationsRepository.save(OperationEntity.builder()
                .id(any())
                .build())).thenReturn(entityToReturn);

        var result = service.calculate(4.0, 5.0);
        Assertions.assertEquals(20.0, result.getResult());
    }
}
