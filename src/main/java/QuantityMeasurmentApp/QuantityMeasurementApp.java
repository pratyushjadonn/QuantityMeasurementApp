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
		//overloaded method to accept raw values and units
		public static boolean demonstrateLengthComparison(double value1,LengthUnit unit1, double value2,
				LengthUnit unit2) {

			Length length1 = new Length(value1, unit1);
			Length length2 = new Length(value2, unit2);

			return demonstrateLengthEquality(length1, length2);
		}
		
		// uc9

		public static void demonstrateWeight() {

		    QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		    QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

		    System.out.println("Weight equality: " + w1.equals(w2));

		    QuantityWeight converted = w1.convertTo(WeightUnit.POUND);

		    System.out.println("1 kg in pounds = " + converted);

		    QuantityWeight sum = w1.add(w2);

		    System.out.println("Sum = " + sum);
		}



		public static void main(String[] args) {
			demonstrateWeight();
	    }
}