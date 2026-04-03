package QuantityMeasurmentApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import QuantityMeasurmentApp.entity.QuantityMeasurementEntity;
import QuantityMeasurmentApp.entity.User;

@Repository
public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {

    // FIX: yeh teen methods service mein use ho rahe the par repository mein the hi nahi
    List<QuantityMeasurementEntity> findByUser(User user);

    List<QuantityMeasurementEntity> findByUserAndOperationIgnoreCase(User user, String operation);

    void deleteAllByUser(User user);

    List<QuantityMeasurementEntity> findByOperation(String operation);
}