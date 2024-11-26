package ru.example.adder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.example.adder.service.AddService;

@RestController
public class AddController {
    private final AddService addService;

    @Autowired
    public AddController(AddService addService) {
        this.addService = addService;
    }

    @GetMapping("/{a}/{b}")
    public Double add(@PathVariable("a") Double a, @PathVariable("b") Double b) {
        return addService.add(a, b);
    }
}
