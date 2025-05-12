package com.jobportal.demo.job_portal.service;


import com.jobportal.demo.job_portal.dto.ProfileDTO;
import com.jobportal.demo.job_portal.entity.Profile;
import com.jobportal.demo.job_portal.exception.JobPortalException;
import com.jobportal.demo.job_portal.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service( "profileService")
public class ProfileImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Long createProfile(String email) {
        Profile profile = new Profile();
        profile.setEmail(email);
        profile.setJobTitle("");
        profile.setCompany("");
        profile.setCompanyLocation("");
        profile.setAbout("");
        profile.setSkills(new ArrayList<>());
        profile.setExperiences(new ArrayList<>());
        profile.setCertificates(new ArrayList<>());
        profileRepository.save(profile);
        return profile.getId();
    }

    @Override
    public ProfileDTO getProfile(Long id) {
        Optional<Profile> profileOptional = profileRepository.findById(id);
        if (profileOptional.isPresent()) {
            return profileOptional.get().toDTO();
        }
        // Or throw an exception, e.g.,
        // throw new ResourceNotFoundException("Profile not found with id: " + id);
        return null; // Or handle as per your application's requirements
    }

    @Override
    public ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException {
        if (profileDTO.getId() == null) {
            // Handle error: ID is required for an update
            // e.g., throw new IllegalArgumentException("Profile ID cannot be null for update");
            return null;
        }
        Optional<Profile> existingProfileOptional = profileRepository.findById(profileDTO.getId());
        if (existingProfileOptional.isPresent()) {
            Profile existingProfile = existingProfileOptional.get();
            // Update fields of existingProfile from profileDTO
            // For example, if you want to allow changing email:
            // existingProfile.setEmail(profileDTO.getEmail());
            // Be careful with what fields are updatable
            //existingProfile.setJobTitle(profileDTO.getJobTitle());
            //existingProfile.setCompany(profileDTO.getCompany());
            //existingProfile.setCompanyLocation(profileDTO.getCompanyLocation());
            //existingProfile.setAbout(profileDTO.getAbout());
            //existingProfile.setSkills(profileDTO.getSkills());
            // For collections like certificates and experiences, you'll need to decide
            // on the update strategy (replace all, or manage individual items).
            // This example assumes replacement.
            // Ensure ProfileDTO.certificates and ProfileDTO.experiences are of the correct type
            // (ideally DTOs themselves, then converted to entities).
            // If ProfileDTO holds entity types directly (as it seems to do), you might assign directly,
            // but this is generally not recommended.
            //existingProfile.setCertificates(profileDTO.getCertificates()); // This assumes ProfileDTO.certificates are List<Certificate> (entity type)
            // existingProfile.setExperiences(profileDTO.getExperiences()); // This assumes ProfileDTO.experiences are List<Experience> (entity type)

            Profile updatedProfile = profileRepository.save(existingProfile);
            return updatedProfile.toDTO();
        } else {
            // Handle profile not found, e.g.,
             throw new JobPortalException("Profile not found with id: " + profileDTO.getId());
        }
    }
}