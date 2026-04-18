package com.quantityapp.quantity.utils;

import com.quantityapp.quantity.dto.QuantityDTO;
import com.quantityapp.quantity.entity.Quantity;
import com.quantityapp.quantity.units.*;

public class QuantityConverter {

    public static Quantity<? extends IMeasurable> toEntity(QuantityDTO dto) {
        String unit = dto.getUnit();

        if (isLength(unit))      return new Quantity<>(dto.getValue(), LengthUnit.valueOf(unit));
        if (isWeight(unit))      return new Quantity<>(dto.getValue(), WeightUnit.valueOf(unit));
        if (isVolume(unit))      return new Quantity<>(dto.getValue(), VolumeUnit.valueOf(unit));
        if (isTemperature(unit)) return new Quantity<>(dto.getValue(), TemperatureUnit.valueOf(unit));

        throw new IllegalArgumentException("Unsupported unit: " + unit);
    }

    private static boolean isLength(String u) {
        try { LengthUnit.valueOf(u); return true; } catch (Exception e) { return false; }
    }
    private static boolean isWeight(String u) {
        try { WeightUnit.valueOf(u); return true; } catch (Exception e) { return false; }
    }
    private static boolean isVolume(String u) {
        try { VolumeUnit.valueOf(u); return true; } catch (Exception e) { return false; }
    }
    private static boolean isTemperature(String u) {
        try { TemperatureUnit.valueOf(u); return true; } catch (Exception e) { return false; }
    }
}
