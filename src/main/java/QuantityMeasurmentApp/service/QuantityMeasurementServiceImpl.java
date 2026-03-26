package QuantityMeasurmentApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import QuantityMeasurmentApp.dto.QuantityInputDTO;
import QuantityMeasurmentApp.dto.QuantityMeasurementDTO;
import QuantityMeasurmentApp.entity.Quantity;
import QuantityMeasurmentApp.entity.QuantityMeasurementEntity;
import QuantityMeasurmentApp.repository.QuantityMeasurementRepository;
import QuantityMeasurmentApp.units.IMeasurable;
import QuantityMeasurmentApp.utils.QuantityConverter;

@Service
public class QuantityMeasurementServiceImpl implements QuantityMeasurementService {

    private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementServiceImpl.class);

    @Autowired
    private QuantityMeasurementRepository repository;

    private void validateSameType(Quantity<? extends IMeasurable> q1, Quantity<? extends IMeasurable> q2) {
        if (!q1.getUnit().getClass().equals(q2.getUnit().getClass())) {
            throw new IllegalArgumentException("Cannot operate on different measurement types");
        }
    }

    @Override
    public QuantityMeasurementDTO add(QuantityInputDTO input) {
        try {
            Quantity<IMeasurable> q1 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThisQuantityDTO());
            Quantity<IMeasurable> q2 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThatQuantityDTO());

            validateSameType(q1, q2);

            double result = q1.add(q2, q1.getUnit()).getValue();

            repository.save(new QuantityMeasurementEntity("ADD", q1.getValue(), q2.getValue(), result));

            return new QuantityMeasurementDTO("ADD", result);

        } catch (Exception e) {
            logger.error("ADD failed: {}", e.getMessage());
            repository.save(new QuantityMeasurementEntity("ADD", e.getMessage()));
            return new QuantityMeasurementDTO("ADD", e.getMessage());
        }
    }

    @Override
    public QuantityMeasurementDTO subtract(QuantityInputDTO input) {
        try {
            Quantity<IMeasurable> q1 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThisQuantityDTO());
            Quantity<IMeasurable> q2 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThatQuantityDTO());

            validateSameType(q1, q2);

            double result = q1.subtract(q2, q1.getUnit()).getValue();

            repository.save(new QuantityMeasurementEntity("SUBTRACT", q1.getValue(), q2.getValue(), result));

            return new QuantityMeasurementDTO("SUBTRACT", result);

        } catch (Exception e) {
            logger.error("SUBTRACT failed: {}", e.getMessage());
            repository.save(new QuantityMeasurementEntity("SUBTRACT", e.getMessage()));
            return new QuantityMeasurementDTO("SUBTRACT", e.getMessage());
        }
    }

    @Override
    public Double divide(QuantityInputDTO input) {
        try {
            Quantity<IMeasurable> q1 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThisQuantityDTO());
            Quantity<IMeasurable> q2 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThatQuantityDTO());

            validateSameType(q1, q2);

            if (q2.getValue() == 0)
                throw new ArithmeticException("Division by zero");

            double result = q1.divide(q2);

            repository.save(new QuantityMeasurementEntity("DIVIDE", q1.getValue(), q2.getValue(), result));

            return result;

        } catch (Exception e) {
            logger.error("DIVIDE failed: {}", e.getMessage());
            repository.save(new QuantityMeasurementEntity("DIVIDE", e.getMessage()));
            throw e;
        }
    }

    @Override
    public QuantityMeasurementDTO convert(QuantityInputDTO input) {
        try {
            Quantity<IMeasurable> q1 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThisQuantityDTO());
            Quantity<IMeasurable> target = (Quantity<IMeasurable>) QuantityConverter
                    .toEntity(input.getThatQuantityDTO());

            validateSameType(q1, target);

            double result = q1.convertTo(target.getUnit()).getValue();

            repository.save(new QuantityMeasurementEntity("CONVERT", q1.getValue(), 0, result));

            return new QuantityMeasurementDTO("CONVERT", result);

        } catch (Exception e) {
            logger.error("CONVERT failed: {}", e.getMessage());
            return new QuantityMeasurementDTO("CONVERT", e.getMessage());
        }
    }

    @Override
    public QuantityMeasurementDTO compare(QuantityInputDTO input) {
        try {
            Quantity<IMeasurable> q1 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThisQuantityDTO());
            Quantity<IMeasurable> q2 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThatQuantityDTO());

            validateSameType(q1, q2);

            boolean result = q1.equals(q2);

            repository.save(new QuantityMeasurementEntity("COMPARE", q1.getValue(), q2.getValue(), result ? 1 : 0));

            return new QuantityMeasurementDTO("COMPARE", result ? 1.0 : 0.0);

        } catch (Exception e) {
            logger.error("COMPARE failed: {}", e.getMessage());
            return new QuantityMeasurementDTO("COMPARE", e.getMessage());
        }
    }

    @Override
    public List<?> getHistory() {
        return repository.findAll();
    }

    @Override
    public List<?> getByOperation(String operation) {
        return repository.findByOperation(operation);
    }
}