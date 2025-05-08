package com.jobportal.demo.job_portal.service;


import com.jobportal.demo.job_portal.entity.Profile;
import com.jobportal.demo.job_portal.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service( "profileService")
public class ProfileImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Long createProfile(String email) {
        Profile profile = new Profile();
        profile.setEmail(email);
        profile.setSkills(new ArrayList<>());
        profile.setExperiences(new ArrayList<>());
        profile.setCertificates(new ArrayList<>());
        profileRepository.save(profile);
        return profile.getId();
    }
}
