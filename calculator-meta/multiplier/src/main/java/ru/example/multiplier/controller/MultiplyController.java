package ru.example.multiplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.example.multiplier.service.MultiplyService;

@RestController
public class MultiplyController {
    private final MultiplyService multiplyService;

    @Autowired
    public MultiplyController(MultiplyService multiplyService) {
        this.multiplyService = multiplyService;
    }

    @GetMapping("/{a}/{b}")
    public Double multiply(@PathVariable("a") Double a, @PathVariable("b") Double b) {
        return multiplyService.multiply(a, b);
    }
}
