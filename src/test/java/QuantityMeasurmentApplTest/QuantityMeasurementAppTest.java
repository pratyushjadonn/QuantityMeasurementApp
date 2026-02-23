package QuantityMeasurmentApplTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import QuantityMeasurmentApp.Length;
import QuantityMeasurmentApp.QuantityMeasurementApp;
class QuantityMeasurementAppTest {
	// UC5 - Direct conversion API tests

	@Test
	public void testConvertFeetToInches() {
	    double result = Length.convert(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
	    assertEquals(12.0, result, 0.0001);
	}

	@Test
	public void testConvertYardsToFeet() {
	    double result = Length.convert(3.0, Length.LengthUnit.YARDS, Length.LengthUnit.FEET);
	    assertEquals(9.0, result, 0.0001);
	}

	@Test
	public void testConvertInchesToYards() {
	    double result = Length.convert(36.0, Length.LengthUnit.INCHES, Length.LengthUnit.YARDS);
	    assertEquals(1.0, result, 0.0001);
	}

	@Test
	public void testConvertCentimetersToFeet() {
	    double result = Length.convert(30.48, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.FEET);
	    assertEquals(1.0, result, 0.0001);
	}

	@Test
	public void testConvertZeroValue() {
	    double result = Length.convert(0.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
	    assertEquals(0.0, result);
	}

	@Test
	public void testConvertNegativeValue() {
	    double result = Length.convert(-1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
	    assertEquals(-12.0, result, 0.0001);
	}

	@Test
	public void testSameUnitConversion() {
	    double result = Length.convert(5.0, Length.LengthUnit.FEET, Length.LengthUnit.FEET);
	    assertEquals(5.0, result);
	}

	@Test
	public void testRoundTripConversion() {
	    double original = 5.0;
	    double converted = Length.convert(original, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
	    double back = Length.convert(converted, Length.LengthUnit.INCHES, Length.LengthUnit.FEET);
	    assertEquals(original, back, 0.0001);
	}

	@Test
	public void testNaNValueThrowsException() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        Length.convert(Double.NaN, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
	    });
	}

	@Test
	public void testInfiniteValueThrowsException() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        Length.convert(Double.POSITIVE_INFINITY, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
	    });
	}

	@Test
	public void testNullUnitThrowsException() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        Length.convert(1.0, null, Length.LengthUnit.FEET);
	    });
	}
}