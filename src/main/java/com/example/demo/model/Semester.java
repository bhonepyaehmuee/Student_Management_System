package com.example.demo.model;

import com.example.demo.enums.SemesterStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "semester")
@AllArgsConstructor
@NoArgsConstructor
public class Semester extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private  LocalDate endDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SemesterStatus status;
}
