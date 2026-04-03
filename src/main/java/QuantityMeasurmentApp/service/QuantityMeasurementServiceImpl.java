package QuantityMeasurmentApp.service;

import java.util.List;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import QuantityMeasurmentApp.dto.*;
import QuantityMeasurmentApp.entity.*;
import QuantityMeasurmentApp.repository.*;
import QuantityMeasurmentApp.units.IMeasurable;
import QuantityMeasurmentApp.utils.QuantityConverter;

@Service
public class QuantityMeasurementServiceImpl implements QuantityMeasurementService {

	private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementServiceImpl.class);

	private final QuantityMeasurementRepository repository;
	private final UserRepository userRepository;

	public QuantityMeasurementServiceImpl(QuantityMeasurementRepository repository, UserRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}

	private User getCurrentUser() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
	}

	private void saveHistory(QuantityMeasurementEntity entity) {
		entity.setUser(getCurrentUser());
		repository.save(entity);
	}

	private void validateSameType(Quantity<? extends IMeasurable> q1, Quantity<? extends IMeasurable> q2) {

		Class<?> c1 = (q1.getUnit() instanceof Enum<?> e1) ? e1.getDeclaringClass() : q1.getUnit().getClass();

		Class<?> c2 = (q2.getUnit() instanceof Enum<?> e2) ? e2.getDeclaringClass() : q2.getUnit().getClass();

		if (!c1.equals(c2)) {
			throw new IllegalArgumentException("Cannot operate on different measurement types");
		}
	}

	@SuppressWarnings("unchecked")
	private Quantity<IMeasurable> getOutputQuantity(QuantityInputDTO input) {
		if (input.getOutputUnit() == null || input.getOutputUnit().isBlank()) {
			return null;
		}

		QuantityDTO dto = new QuantityDTO();
		dto.setValue(1.0);
		dto.setUnit(input.getOutputUnit());
		dto.setMeasurementType(input.getThisQuantityDTO().getMeasurementType());

		return (Quantity<IMeasurable>) QuantityConverter.toEntity(dto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public QuantityMeasurementDTO add(QuantityInputDTO input) {
		try {
			Quantity<IMeasurable> q1 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThisQuantityDTO());
			Quantity<IMeasurable> q2 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThatQuantityDTO());

			validateSameType(q1, q2);

			Quantity<IMeasurable> outputQuantity = getOutputQuantity(input);
			double result;

			if (outputQuantity != null) {
				result = q1.add(q2, outputQuantity.getUnit()).getValue();
			} else {
				result = q1.add(q2, q1.getUnit()).getValue();
			}

			saveHistory(new QuantityMeasurementEntity("ADD", q1.getValue(), q2.getValue(), result));
			return new QuantityMeasurementDTO("ADD", result);

		} catch (Exception e) {
			logger.error("ADD failed: {}", e.getMessage());
			saveHistory(new QuantityMeasurementEntity("ADD", e.getMessage()));
			return new QuantityMeasurementDTO("ADD", e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public QuantityMeasurementDTO subtract(QuantityInputDTO input) {
		try {
			Quantity<IMeasurable> q1 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThisQuantityDTO());
			Quantity<IMeasurable> q2 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThatQuantityDTO());

			validateSameType(q1, q2);

			Quantity<IMeasurable> outputQuantity = getOutputQuantity(input);
			double result;

			if (outputQuantity != null) {
				result = q1.subtract(q2, outputQuantity.getUnit()).getValue();
			} else {
				result = q1.subtract(q2, q1.getUnit()).getValue();
			}

			saveHistory(new QuantityMeasurementEntity("SUBTRACT", q1.getValue(), q2.getValue(), result));
			return new QuantityMeasurementDTO("SUBTRACT", result);

		} catch (Exception e) {
			logger.error("SUBTRACT failed: {}", e.getMessage());
			saveHistory(new QuantityMeasurementEntity("SUBTRACT", e.getMessage()));
			return new QuantityMeasurementDTO("SUBTRACT", e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public QuantityMeasurementDTO divide(QuantityInputDTO input) {
		try {
			Quantity<IMeasurable> q1 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThisQuantityDTO());
			Quantity<IMeasurable> q2 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThatQuantityDTO());

			validateSameType(q1, q2);

			if (q2.getValue() == 0) {
				throw new ArithmeticException("Division by zero");
			}

			double result = q1.divide(q2);

			saveHistory(new QuantityMeasurementEntity("DIVIDE", q1.getValue(), q2.getValue(), result));

			QuantityMeasurementDTO response = new QuantityMeasurementDTO("DIVIDE", result);
			response.setMessage("Division completed successfully.");
			return response;

		} catch (Exception e) {
			logger.error("DIVIDE failed: {}", e.getMessage());
			saveHistory(new QuantityMeasurementEntity("DIVIDE", e.getMessage()));
			return new QuantityMeasurementDTO("DIVIDE", e.getMessage());
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public QuantityMeasurementDTO convert(QuantityInputDTO input) {
		try {
			Quantity<IMeasurable> q1 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThisQuantityDTO());
			Quantity<IMeasurable> target = (Quantity<IMeasurable>) QuantityConverter
					.toEntity(input.getThatQuantityDTO());

			validateSameType(q1, target);

			double result = q1.convertTo(target.getUnit()).getValue();

			saveHistory(new QuantityMeasurementEntity("CONVERT", q1.getValue(), 0, result));

			return new QuantityMeasurementDTO("CONVERT", result);

		} catch (Exception e) {
			logger.error("CONVERT failed: {}", e.getMessage());
			saveHistory(new QuantityMeasurementEntity("CONVERT", e.getMessage()));
			return new QuantityMeasurementDTO("CONVERT", e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public QuantityMeasurementDTO compare(QuantityInputDTO input) {
		try {
			Quantity<IMeasurable> q1 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThisQuantityDTO());
			Quantity<IMeasurable> q2 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThatQuantityDTO());

			validateSameType(q1, q2);

//			double firstInBase = q1.convertTo(q1.getUnit()).getValue();
//			double secondInFirstUnit = q2.convertTo(q1.getUnit()).getValue();
			double firstInBase = q1.getUnit().convertToBaseUnit(q1.getValue());
			double secondInFirstUnit = q2.getUnit().convertToBaseUnit(q2.getValue());

			double result;
			String message;

			if (firstInBase > secondInFirstUnit) {
				result = 1.0;
				message = "First quantity is greater.";
			} else if (firstInBase < secondInFirstUnit) {
				result = -1.0;
				message = "Second quantity is greater.";
			} else {
				result = 0.0;
				message = "Both quantities are equal.";
			}

			saveHistory(new QuantityMeasurementEntity("COMPARE", q1.getValue(), q2.getValue(), result));

			QuantityMeasurementDTO response = new QuantityMeasurementDTO("COMPARE", result);
			response.setMessage(message);
			return response;

		} catch (Exception e) {
			logger.error("COMPARE failed: {}", e.getMessage());
			saveHistory(new QuantityMeasurementEntity("COMPARE", e.getMessage()));
			return new QuantityMeasurementDTO("COMPARE", e.getMessage());
		}
	}
	@Override
	public List<?> getHistory() {
		return repository.findByUser(getCurrentUser())
				.stream()
				.sorted(Comparator.comparing(QuantityMeasurementEntity::getId).reversed())
				.toList();
	}

	@Override
	public List<?> getByOperation(String operation) {
		return repository.findByUserAndOperationIgnoreCase(getCurrentUser(), operation)
				.stream()
				.sorted(Comparator.comparing(QuantityMeasurementEntity::getId).reversed())
				.toList();
	}

	
	public void deleteAllHistory() {
		repository.deleteAllByUser(getCurrentUser());
	}
	
	public void deleteHistoryById(Long id) {
		QuantityMeasurementEntity entity = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("History item not found."));

		if (entity.getUser() == null || !entity.getUser().getId().equals(getCurrentUser().getId())) {
			throw new RuntimeException("You can delete only your own history.");
		}

		repository.delete(entity);
	}
}