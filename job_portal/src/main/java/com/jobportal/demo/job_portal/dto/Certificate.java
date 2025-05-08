package com.jobportal.demo.job_portal.dto;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Certificate {

    private Long id;

//    @NotBlank(message = "Certificate name cannot be blank")
    private String certificateName;

//    @NotBlank(message = "Issuing organization cannot be blank")
    private String issuingOrganization;

//    @NotNull(message = "Issue date cannot be null")
    private LocalDate issueDate;

//    @NotBlank(message = "Description cannot be blank")
    private String description;

    private String CertificateId;
}
