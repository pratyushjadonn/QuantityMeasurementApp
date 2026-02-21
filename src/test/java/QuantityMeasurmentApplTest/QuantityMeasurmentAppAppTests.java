package QuantityMeasurmentApplTest;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import QuantityMeasurmentApp.QuantityMeasurementApp.*;

class QuantityMeasurementAppTest {

	@Test
	public void testFeetEquality_SameValue() {
		Feet feet1 = new Feet(1.0);
		Feet feet2 = new Feet(1.0);

		assertTrue(feet1.equals(feet2));
	}

	@Test
	public void testFeetEquality_DifferentValue() {
		Feet feet1 = new Feet(1.0);
		Feet feet2 = new Feet(2.0);

		assertFalse(feet1.equals(feet2));
	}

	@Test
	public void testFeetEquality_NullComparison() {
		Feet feet = new Feet(1.0);

		assertFalse(feet.equals(null));
	}

	@Test
	public void testFeetEquality_DifferentClass() {
		Feet feet = new Feet(1.0);
		String str = "1.0";

		assertFalse(feet.equals(str));
	}

	@Test
	public void testFeetEquality_SameReference() {
		Feet feet = new Feet(1.0);
		assertTrue(feet.equals(feet));
	}
	 //UC2
		@Test
	    public void testInchesEquality_SameValue() {
	        Inches i1 = new Inches(10.0);
	        Inches i2 = new Inches(10.0);
	        assertEquals(i1, i2);
	    }

	    @Test
	    public void testInchesEquality_DifferentValue() {
	        Inches i1 = new Inches(10.0);
	        Inches i2 = new Inches(20.0);
	        assertNotEquals(i1, i2);
	    }

	    @Test
	    public void testInchesEquality_NullComparison() {
	        Inches i1 = new Inches(10.0);
	        assertNotEquals(i1, null);
	    }

	    @Test
	    public void testInchesEquality_DifferentClass() {
	        Inches i1 = new Inches(10.0);
	        Feet feet = new Feet(10.0);
	        assertNotEquals(i1, feet);
	    }

	    @Test
	    public void testInchesEquality_SameReference() {
	        Inches i1 = new Inches(10.0);
	        Inches i2 = i1;
	        assertEquals(i1, i2);
	    }
}