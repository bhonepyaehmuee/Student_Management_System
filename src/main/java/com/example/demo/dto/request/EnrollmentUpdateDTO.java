package com.example.demo.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EnrollmentUpdateDTO {

    private LocalDate completionDate;

    private String semester;
}
