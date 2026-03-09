package QuantityMeasurmentApp;

public class Length {
	private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    // convert to another unit
    public Length convertTo(LengthUnit targetUnit) {

        double baseValue = unit.convertToBaseUnit(value);

        double newValue =
                targetUnit.convertFromBaseUnit(baseValue);

        return new Length(newValue, targetUnit);
    }

    // addition (default target = this.unit)
    public Length add(Length other) {

        if (other == null)
            throw new IllegalArgumentException("Cannot add null");

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;

        double result =
                this.unit.convertFromBaseUnit(sumBase);

        return new Length(result, this.unit);
    }

 // addition with target unit (UC7, UC8)
    public Length add(Length other, LengthUnit targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Cannot add null length");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        // Step 1: convert both to base unit (INCHES)
        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;

        // Step 2: convert base sum to target unit
        double result =
                targetUnit.convertFromBaseUnit(sumBase);

        return new Length(result, targetUnit);
    }
    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (!(o instanceof Length))
            return false;

        Length other = (Length) o;

        double base1 =
                this.unit.convertToBaseUnit(this.value);

        double base2 =
                other.unit.convertToBaseUnit(other.value);

        // epsilon tolerance comparison
        return Math.abs(base1 - base2) < 0.0001;
    }
    @Override
    public String toString() {
        return value + " " + unit;
    }
}