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
    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
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
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Length other = (Length) obj;

        // convert both to INCHES
        double thisInInches = this.convertTo(LengthUnit.INCHES).getValue();
        double otherInInches = other.convertTo(LengthUnit.INCHES).getValue();

        return Math.abs(thisInInches - otherInInches) < 0.0001;
    }
    // =========================
    // toString override
    // =========================
    @Override
    public String toString() {
        return value + " " + unit;
    }
 // =========================
 // UC6 ADD METHOD
 // =========================
 public Length add(Length other) {

     if (other == null) {
         throw new IllegalArgumentException("Other length cannot be null");
     }

     if (!Double.isFinite(other.value)) {
         throw new IllegalArgumentException("Invalid value");
     }
     double thisBase = this.convertToBaseUnit();
     double otherBase = other.convertToBaseUnit();
     double sumBase = thisBase + otherBase;
     double resultValue =
             sumBase / this.unit.getConversionFactor();
     return new Length(resultValue, this.unit);
 }
 //UC7
 public Length add(Length other, LengthUnit targetUnit) {

	    if (other == null)
	        throw new IllegalArgumentException("Other length cannot be null");

	    if (targetUnit == null)
	        throw new IllegalArgumentException("Target unit cannot be null");

	    // Step 1: convert both to base unit (INCHES)
	    double baseSum =
	            this.convertToBaseUnit() +
	            other.convertToBaseUnit();

	    // Step 2: convert base sum to target unit
	    double resultValue =
	            baseSum / targetUnit.getConversionFactor();

	    // Step 3: return new object (immutability)
	    return new Length(resultValue, targetUnit);
	}
}