package com.jobportal.demo.job_portal.repository;

import com.jobportal.demo.job_portal.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
