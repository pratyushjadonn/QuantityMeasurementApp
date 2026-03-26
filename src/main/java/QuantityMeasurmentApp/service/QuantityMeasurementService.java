package QuantityMeasurmentApp.service;

import QuantityMeasurmentApp.dto.*;

import java.util.List;

public interface QuantityMeasurementService {

    QuantityMeasurementDTO add(QuantityInputDTO input);

    QuantityMeasurementDTO subtract(QuantityInputDTO input);

    Double divide(QuantityInputDTO input);

    QuantityMeasurementDTO convert(QuantityInputDTO input);

    QuantityMeasurementDTO compare(QuantityInputDTO input);

    List<?> getHistory();

    List<?> getByOperation(String operation);
}