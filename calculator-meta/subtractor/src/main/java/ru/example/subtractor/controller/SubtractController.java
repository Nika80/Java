package ru.example.subtractor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.example.subtractor.service.SubtractService;

@RestController
public class SubtractController {
    private final SubtractService subtractService;

    @Autowired
    public SubtractController(SubtractService subtractService) {
        this.subtractService = subtractService;
    }

    @GetMapping("/{a}/{b}")
    public Double subtract(@PathVariable("a") Double a, @PathVariable("b") Double b) {
        return subtractService.subtract(a, b);
    }
}
