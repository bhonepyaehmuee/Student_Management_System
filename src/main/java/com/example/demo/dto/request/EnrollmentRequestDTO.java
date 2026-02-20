package com.example.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EnrollmentRequestDTO {

    @NotNull(message = "Enrollment date cannot be null")
    private LocalDate enrollmentDate;

    private LocalDate completionDate;

    private String semester;


    @NotNull(message = "Student ID cannot be null")
    private Long studentId;

    @NotNull(message = "Course ID can't be null")
    private Long courseId;
}
