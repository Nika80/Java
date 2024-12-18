package ru.example.adder.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class OperationDto {
    UUID id;
    Double firstArgument;
    Double secondArgument;
    Double result;
    LocalDateTime calculatedAt;
}
