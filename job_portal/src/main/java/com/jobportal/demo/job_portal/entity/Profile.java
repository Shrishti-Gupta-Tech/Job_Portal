package com.jobportal.demo.job_portal.entity;

import com.jobportal.demo.job_portal.dto.Certificate;
import com.jobportal.demo.job_portal.dto.Experience;
import com.jobportal.demo.job_portal.dto.ProfileDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "company", nullable = true)
    private String company;

    @Column(name = "company_location")
    private String companyLocation;

    @Column(columnDefinition = "TEXT")
    private String about;

    @ElementCollection
    @CollectionTable(name = "profile_skills", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "skill")
    private List<String> skills;


    @ElementCollection
    @CollectionTable(name = "profile_certificates", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "certificate")
    private List<Certificate> certificates;

    @ElementCollection
    @CollectionTable(name = "profile_experiences", joinColumns = @JoinColumn(name = "profile_id"))
    private List<Experience> experiences;


    public ProfileDTO toDTO() {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(this.id);
        dto.setEmail(this.email);
        dto.setJobTitle(this.jobTitle);
        dto.setCompany(this.company);
        dto.setCompanyLocation(this.companyLocation);
        dto.setAbout(this.about);
        dto.setSkills(this.skills);
        dto.setCertificates(this.certificates);
        dto.setExperiences(this.experiences);
        return dto;
    }
}
