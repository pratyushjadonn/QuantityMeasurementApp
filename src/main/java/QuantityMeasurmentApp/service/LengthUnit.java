package QuantityMeasurmentApp.service;

import QuantityMeasurmentApp.entity.IMeasurable;

public enum LengthUnit implements IMeasurable {

    INCHES(1.0),
    FEET(12.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }

	@Override
	public double convertToBaseUnit(double value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double convertFromBaseUnit(double value) {
		// TODO Auto-generated method stub
		return 0;
	}
}