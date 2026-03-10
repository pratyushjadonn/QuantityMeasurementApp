package QuantityMeasurmentApplTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import QuantityMeasurmentApp.*;
class QuantityMeasurementAppTest {
	 // ---------- Equality Tests ----------

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


    // ---------- Subtraction Tests ----------

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


    // ---------- Division Tests ----------

    @Test
    public void testDivision_ByZero() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class,
                () -> q1.divide(q2));
    }


    // =====================================================
    //                UC14 TEMPERATURE TESTS
    // =====================================================


    // Temperature Equality

    @Test
    public void testTemperatureEquality_CelsiusToCelsius() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        assertEquals(t1, t2);
    }

    @Test
    public void testTemperatureEquality_CelsiusToFahrenheit() {

        Quantity<TemperatureUnit> c =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> f =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertEquals(c, f);
    }

    @Test
    public void testTemperatureEquality_FahrenheitToCelsius() {

        Quantity<TemperatureUnit> f =
                new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> c =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        assertEquals(f, c);
    }


    // Temperature Conversion

    @Test
    public void testTemperatureConversion_CelsiusToFahrenheit() {

        Quantity<TemperatureUnit> c =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                c.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT), result);
    }

    @Test
    public void testTemperatureConversion_FahrenheitToCelsius() {

        Quantity<TemperatureUnit> f =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> result =
                f.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(new Quantity<>(0.0, TemperatureUnit.CELSIUS), result);
    }


    // Unsupported Operations

    @Test
    public void testTemperatureUnsupportedOperation_Add() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class,
                () -> t1.add(t2));
    }

    @Test
    public void testTemperatureUnsupportedOperation_Subtract() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class,
                () -> t1.subtract(t2));
    }

    @Test
    public void testTemperatureUnsupportedOperation_Divide() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class,
                () -> t1.divide(t2));
    }


    // Cross Category Comparison

    @Test
    public void testTemperatureVsLengthComparison() {

        Quantity<TemperatureUnit> temp =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<LengthUnit> length =
                new Quantity<>(100.0, LengthUnit.FEET);

        assertNotEquals(temp, length);
    }

}