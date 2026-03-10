package QuantityMeasurmentApp;
import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (Double.isNaN(value) || Double.isInfinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    // ---------------- Conversion ----------------

    public Quantity<U> convertTo(U targetUnit) {

        double baseValue = unit.convertToBaseUnit(value);

        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        convertedValue = roundToTwoDecimals(convertedValue);

        return new Quantity<>(convertedValue, targetUnit);
    }

    // ---------------- Addition ----------------

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double resultBase =
                performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result = targetUnit.convertFromBaseUnit(resultBase);

        result = roundToTwoDecimals(result);

        return new Quantity<>(result, targetUnit);
    }

    // ---------------- Subtraction ----------------

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double resultBase =
                performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result = targetUnit.convertFromBaseUnit(resultBase);

        result = roundToTwoDecimals(result);

        return new Quantity<>(result, targetUnit);
    }

    // ---------------- Division ----------------

    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    // ---------------- Equality ----------------

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (unit.getClass() != other.unit.getClass())
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Double.compare(base1, base2) == 0;
    }

    // ---------------- HashCode ----------------

    @Override
    public int hashCode() {
        return Objects.hash(unit.convertToBaseUnit(value));
    }

    // ---------------- ToString ----------------

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }

    // ---------------- Helper Methods ----------------

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetUnitRequired) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different measurement categories not allowed");

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Values must be finite");

        if (targetUnitRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
    }

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation) {

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return operation.compute(base1, base2);
    }

    // ---------------- Arithmetic Enum ----------------

    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {
            if (b == 0)
                throw new ArithmeticException("Cannot divide by zero");
            return a / b;
        });

        private final DoubleBinaryOperator operation;

        ArithmeticOperation(DoubleBinaryOperator operation) {
            this.operation = operation;
        }

        public double compute(double a, double b) {
            return operation.applyAsDouble(a, b);
        }
    }
}