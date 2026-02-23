package QuantityMeasurmentApp;

public class Length {

    private final double value;
    private final LengthUnit unit;

    // =========================
    // ENUM with conversion factors
    // Base unit = INCHES
    // =========================
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
    }

    // =========================
    // Constructor
    // =========================
    public Length(double value, LengthUnit unit) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        this.value = value;
        this.unit = unit;
    }
    // =========================
    // Convert to base unit (INCHES)
    // =========================
    private double convertToBaseUnit() {
        return value * unit.getConversionFactor();
    }


    // =========================
    // UC5 STATIC CONVERSION METHOD
    // =========================
    public static double convert(
            double value,
            LengthUnit source,
            LengthUnit target) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        if (source == null || target == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        // convert to base unit
        double baseValue =
                value * source.getConversionFactor();

        // convert to target unit
        return baseValue /
                target.getConversionFactor();
    }


    // =========================
    // INSTANCE METHOD conversion
    // =========================
    public Length convertTo(LengthUnit targetUnit) {

        double newValue =
                convert(this.value, this.unit, targetUnit);

        return new Length(newValue, targetUnit);
    }


    // =========================
    // equals override
    // =========================
    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        Length other = (Length) o;

        return Double.compare(
                this.convertToBaseUnit(),
                other.convertToBaseUnit()) == 0;
    }
    // =========================
    // toString override
    // =========================
    @Override
    public String toString() {
        return value + " " + unit;
    }
}