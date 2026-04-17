package com.quantityapp.quantity.units;

public interface IMeasurable {
    double getConversionFactor();
    String getUnitName();
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double value);

    default void validateOperationSupport(String operation) {
        // default: all operations allowed
    }
}
