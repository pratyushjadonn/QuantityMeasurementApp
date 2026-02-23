package QuantityMeasurmentApp;
import java.util.*;
public class QuantityMeasurementApp {
	//generic method to demonstrate Length equality check
	public static boolean demonstrateLengthEquality(Length length1, Length length2) {
		if (length1 == null || length2 == null) {
			return false;
		}

		boolean result = length1.equals(length2);
		System.out.println("Comparing: " + length1 + " and " + length2);
		System.out.println("Are equal? " + result);
		return result;
	}

	//demonstrate Feet equality
	public static void demonstrateFeetEquality() {
		Length length1 = new Length(1.0, Length.LengthUnit.FEET);
		Length length2 = new Length(1.0, Length.LengthUnit.FEET);

		System.out.println("Feet to Feet Comparison:");
		demonstrateLengthEquality(length1, length2);
		System.out.println();
	}

	//demonstrate Inches equality
	public static void demonstrateInchesEquality() {
		Length length1 = new Length(1.0, Length.LengthUnit.INCHES);
		Length length2 = new Length(1.0, Length.LengthUnit.INCHES);

		System.out.println("Inches to Inches Comparison:");
		demonstrateLengthEquality(length1, length2);
		System.out.println();
	}

	//demonstrate Feet and Inches comparison
	public static void demonstrateFeetInchesComparison() {
		Length length1 = new Length(1.0, Length.LengthUnit.FEET);
		Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

		System.out.println("Feet to Inches Comparison:");
		demonstrateLengthEquality(length1, length2);
		System.out.println();
	}

	public static void main(String[] args) {
		demonstrateFeetEquality();
		demonstrateInchesEquality();
		demonstrateFeetInchesComparison();
	}
}