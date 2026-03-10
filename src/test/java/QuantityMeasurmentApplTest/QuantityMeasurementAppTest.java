package QuantityMeasurmentApplTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import QuantityMeasurmentApp.*;
class QuantityMeasurementAppTest {
	 @Test
	    public void testEquality_SameUnit() {

	        Quantity<LengthUnit> q1 =
	                new Quantity<>(10.0, LengthUnit.FEET);

	        Quantity<LengthUnit> q2 =
	                new Quantity<>(10.0, LengthUnit.FEET);

	        assertEquals(q1, q2);
	    }

	    @Test
	    public void testEquality_CrossUnit() {

	        Quantity<LengthUnit> inches =
	                new Quantity<>(12.0, LengthUnit.INCHES);

	        Quantity<LengthUnit> feet =
	                new Quantity<>(1.0, LengthUnit.FEET);

	        assertEquals(inches, feet);
	    }


	    // ---------- Conversion Tests ----------

	    @Test
	    public void testConversion_FeetToInches() {

	        Quantity<LengthUnit> feet =
	                new Quantity<>(1.0, LengthUnit.FEET);

	        Quantity<LengthUnit> result =
	                feet.convertTo(LengthUnit.INCHES);

	        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), result);
	    }

	    @Test
	    public void testConversion_LitreToMillilitre() {

	        Quantity<VolumeUnit> litre =
	                new Quantity<>(1.0, VolumeUnit.LITRE);

	        Quantity<VolumeUnit> result =
	                litre.convertTo(VolumeUnit.MILLILITRE);

	        assertEquals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), result);
	    }


	    // ---------- Addition Tests ----------

	    @Test
	    public void testAddition_SameUnit() {

	        Quantity<LengthUnit> q1 =
	                new Quantity<>(5.0, LengthUnit.FEET);

	        Quantity<LengthUnit> q2 =
	                new Quantity<>(3.0, LengthUnit.FEET);

	        Quantity<LengthUnit> result = q1.add(q2);

	        assertEquals(new Quantity<>(8.0, LengthUnit.FEET), result);
	    }

	    @Test
	    public void testAddition_CrossUnit() {

	        Quantity<LengthUnit> feet =
	                new Quantity<>(1.0, LengthUnit.FEET);

	        Quantity<LengthUnit> inches =
	                new Quantity<>(12.0, LengthUnit.INCHES);

	        Quantity<LengthUnit> result = feet.add(inches);

	        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), result);
	    }

	    @Test
	    public void testAddition_TargetUnit() {

	        Quantity<LengthUnit> feet =
	                new Quantity<>(1.0, LengthUnit.FEET);

	        Quantity<LengthUnit> inches =
	                new Quantity<>(12.0, LengthUnit.INCHES);

	        Quantity<LengthUnit> result =
	                feet.add(inches, LengthUnit.INCHES);

	        assertEquals(new Quantity<>(24.0, LengthUnit.INCHES), result);
	    }


	    // ---------- Subtraction Tests (UC12) ----------

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
	    public void testSubtraction_TargetUnit() {

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


	    // ---------- Division Tests (UC12) ----------

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


	    // ---------- Validation Tests (UC13) ----------

	    @Test
	    public void testAdd_NullQuantity() {

	        Quantity<LengthUnit> q1 =
	                new Quantity<>(5.0, LengthUnit.FEET);

	        assertThrows(IllegalArgumentException.class,
	                () -> q1.add(null));
	    }

	    @Test
	    public void testSubtract_NullQuantity() {

	        Quantity<LengthUnit> q1 =
	                new Quantity<>(5.0, LengthUnit.FEET);

	        assertThrows(IllegalArgumentException.class,
	                () -> q1.subtract(null));
	    }

	    @Test
	    public void testDifferentMeasurementCategories() {

	        Quantity<LengthUnit> length =
	                new Quantity<>(5.0, LengthUnit.FEET);

	        Quantity<WeightUnit> weight =
	                new Quantity<>(2.0, WeightUnit.KILOGRAM);

	        assertThrows(IllegalArgumentException.class,
	                () -> length.add((Quantity) weight));
	    }

}