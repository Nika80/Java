package ru.example.divider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.example.divider.service.DivideService;

@RestController
public class DivideController {
    private final DivideService divideService;

    public DivideController(DivideService divideService) {
        this.divideService = divideService;
    }

    @GetMapping("/{a}/{b}")
    public Double divide(@PathVariable("a") Double a, @PathVariable("b") Double b) {
        return divideService.divide(a, b);
    }
}
