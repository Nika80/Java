package ru.example.divider.service;

import org.springframework.stereotype.Service;

@Service
public class DivideService {
    public DivideService() {
    }

    public Double divide(Double a, Double b) {
        return a / b;
    }
}
