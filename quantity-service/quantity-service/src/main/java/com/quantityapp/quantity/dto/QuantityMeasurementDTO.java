package com.quantityapp.quantity.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementDTO {
    private String operation;
    private Double result;
    private String message;
    private boolean error;

    // success
    public QuantityMeasurementDTO(String operation, Double result) {
        this.operation = operation;
        this.result    = result;
        this.error     = false;
    }

    // error
    public QuantityMeasurementDTO(String operation, String message) {
        this.operation = operation;
        this.message   = message;
        this.error     = true;
    }
}
