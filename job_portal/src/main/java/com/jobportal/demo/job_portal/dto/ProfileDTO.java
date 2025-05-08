package com.jobportal.demo.job_portal.dto;

import com.jobportal.demo.job_portal.entity.Profile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private Long id;

    @NotBlank(message = "Email cannot be blank")
    private String email;

//    @NotBlank(message = "Job title cannot be blank")
    private String jobTitle;

//    @NotBlank(message = "Company cannot be blank")
    private String company;

//    @NotBlank(message = "Company location cannot be blank")
    private String companyLocation;

    @Size(max = 1000, message = "About section cannot exceed 1000 characters")
    private String about;

    private List<String> skills;

    private List<Certificate> certificates; // Assuming you will create CertificateDTO

    private List<Experience> experiences; // Assuming you will create ExperienceDTO



    private Long userId; // Assuming you still want this field for user reference (optional)

    public Profile toEntity() {
        Profile profile = new Profile();
        profile.setId(this.id);
        profile.setCompany(this.company);  // Ensure this field is set
        profile.setEmail(this.email);
        profile.setJobTitle(this.jobTitle);
        profile.setCompanyLocation(this.companyLocation);
        profile.setAbout(this.about);
        profile.setSkills(this.skills);
        profile.setCertificates(this.certificates);
        profile.setExperiences(this.experiences);
        return profile;
    }

}