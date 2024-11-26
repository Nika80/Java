package ru.example.subtractor.service;

import org.springframework.stereotype.Service;

@Service
public class SubtractService {
    public SubtractService() {
    }

    public Double subtract(Double a, Double b) {
        return a - b;
    }
}
