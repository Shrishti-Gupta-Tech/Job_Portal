package com.jobportal.demo.job_portal.controller;

import com.jobportal.demo.job_portal.dto.ProfileDTO;
import com.jobportal.demo.job_portal.exception.JobPortalException;
import com.jobportal.demo.job_portal.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/profile")
public class ProfileAPI {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Long id) throws JobPortalException {

        return new ResponseEntity<>(profileService.getProfile(id),HttpStatus.OK);
    }
}