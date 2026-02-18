package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO
{
    @NotBlank(message = "Course code is required")
    @Pattern(
            regexp = "^[A-Z][a-zA-Z]*-\\d{3}$",
            message = "Course code must follow pattern: Java-000"
    )
    private String courseCode;

    @NotBlank(message = "Course name is required")
    private String courseName;

    private String description;



}
