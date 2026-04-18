package com.quantityapp.quantity.units;

public enum VolumeUnit implements IMeasurable {
    MILLILITRE(1.0), LITRE(1000.0), GALLON(3785.41);

    private final double factor;

    VolumeUnit(double factor) { this.factor = factor; }

    @Override public double convertToBaseUnit(double value)  { return value * factor; }
    @Override public double convertFromBaseUnit(double base) { return base / factor; }
    @Override public double getConversionFactor()            { return factor; }
    @Override public String getUnitName()                    { return name(); }
}
