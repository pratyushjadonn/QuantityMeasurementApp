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



		public static void main(String[] args) {

		    Length l1 = new Length(1.0, LengthUnit.FEET);
		    Length l2 = new Length(12.0, LengthUnit.INCHES);

		    Length result = l1.add(l2, LengthUnit.FEET);

		    System.out.println(result); // 2 FEET
	    }
}