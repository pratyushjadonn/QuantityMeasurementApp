package QuantityMeasurmentApp.service;

import QuantityMeasurmentApp.dto.*;
import java.util.List;

public interface QuantityMeasurementService {

    QuantityMeasurementDTO add(QuantityInputDTO input);

    QuantityMeasurementDTO subtract(QuantityInputDTO input);

    // FIX: return type Double tha — impl mein QuantityMeasurementDTO tha, mismatch fix kiya
    QuantityMeasurementDTO divide(QuantityInputDTO input);

    QuantityMeasurementDTO convert(QuantityInputDTO input);

    QuantityMeasurementDTO compare(QuantityInputDTO input);

    List<?> getHistory();

    List<?> getByOperation(String operation);
}