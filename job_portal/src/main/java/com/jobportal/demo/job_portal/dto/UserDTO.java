package com.jobportal.demo.job_portal.dto;

import com.jobportal.demo.job_portal.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private Long id;

    @NotBlank(message = "{userDTO.userName.notBlank}")
    private String userName;
    @NotBlank(message = "{userDTO.password.notBlank}")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "{userDTO.password.pattern}"
    )
    private String password;
    @NotBlank(message = "{userDTO.email.notBlank}")
    @Email(message = "{userDTO.email.invalid}")
    private String email;
    @NotBlank(message = "{userDTO.phoneNumber.notBlank}")
    private String phoneNumber;
    private Role role;

    private String jobTitle;
    private String company;
    private String companyLocation;
    private String about;
    private List<String> skills;
    private List<Certificate> certificates;
    private List<Experience> experiences;

    private Long profileID;

    public User toEntity()  {
        return new User(
                this.id, this.userName, this.password, this.email, this.phoneNumber,
                this.role, this.profileID, this.jobTitle, this.company, this.companyLocation,
                this.about, this.skills, this.certificates, this.experiences
        );
    }
}

