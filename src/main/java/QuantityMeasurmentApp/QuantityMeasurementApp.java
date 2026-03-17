package QuantityMeasurmentApp;
import java.util.*;

import QuantityMeasurmentApp.entity.IMeasurable;
import QuantityMeasurmentApp.entity.Quantity;
import QuantityMeasurmentApp.service.TemperatureUnit;
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
	        System.out.println("Result: " + q1.add(q2));
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

	    	Quantity<TemperatureUnit> t1 =
	                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

	        Quantity<TemperatureUnit> t2 =
	                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

	        System.out.println("Equality:");
	        System.out.println(t1.equals(t2));

	        System.out.println("\nConversion:");
	        System.out.println(
	                new Quantity<>(100.0, TemperatureUnit.CELSIUS)
	                        .convertTo(TemperatureUnit.FAHRENHEIT)
	        );
	        System.out.println("\nUnsupported operation:");

	        Quantity<TemperatureUnit> temp =
	                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

	        try {
	            temp.add(new Quantity<>(50.0, TemperatureUnit.CELSIUS));
	        } catch (UnsupportedOperationException e) {
	            System.out.println(e.getMessage());
	        }
	    }
}