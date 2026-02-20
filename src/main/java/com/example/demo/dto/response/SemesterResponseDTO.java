package com.example.demo.dto.response;

import com.example.demo.enums.SemesterStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

public class SemesterResponseDTO {
    private Long id;

    private String name;
    private String description;
    private LocalDate startDate;
    private  LocalDate endDate;
    private SemesterStatus status;
}
