package com.quantityapp.quantity.repository;

import com.quantityapp.quantity.entity.QuantityMeasurementEntity;
import com.quantityapp.quantity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {
    List<QuantityMeasurementEntity> findByUser(User user);
    List<QuantityMeasurementEntity> findByUserAndOperationIgnoreCase(User user, String operation);
    void deleteAllByUser(User user);
}
