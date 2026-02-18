package com.example.demo.model;

import com.example.demo.enums.EnrollmentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Enrollment date cannot be null")
    @PastOrPresent(message = "Enrollment date cannot be in the future")
    private LocalDate enrollmentDate;

    @PastOrPresent(message = "Completion date cannot be in the future")
    private LocalDate completionDate;

    @NotNull(message = "Semester cannot be null")
    @Pattern(regexp = "^sem-[1-9]$", message = "Semester must follow the pattern: sem-1, sem-2, etc.")
    private String semester;

    @NotNull(message = "Enrollment status cannot be null")
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    @NotNull(message = "Student cannot be null")
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @NotNull(message = "Course cannot be null")
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
