package com.quantityapp.quantity.units;

public enum LengthUnit implements IMeasurable {
    INCHES(1.0), FEET(12.0), YARDS(36.0), CENTIMETERS(0.393701);

    private final double factor;

    LengthUnit(double factor) { this.factor = factor; }

    @Override public double convertToBaseUnit(double value)   { return value * factor; }
    @Override public double convertFromBaseUnit(double base)  { return base / factor; }
    @Override public double getConversionFactor()             { return factor; }
    @Override public String getUnitName()                     { return name(); }
}
