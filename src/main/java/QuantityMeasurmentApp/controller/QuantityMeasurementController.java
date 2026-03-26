package QuantityMeasurmentApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import QuantityMeasurmentApp.dto.*;
import QuantityMeasurmentApp.service.QuantityMeasurementService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private QuantityMeasurementService service;

    @PostMapping("/add")
    public QuantityMeasurementDTO add(@Valid @RequestBody QuantityInputDTO input) {
        return service.add(input);
    }

    @PostMapping("/subtract")
    public QuantityMeasurementDTO subtract(@Valid @RequestBody QuantityInputDTO input) {
        return service.subtract(input);
    }

    @PostMapping("/divide")
    public Double divide(@Valid @RequestBody QuantityInputDTO input) {
        return service.divide(input);
    }

    @PostMapping("/convert")
    public QuantityMeasurementDTO convert(@Valid @RequestBody QuantityInputDTO input) {
        return service.convert(input);
    }

    @PostMapping("/compare")
    public QuantityMeasurementDTO compare(@Valid @RequestBody QuantityInputDTO input) {
        return service.compare(input);
    }

    @GetMapping("/history")
    public List<?> history() {
        return service.getHistory();
    }

    @GetMapping("/history/{operation}")
    public List<?> historyByOperation(@PathVariable String operation) {
        return service.getByOperation(operation.toUpperCase());
    }
}