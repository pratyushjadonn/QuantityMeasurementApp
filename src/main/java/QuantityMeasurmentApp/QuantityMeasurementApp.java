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

        System.out.println("Sum = " + q1.add(q2, targetUnit));
    }

    public static void main(String[] args) {

    	Quantity<VolumeUnit> litre =
    	        new Quantity<>(1.0, VolumeUnit.LITRE);

    	Quantity<VolumeUnit> ml =
    	        new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

    	System.out.println(litre.equals(ml));

    	System.out.println(litre.convertTo(VolumeUnit.GALLON));

    	System.out.println(litre.add(ml));
    }
}