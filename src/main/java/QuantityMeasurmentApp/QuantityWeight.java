package QuantityMeasurmentApp;

import java.util.Objects;

/**
 * Immutable class representing weight measurement
 */
public final class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    private static final double EPSILON = 1e-6;

    public QuantityWeight(double value, WeightUnit unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    // convert to base unit
    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    // convert to target unit
    public QuantityWeight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = toBaseUnit();

        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityWeight(convertedValue, targetUnit);
    }

    // addition (default unit = this.unit)
    public QuantityWeight add(QuantityWeight other) {

        if (other == null)
            throw new IllegalArgumentException("Other weight cannot be null");

        double sumBase = this.toBaseUnit() + other.toBaseUnit();

        double resultValue = this.unit.convertFromBaseUnit(sumBase);

        return new QuantityWeight(resultValue, this.unit);
    }

    // addition with explicit target unit
    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {

        if (other == null || targetUnit == null)
            throw new IllegalArgumentException("Invalid input");

        double sumBase = this.toBaseUnit() + other.toBaseUnit();

        double resultValue = targetUnit.convertFromBaseUnit(sumBase);

        return new QuantityWeight(resultValue, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        QuantityWeight other = (QuantityWeight) obj;

        double thisBase = this.toBaseUnit();
        double otherBase = other.toBaseUnit();

        return Math.abs(thisBase - otherBase) < 0.001;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBaseUnit());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
