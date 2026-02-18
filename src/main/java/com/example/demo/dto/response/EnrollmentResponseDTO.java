package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
//@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponseDTO extends AuditableResponseDTO {
    private Long id;
    private LocalDate enrollmentDate;
    private LocalDate completionDate;
    private String semester;

    private Long studentId;
    private String studentName;

    private Long courseId;
    private String courseCode;
    private String courseName;
}
