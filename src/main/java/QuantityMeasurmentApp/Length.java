package QuantityMeasurmentApp;

public class Length {

    private final double value;
    private final LengthUnit unit;


    // Step 2: Enum for units
    public enum LengthUnit {

        FEET(12.0),     
        INCHES(1.0);   

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }
    public Length(double value, LengthUnit unit) {
		if (unit == null) {
			throw new IllegalArgumentException("Unit cannot be null");
		}
		this.value = value;
		this.unit = unit;
	}
    private double convertToBaseUnit() {

        return value * unit.getConversionFactor();

    }
    public boolean compare(Length thatLength) {

        if (thatLength == null)
            return false;

        return Double.compare(
                this.convertToBaseUnit(),
                thatLength.convertToBaseUnit()
        ) == 0;
    }
    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        Length thatLength = (Length) o;

        return compare(thatLength);
    }
    @Override
    public String toString() {

        return value + " " + unit;

    }

}