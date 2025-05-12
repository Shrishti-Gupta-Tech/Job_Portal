package com.jobportal.demo.job_portal.service;

import com.jobportal.demo.job_portal.dto.ProfileDTO;
import com.jobportal.demo.job_portal.exception.JobPortalException;

public interface ProfileService {
    public Long createProfile(String email);
    public ProfileDTO getProfile(Long id);
    public ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException;
}
