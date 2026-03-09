package QuantityMeasurmentApplTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import QuantityMeasurmentApp.Length;
import QuantityMeasurmentApp.LengthUnit;
import QuantityMeasurmentApp.QuantityMeasurementApp;
class QuantityMeasurementAppTest {
	// ==============================
		// UC8 Additional Test Cases
		// ==============================

		@Test
		public void testAddition_ResultInCentimeters() {

		    Length l1 = new Length(1.0, LengthUnit.INCHES);
		    Length l2 = new Length(1.0, LengthUnit.INCHES);

		    Length result = l1.add(l2, LengthUnit.CENTIMETERS);

		    Length expected = new Length(5.08, LengthUnit.CENTIMETERS);

		    assertEquals(expected, result);
		}

		@Test
		public void testAddition_ResultInYards_FromMixedUnits() {

		    Length l1 = new Length(6.0, LengthUnit.FEET);
		    Length l2 = new Length(36.0, LengthUnit.INCHES); // 3 feet

		    Length result = l1.add(l2, LengthUnit.YARDS);

		    Length expected = new Length(3.0, LengthUnit.YARDS);

		    assertEquals(expected, result);
		}

		@Test
		public void testAddition_NegativeValues_TargetUnit() {

		    Length l1 = new Length(5.0, LengthUnit.FEET);
		    Length l2 = new Length(-2.0, LengthUnit.FEET);

		    Length result = l1.add(l2, LengthUnit.FEET);

		    Length expected = new Length(3.0, LengthUnit.FEET);

		    assertEquals(expected, result);
		}

		@Test
		public void testAddition_LargeValues_TargetUnit() {

		    Length l1 = new Length(1000.0, LengthUnit.FEET);
		    Length l2 = new Length(1000.0, LengthUnit.FEET);

		    Length result = l1.add(l2, LengthUnit.YARDS);

		    Length expected = new Length(666.6666666666666, LengthUnit.YARDS);

		    assertEquals(expected, result);
		}

		@Test
		public void testAddition_NullOperand_TargetUnit() {

		    Length l1 = new Length(5.0, LengthUnit.FEET);

		    assertThrows(IllegalArgumentException.class, () -> {
		        l1.add(null, LengthUnit.FEET);
		    });
		}

		@Test
		public void testAddition_NullTargetUnit() {

		    Length l1 = new Length(5.0, LengthUnit.FEET);
		    Length l2 = new Length(5.0, LengthUnit.FEET);

		    assertThrows(IllegalArgumentException.class, () -> {
		        l1.add(l2, null);
		    });
		}

		@Test
		public void testAddition_Commutativity_TargetUnit() {

		    Length l1 = new Length(1.0, LengthUnit.FEET);
		    Length l2 = new Length(12.0, LengthUnit.INCHES);

		    Length result1 = l1.add(l2, LengthUnit.INCHES);
		    Length result2 = l2.add(l1, LengthUnit.INCHES);

		    assertEquals(result1, result2);
		}
}