package com.example.demo.service;

import com.example.demo.dto.request.EnrollmentRequestDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.EnrollmentResponseDTO;
import com.example.demo.enums.EnrollmentStatus;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EnrollmentService {
    ApiResponse<EnrollmentResponseDTO> createEnrollment(@Valid EnrollmentRequestDTO enrollmentRequestDTO);

    ApiResponse<List<EnrollmentResponseDTO>> getEnrollments();

    ApiResponse<List<EnrollmentResponseDTO>> getEnrollmentsByStatus(EnrollmentStatus status);

    ApiResponse<EnrollmentResponseDTO> updateEnrollmentsById(Long id, EnrollmentRequestDTO enrollmentRequestDTO);
}
