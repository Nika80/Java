package ru.example.divider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.example.divider.dto.OperationDto;
import ru.example.divider.service.Service;

import java.util.UUID;

@RestController
public class Controller {
    private final Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("/calculate/{a}/{b}")
    public OperationDto calculate(@PathVariable("a") Double a, @PathVariable("b") Double b) {
        return service.calculate(a, b);
    }

    @GetMapping("/get/{id}")
    public OperationDto get(@PathVariable("id") UUID id) {
        return service.get(id);
    }

    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable("id") UUID id) {
        service.remove(id);
    }
}
