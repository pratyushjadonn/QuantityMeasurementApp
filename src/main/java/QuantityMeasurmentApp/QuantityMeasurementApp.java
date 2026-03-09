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
		public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1, double value2,
				Length.LengthUnit unit2) {

			Length length1 = new Length(value1, unit1);
			Length length2 = new Length(value2, unit2);

			return demonstrateLengthEquality(length1, length2);
		}



		public static void main(String[] args) {
		    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		    Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		    Length resultFeet = l1.add(l2, Length.LengthUnit.FEET);
		    Length resultInches = l1.add(l2, Length.LengthUnit.INCHES);
		    Length resultYards = l1.add(l2, Length.LengthUnit.YARDS);

		    System.out.println(resultFeet); 
		    System.out.println(resultInches); 
		    System.out.println(resultYards); 

	    }
}