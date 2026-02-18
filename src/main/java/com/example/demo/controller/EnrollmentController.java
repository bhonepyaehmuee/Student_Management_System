package com.example.demo.controller;

import com.example.demo.dto.request.EnrollmentRequestDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.EnrollmentResponseDTO;
import com.example.demo.enums.EnrollmentStatus;
import com.example.demo.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/enrollment")
@Tag(name="Enrollment API", description = "Enrollment is the mid class between student and course!")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @Operation(summary = "create the enrollment")
    @PostMapping("/")
    public ResponseEntity<ApiResponse<EnrollmentResponseDTO>> createEnrollment(@Valid @RequestBody EnrollmentRequestDTO enrollmentRequestDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.createEnrollment(enrollmentRequestDTO));
    }

    @Operation(summary = "Retrieve all enrollments")
    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<EnrollmentResponseDTO>>> getEnrollment()
    {
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.getEnrollments());
    }

    @Operation(summary = "Retrieve enrollments by status")
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<EnrollmentResponseDTO>>> getEnrollmentByStatus(@PathVariable EnrollmentStatus status)
    {
        return ResponseEntity.ok().body(enrollmentService.getEnrollmentsByStatus(status));
    }

    @Operation(summary ="Update Enrollments")
    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<EnrollmentResponseDTO>> updateEnrollmentById(@PathVariable Long id, @RequestBody EnrollmentRequestDTO enrollmentRequestDTO)
    {
        return  ResponseEntity.ok().body(enrollmentService.updateEnrollmentsById(id,enrollmentRequestDTO));
    }

}
