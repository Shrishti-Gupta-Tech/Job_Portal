package com.jobportal.demo.job_portal.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experience {

    
    @Column
    private String title;

    @Column
    private String company;

//    @Column(nullable = false)
    private String location;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "working")
    private boolean working;

    @Column(columnDefinition = "TEXT")
    private String description;
}
