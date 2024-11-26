package ru.example.multiplier.service;

import org.springframework.stereotype.Service;

@Service
public class MultiplyService {
    public MultiplyService() {
    }

    public Double multiply(Double a, Double b) {
        return a * b;
    }
}
