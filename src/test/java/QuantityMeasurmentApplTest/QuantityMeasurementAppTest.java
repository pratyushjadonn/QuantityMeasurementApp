package QuantityMeasurmentApplTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import QuantityMeasurmentApp.Length;
import QuantityMeasurmentApp.QuantityMeasurementApp;
class QuantityMeasurementAppTest {
	@Test
	public void testEquality_SameUnit_FeetEqualsFeet() {
	    Length l1 = new Length(2.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(2.0, Length.LengthUnit.FEET);

	    assertEquals(l1, l2);
	}

	@Test
	public void testEquality_CrossUnit_FeetEqualsInches() {
	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

	    assertEquals(l1, l2);
	}

	@Test
	public void testEquality_CrossUnit_InchEqualsCentimeter() {
	    Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
	    Length l2 = new Length(2.54, Length.LengthUnit.CENTIMETERS);

	    assertEquals(l1, l2);
	}

	@Test
	public void testEquality_YardEqualsFeet() {
	    Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
	    Length l2 = new Length(3.0, Length.LengthUnit.FEET);

	    assertEquals(l1, l2);
	}

	@Test
	public void testEquality_NotEqualDifferentValues() {
	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(2.0, Length.LengthUnit.FEET);

	    assertNotEquals(l1, l2);
	}

	@Test
	public void testEquality_NotEqualDifferentUnits() {
	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(10.0, Length.LengthUnit.INCHES);

	    assertNotEquals(l1, l2);
	}

	@Test
	public void testEquality_WithZero() {
	    Length l1 = new Length(0.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(0.0, Length.LengthUnit.INCHES);

	    assertEquals(l1, l2);
	}

	@Test
	public void testEquality_NullComparison() {
	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);

	    assertNotEquals(l1, null);
	}

	@Test
	public void testEquality_SameReference() {
	    Length l1 = new Length(5.0, Length.LengthUnit.FEET);

	    assertEquals(l1, l1);
	}

	@Test
	public void testEquality_LargeValues() {
	    Length l1 = new Length(1000.0, Length.LengthUnit.YARDS);
	    Length l2 = new Length(3000.0, Length.LengthUnit.FEET);

	    assertEquals(l1, l2);
	}
}