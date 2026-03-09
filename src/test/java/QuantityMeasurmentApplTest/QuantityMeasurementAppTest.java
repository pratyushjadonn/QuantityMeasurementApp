package QuantityMeasurmentApplTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import QuantityMeasurmentApp.LengthUnit;
import QuantityMeasurmentApp.Quantity;
import QuantityMeasurmentApp.VolumeUnit;
import QuantityMeasurmentApp.WeightUnit;
class QuantityMeasurementAppTest {
	@Test
	public void testSubtraction_SameUnit() {

	    Quantity<LengthUnit> q1 =
	            new Quantity<>(10.0, LengthUnit.FEET);

	    Quantity<LengthUnit> q2 =
	            new Quantity<>(5.0, LengthUnit.FEET);

	    Quantity<LengthUnit> result = q1.subtract(q2);

	    assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
	}
	@Test
	public void testSubtraction_CrossUnit() {

	    Quantity<LengthUnit> feet =
	            new Quantity<>(10.0, LengthUnit.FEET);

	    Quantity<LengthUnit> inches =
	            new Quantity<>(6.0, LengthUnit.INCHES);

	    Quantity<LengthUnit> result = feet.subtract(inches);

	    assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
	}
	@Test
	public void testSubtraction_ExplicitTargetUnit() {

	    Quantity<LengthUnit> feet =
	            new Quantity<>(10.0, LengthUnit.FEET);

	    Quantity<LengthUnit> inches =
	            new Quantity<>(6.0, LengthUnit.INCHES);

	    Quantity<LengthUnit> result =
	            feet.subtract(inches, LengthUnit.INCHES);

	    assertEquals(new Quantity<>(114.0, LengthUnit.INCHES), result);
	}
	
	@Test
	public void testSubtraction_NegativeResult() {

	    Quantity<LengthUnit> q1 =
	            new Quantity<>(5.0, LengthUnit.FEET);

	    Quantity<LengthUnit> q2 =
	            new Quantity<>(10.0, LengthUnit.FEET);

	    Quantity<LengthUnit> result = q1.subtract(q2);

	    assertEquals(new Quantity<>(-5.0, LengthUnit.FEET), result);
	}
	
	@Test
	public void testDivision_SameUnit() {

	    Quantity<LengthUnit> q1 =
	            new Quantity<>(10.0, LengthUnit.FEET);

	    Quantity<LengthUnit> q2 =
	            new Quantity<>(2.0, LengthUnit.FEET);

	    assertEquals(5.0, q1.divide(q2));
	}
	@Test
	public void testDivision_CrossUnit() {

	    Quantity<LengthUnit> inches =
	            new Quantity<>(24.0, LengthUnit.INCHES);

	    Quantity<LengthUnit> feet =
	            new Quantity<>(2.0, LengthUnit.FEET);

	    assertEquals(1.0, inches.divide(feet));
	}
	
	@Test
	public void testDivision_ByZero() {

	    Quantity<LengthUnit> q1 =
	            new Quantity<>(10.0, LengthUnit.FEET);

	    Quantity<LengthUnit> q2 =
	            new Quantity<>(0.0, LengthUnit.FEET);

	    assertThrows(ArithmeticException.class,
	            () -> q1.divide(q2));
	}

}