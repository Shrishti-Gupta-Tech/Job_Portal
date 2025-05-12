package com.jobportal.demo.job_portal.entity;

import com.jobportal.demo.job_portal.dto.Certificate;
import com.jobportal.demo.job_portal.dto.Experience;
import com.jobportal.demo.job_portal.dto.Role;
import com.jobportal.demo.job_portal.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Long profileID;

    private String jobTitle;
    private String company;
    private String companyLocation;
    private String about;

    @ElementCollection
    private List<String> skills;

    @ElementCollection
    private List<Certificate> certificates;

    @ElementCollection
    private List<Experience> experiences;

    public UserDTO toDTO()  {
        return new UserDTO(
                this.id, this.userName, this.password, this.email, this.phoneNumber, this.role,
                this.jobTitle, this.company, this.companyLocation, this.about, this.skills,
                this.certificates, this.experiences, this.profileID
        );
    }
}

