package QuantityMeasurmentApp;

public enum WeightUnit implements IMeasurable {

    GRAM(1.0),
    KILOGRAM(1000.0),
    POUND(453.592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
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