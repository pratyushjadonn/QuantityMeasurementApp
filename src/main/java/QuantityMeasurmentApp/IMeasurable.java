package QuantityMeasurmentApp;

@FunctionalInterface
interface SupportsArithmetic {
    boolean isSupported();
}

public interface IMeasurable {

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double value);

    String getUnitName();

    // Default lambda: all units support arithmetic
    SupportsArithmetic supportsArithmetic = () -> true;

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    // Default validation (do nothing)
    default void validateOperationSupport(String operation) {
        // subclasses may override
    }

	double getConversionFactor();
}