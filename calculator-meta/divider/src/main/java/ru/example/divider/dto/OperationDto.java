package ru.example.divider.dto;

import lombok.Builder;
import lombok.Data;

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
