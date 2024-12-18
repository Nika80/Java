package ru.example.subtractor.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.example.subtractor.dto.ErrorMessageDto;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    public GlobalExceptionHandler() {
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessageDto> handleNotFoundException(Exception ex, HttpServletRequest ignoredRequest) {
        return new ResponseEntity<>(new ErrorMessageDto(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDto> handleEveryException(Exception ex, HttpServletRequest ignoredRequest) {
        return new ResponseEntity<>(new ErrorMessageDto(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
