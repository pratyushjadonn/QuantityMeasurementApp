package com.quantityapp.quantity.service;

import com.quantityapp.quantity.dto.QuantityInputDTO;
import com.quantityapp.quantity.dto.QuantityMeasurementDTO;

import java.util.List;

public interface QuantityMeasurementService {
    QuantityMeasurementDTO add(QuantityInputDTO input);
    QuantityMeasurementDTO subtract(QuantityInputDTO input);
    QuantityMeasurementDTO divide(QuantityInputDTO input);
    QuantityMeasurementDTO convert(QuantityInputDTO input);
    QuantityMeasurementDTO compare(QuantityInputDTO input);
    List<?> getHistory();
    List<?> getByOperation(String operation);
    void deleteAllHistory();
    void deleteHistoryById(Long id);
}
