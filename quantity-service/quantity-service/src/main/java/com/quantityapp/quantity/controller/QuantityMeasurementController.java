package com.quantityapp.quantity.controller;

import com.quantityapp.quantity.dto.QuantityInputDTO;
import com.quantityapp.quantity.dto.QuantityMeasurementDTO;
import com.quantityapp.quantity.service.QuantityMeasurementServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    private final QuantityMeasurementServiceImpl service;

    public QuantityMeasurementController(QuantityMeasurementServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/add")
    public QuantityMeasurementDTO add(@Valid @RequestBody QuantityInputDTO input) {
        return service.add(input);
    }

    @PostMapping("/subtract")
    public QuantityMeasurementDTO subtract(@Valid @RequestBody QuantityInputDTO input) {
        return service.subtract(input);
    }

    @PostMapping("/divide")
    public QuantityMeasurementDTO divide(@Valid @RequestBody QuantityInputDTO input) {
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

    @DeleteMapping("/history")
    public void deleteAllHistory() {
        service.deleteAllHistory();
    }

    @DeleteMapping("/history/{id}")
    public void deleteHistoryById(@PathVariable Long id) {
        service.deleteHistoryById(id);
    }
}
