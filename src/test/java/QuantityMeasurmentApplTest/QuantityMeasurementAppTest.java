package QuantityMeasurmentApplTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import QuantityMeasurmentApp.QuantityWeight;
import QuantityMeasurmentApp.WeightUnit;
class QuantityMeasurementAppTest {

    @Test
    public void testKgEqualsGram() {

        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertEquals(kg, g);
    }

    @Test
    public void testKgEqualsPound() {

        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight pound = new QuantityWeight(2.20462, WeightUnit.POUND);

        assertEquals(kg, pound);
    }

    @Test
    public void testConversionKgToGram() {

        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight g = kg.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, g.getValue(), 0.001);
    }

    @Test
    public void testConversionGramToPound() {

        QuantityWeight g = new QuantityWeight(453.592, WeightUnit.GRAM);

        QuantityWeight pound = g.convertTo(WeightUnit.POUND);

        assertEquals(1.0, pound.getValue(), 0.001);
    }

    @Test
    public void testAdditionKgAndGram() {

        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = kg.add(g);

        assertEquals(2.0, result.getValue(), 0.001);
    }

    @Test
    public void testAdditionExplicitUnit() {

        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = kg.add(g, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), 0.001);
    }

    @Test
    public void testZeroWeight() {

        QuantityWeight kg = new QuantityWeight(0.0, WeightUnit.KILOGRAM);

        QuantityWeight g = kg.convertTo(WeightUnit.GRAM);

        assertEquals(0.0, g.getValue());
    }

    @Test
    public void testNegativeWeight() {

        QuantityWeight kg = new QuantityWeight(-1.0, WeightUnit.KILOGRAM);

        QuantityWeight g = new QuantityWeight(-1000.0, WeightUnit.GRAM);

        assertEquals(kg, g);
    }
}