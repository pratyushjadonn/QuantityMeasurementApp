package QuantityMeasurmentApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import QuantityMeasurmentApp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}