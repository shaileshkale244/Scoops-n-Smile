package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Order;
import com.app.entities.Otp;

public interface OtpRepository extends JpaRepository<Otp, Long> {
Optional<Otp> findByOrder(Order order);
}
