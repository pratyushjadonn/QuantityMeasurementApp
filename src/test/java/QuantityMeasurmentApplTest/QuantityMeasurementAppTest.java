package QuantityMeasurmentApplTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import QuantityMeasurmentApp.LengthUnit;
import QuantityMeasurmentApp.Quantity;
import QuantityMeasurmentApp.VolumeUnit;
import QuantityMeasurmentApp.WeightUnit;
class QuantityMeasurementAppTest {
	 @Test
	    public void testVolumeEquality_LitreToLitre() {

	        Quantity<VolumeUnit> v1 =
	                new Quantity<>(1.0, VolumeUnit.LITRE);

	        Quantity<VolumeUnit> v2 =
	                new Quantity<>(1.0, VolumeUnit.LITRE);

	        assertEquals(v1, v2);
	    }


	    @Test
	    public void testVolumeEquality_LitreToMillilitre() {

	        Quantity<VolumeUnit> litre =
	                new Quantity<>(1.0, VolumeUnit.LITRE);

	        Quantity<VolumeUnit> ml =
	                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

	        assertEquals(litre, ml);
	    }


	    @Test
	    public void testVolumeEquality_GallonToLitre() {

	        Quantity<VolumeUnit> gallon =
	                new Quantity<>(1.0, VolumeUnit.GALLON);

	        Quantity<VolumeUnit> litre =
	                new Quantity<>(3.78541, VolumeUnit.LITRE);

	        assertEquals(gallon, litre);
	    }


	    @Test
	    public void testVolumeConversion_LitreToMillilitre() {

	        Quantity<VolumeUnit> litre =
	                new Quantity<>(1.0, VolumeUnit.LITRE);

	        Quantity<VolumeUnit> result =
	                litre.convertTo(VolumeUnit.MILLILITRE);

	        assertEquals(
	                new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
	                result
	        );
	    }

	    @Test
	    public void testVolumeAddition_LitrePlusMillilitre() {

	        Quantity<VolumeUnit> litre =
	                new Quantity<>(1.0, VolumeUnit.LITRE);

	        Quantity<VolumeUnit> ml =
	                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

	        Quantity<VolumeUnit> result =
	                litre.add(ml);

	        assertEquals(
	                new Quantity<>(2.0, VolumeUnit.LITRE),
	                result
	        );
	    }


	    @Test
	    public void testVolumeAddition_WithExplicitUnit() {

	        Quantity<VolumeUnit> litre =
	                new Quantity<>(1.0, VolumeUnit.LITRE);

	        Quantity<VolumeUnit> gallon =
	                new Quantity<>(1.0, VolumeUnit.GALLON);

	        Quantity<VolumeUnit> result =
	                litre.add(gallon, VolumeUnit.MILLILITRE);

	        assertEquals(
	                new Quantity<>(4785.41, VolumeUnit.MILLILITRE),
	                result
	        );
	    }


	    @Test
	    public void testVolumeZeroEquality() {

	        Quantity<VolumeUnit> v1 =
	                new Quantity<>(0.0, VolumeUnit.LITRE);

	        Quantity<VolumeUnit> v2 =
	                new Quantity<>(0.0, VolumeUnit.MILLILITRE);

	        assertEquals(v1, v2);
	    }


	    @Test
	    public void testVolumeNegativeValues() {

	        Quantity<VolumeUnit> v1 =
	                new Quantity<>(-1.0, VolumeUnit.LITRE);

	        Quantity<VolumeUnit> v2 =
	                new Quantity<>(-1000.0, VolumeUnit.MILLILITRE);

	        assertEquals(v1, v2);
	    }


	    @Test
	    public void testVolumeVsLength_NotEqual() {

	        Quantity<VolumeUnit> volume =
	                new Quantity<>(1.0, VolumeUnit.LITRE);

	        Quantity<LengthUnit> length =
	                new Quantity<>(1.0, LengthUnit.FEET);

	        assertNotEquals(volume, length);
	    }


	    @Test
	    public void testVolumeVsWeight_NotEqual() {

	        Quantity<VolumeUnit> volume =
	                new Quantity<>(1.0, VolumeUnit.LITRE);

	        Quantity<WeightUnit> weight =
	                new Quantity<>(1.0, WeightUnit.KILOGRAM);

	        assertNotEquals(volume, weight);
	    }
}