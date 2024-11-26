package ru.example.adder.service;

import org.springframework.stereotype.Service;

@Service
public class AddService {
    public Double add(Double a, Double b) {
        return a + b;
    }
}
