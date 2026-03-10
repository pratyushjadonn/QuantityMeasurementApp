package QuantityMeasurmentApp;
import java.util.*;
public class QuantityMeasurementApp {
	 public static <U extends IMeasurable> void demonstrateEquality(
	            Quantity<U> q1, Quantity<U> q2) {

	        System.out.println(q1 + " equals " + q2 + " : " + q1.equals(q2));
	    }

	    public static <U extends IMeasurable> void demonstrateConversion(
	            Quantity<U> quantity, U targetUnit) {

	        System.out.println(quantity + " = " + quantity.convertTo(targetUnit));
	    }

	    public static <U extends IMeasurable> void demonstrateAddition(
	            Quantity<U> q1, Quantity<U> q2, U targetUnit) {

	        System.out.println("Adding: " + q1 + " + " + q2);
	        System.out.println("Result: " + q1.add(q2, targetUnit));
	    }

	    // UC12
	    public static <U extends IMeasurable> void demonstrateSubtraction(
	            Quantity<U> q1, Quantity<U> q2) {

	        Quantity<U> result = q1.subtract(q2);

	        System.out.println("Subtracting: " + q1 + " - " + q2);
	        System.out.println("Result: " + result);
	    }

	    public static <U extends IMeasurable> void demonstrateDivision(
	            Quantity<U> q1, Quantity<U> q2) {

	        double result = q1.divide(q2);

	        System.out.println("Dividing: " + q1 + " / " + q2);
	        System.out.println("Result: " + result);
	    }

	    public static void main(String[] args) {

	        Quantity<LengthUnit> feet =
	                new Quantity<>(10.0, LengthUnit.FEET);

	        Quantity<LengthUnit> inches =
	                new Quantity<>(6.0, LengthUnit.INCHES);

	        // Addition
	        demonstrateAddition(feet, inches, LengthUnit.FEET);

	        // Subtraction
	        demonstrateSubtraction(feet, inches);

	        // Division
	        demonstrateDivision(
	                new Quantity<>(10.0, LengthUnit.FEET),
	                new Quantity<>(2.0, LengthUnit.FEET)
	        );

	        // Conversion
	        demonstrateConversion(
	                new Quantity<>(1.0, LengthUnit.FEET),
	                LengthUnit.INCHES
	        );

	        // Equality
	        demonstrateEquality(
	                new Quantity<>(12.0, LengthUnit.INCHES),
	                new Quantity<>(1.0, LengthUnit.FEET)
	        );
	    }
}