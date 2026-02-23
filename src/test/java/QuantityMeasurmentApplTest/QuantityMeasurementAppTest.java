package QuantityMeasurmentApplTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import QuantityMeasurmentApp.Length;
import QuantityMeasurmentApp.QuantityMeasurementApp;
import QuantityMeasurmentApp.QuantityMeasurementApp.*;

class QuantityMeasurementAppTest {
	@Test
	public void testFeetEquality() {
		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(1.0, Length.LengthUnit.FEET);
		assertEquals(l1, l2);
	}

	@Test
	public void testInchesEquality() {
		Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
		Length l2 = new Length(1.0, Length.LengthUnit.INCHES);
		assertEquals(l1, l2);
	}

	@Test
	public void testFeetInchesComparison() {
		Length feet = new Length(1.0, Length.LengthUnit.FEET);
		Length inches = new Length(12.0, Length.LengthUnit.INCHES);
		assertEquals(feet, inches);
	}

	@Test
	public void testFeetInequality() {
		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(2.0, Length.LengthUnit.FEET);
		assertNotEquals(l1, l2);
	}

	@Test
	public void testInchesInequality() {
		Length l1 = new Length(2.0, Length.LengthUnit.INCHES);
		Length l2 = new Length(3.0, Length.LengthUnit.INCHES);
		assertNotEquals(l1, l2);
	}

	@Test
	public void testCrossUnitInequality() {
		Length feet = new Length(1.0, Length.LengthUnit.FEET);
		Length inches = new Length(11.0, Length.LengthUnit.INCHES);
		assertNotEquals(feet, inches);
	}

	@Test
	public void testMultipleFeetComparison() {
		Length l1 = new Length(2.0, Length.LengthUnit.FEET);
		Length l2 = new Length(2.0, Length.LengthUnit.FEET);
		assertEquals(l1, l2);
	}

	@Test
	public void yardEquals36Inches() {
		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
		Length inches = new Length(36.0, Length.LengthUnit.INCHES);
		assertEquals(yard, inches);
	}

	@Test
	public void centimeterEquals39Point3701Inches() {
		Length cm = new Length(1.0, Length.LengthUnit.CENTIMETERS);
		Length inches = new Length(0.393701, Length.LengthUnit.INCHES);
		assertEquals(cm, inches);
	}

	@Test
	public void threeFeetEqualsOneYard() {
		Length feet = new Length(3.0, Length.LengthUnit.FEET);
		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
		assertEquals(feet, yard);
	}

	@Test
	public void thirtyPoint48CmEqualsOneFoot() {

		double cmInInches = 30.48 * Length.LengthUnit.CENTIMETERS.getConversionFactor();

		double footInInches = 1.0 * Length.LengthUnit.FEET.getConversionFactor();

		assertEquals(cmInInches, footInInches, 0.001);
	}

	@Test
	public void yardNotEqualToInches() {
		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
		Length inches = new Length(12.0, Length.LengthUnit.INCHES);
		assertNotEquals(yard, inches);
	}

	@Test
	public void referenceEqualitySameObject() {
		Length length = new Length(1.0, Length.LengthUnit.FEET);
		assertEquals(length, length);
	}

	@Test
	public void equalsReturnsFalseForNull() {
		Length length = new Length(1.0, Length.LengthUnit.FEET);
		assertNotEquals(length, null);
	}

	@Test
	public void reflexiveSymmetricAndTransitiveProperty() {
		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
		Length feet = new Length(3.0, Length.LengthUnit.FEET);
		Length inches = new Length(36.0, Length.LengthUnit.INCHES);

		assertEquals(yard, feet);
		assertEquals(feet, inches);
		assertEquals(yard, inches);
	}

	@Test
	public void differentValuesSameUnitNotEqual() {
		Length l1 = new Length(1.0, Length.LengthUnit.CENTIMETERS);
		Length l2 = new Length(2.0, Length.LengthUnit.CENTIMETERS);
		assertNotEquals(l1, l2);
	}

	@Test
	public void crossUnitEqualityDemonstrateMethod() {
		assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(1.0, Length.LengthUnit.YARDS, 3.0,Length.LengthUnit.FEET));
	}
}