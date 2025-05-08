package com.jobportal.demo.job_portal.repository;

import com.jobportal.demo.job_portal.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OTPRepository extends JpaRepository<OTP, String> {

    Optional<OTP> findByEmail(String email);

    List<OTP> findByOtpCreationTimeBefore(LocalDateTime expiryTime);
}

