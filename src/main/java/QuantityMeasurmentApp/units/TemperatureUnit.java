package QuantityMeasurmentApp.units;

import QuantityMeasurmentApp.exception.QuantityMeasurementException;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS {
        @Override
        public double convertToBaseUnit(double value) {
            return value;
        }

        @Override
        public double convertFromBaseUnit(double value) {
            return value;
        }
    },

    FAHRENHEIT {
        @Override
        public double convertToBaseUnit(double value) {
            return (value - 32) * 5 / 9;
        }

        @Override
        public double convertFromBaseUnit(double value) {
            return value * 9 / 5 + 32;
        }
    },

    KELVIN {
        @Override
        public double convertToBaseUnit(double value) {
            return value - 273.15;
        }

        @Override
        public double convertFromBaseUnit(double value) {
            return value + 273.15;
        }
    };

    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new QuantityMeasurementException("Temperature does not support " + operation + " operation");
    }
}