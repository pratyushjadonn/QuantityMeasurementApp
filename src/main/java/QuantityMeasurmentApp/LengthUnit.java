package QuantityMeasurmentApp;

public enum LengthUnit {

    INCHES(1.0),
    FEET(12.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    // convert to base unit (INCHES)
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    // convert from base unit (INCHES)
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}