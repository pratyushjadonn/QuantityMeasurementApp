package QuantityMeasurmentApplTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import QuantityMeasurmentApp.Length;
import QuantityMeasurmentApp.QuantityMeasurementApp;
class QuantityMeasurementAppTest {
	// ==============================
		// UC6 - Addition Tests
		// ==============================

		@Test
		public void testAddition_SameUnit_FeetPlusFeet() {
		    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		    Length l2 = new Length(2.0, Length.LengthUnit.FEET);

		    Length result = l1.add(l2);

		    assertEquals(new Length(3.0, Length.LengthUnit.FEET), result);
		}

		@Test
		public void testAddition_SameUnit_InchesPlusInches() {
		    Length l1 = new Length(6.0, Length.LengthUnit.INCHES);
		    Length l2 = new Length(6.0, Length.LengthUnit.INCHES);

		    Length result = l1.add(l2);

		    assertEquals(new Length(12.0, Length.LengthUnit.INCHES), result);
		}

		@Test
		public void testAddition_CrossUnit_FeetPlusInches() {
		    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		    Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		    Length result = l1.add(l2);

		    assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
		}

		@Test
		public void testAddition_CrossUnit_InchesPlusFeet() {
		    Length l1 = new Length(12.0, Length.LengthUnit.INCHES);
		    Length l2 = new Length(1.0, Length.LengthUnit.FEET);

		    Length result = l1.add(l2);

		    assertEquals(new Length(24.0, Length.LengthUnit.INCHES), result);
		}

		@Test
		public void testAddition_YardPlusFeet() {
		    Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
		    Length l2 = new Length(3.0, Length.LengthUnit.FEET);

		    Length result = l1.add(l2);

		    assertEquals(new Length(2.0, Length.LengthUnit.YARDS), result);
		}

		@Test
		public void testAddition_CentimeterPlusInch() {

		    Length l1 = new Length(2.54, Length.LengthUnit.CENTIMETERS);
		    Length l2 = new Length(1.0, Length.LengthUnit.INCHES);

		    Length result = l1.add(l2);

		    assertEquals(
		            5.08,
		            result.getValue(),
		            0.0001
		    );

		    assertEquals(
		            Length.LengthUnit.CENTIMETERS,
		            result.getUnit()
		    );
		}

		@Test
		public void testAddition_WithZero() {
		    Length l1 = new Length(5.0, Length.LengthUnit.FEET);
		    Length l2 = new Length(0.0, Length.LengthUnit.INCHES);

		    Length result = l1.add(l2);

		    assertEquals(new Length(5.0, Length.LengthUnit.FEET), result);
		}

		@Test
		public void testAddition_NegativeValues() {
		    Length l1 = new Length(5.0, Length.LengthUnit.FEET);
		    Length l2 = new Length(-2.0, Length.LengthUnit.FEET);

		    Length result = l1.add(l2);

		    assertEquals(new Length(3.0, Length.LengthUnit.FEET), result);
		}

		@Test
		public void testAddition_Commutativity() {
		    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		    Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		    Length result1 = l1.add(l2);
		    Length result2 = l2.add(l1);

		    assertEquals(
		            result1.convertTo(Length.LengthUnit.INCHES),
		            result2
		    );
		}

		@Test
		public void testAddition_NullOperandThrowsException() {
		    Length l1 = new Length(1.0, Length.LengthUnit.FEET);

		    assertThrows(IllegalArgumentException.class, () -> {
		        l1.add(null);
		    });
		}

		@Test
		public void testAddition_LargeValues() {
		    Length l1 = new Length(1e6, Length.LengthUnit.FEET);
		    Length l2 = new Length(1e6, Length.LengthUnit.FEET);

		    Length result = l1.add(l2);

		    assertEquals(new Length(2e6, Length.LengthUnit.FEET), result);
		}

		@Test
		public void testAddition_SmallValues() {
		    Length l1 = new Length(0.001, Length.LengthUnit.FEET);
		    Length l2 = new Length(0.002, Length.LengthUnit.FEET);

		    Length result = l1.add(l2);

		    assertEquals(new Length(0.003, Length.LengthUnit.FEET), result);
		}
}