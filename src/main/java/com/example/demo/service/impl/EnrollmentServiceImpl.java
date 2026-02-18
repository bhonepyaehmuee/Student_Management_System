package com.example.demo.service.impl;

import com.example.demo.dto.request.EnrollmentRequestDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.dto.response.EnrollmentResponseDTO;
import com.example.demo.dto.response.InstructorResponseDTO;
import com.example.demo.enums.EnrollmentStatus;
import com.example.demo.mapper.EnrollmentMapper;
import com.example.demo.model.Course;
import com.example.demo.model.Enrollment;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepo;
import com.example.demo.repository.EnrollmentRepo;
import com.example.demo.repository.StudentRepo;
import com.example.demo.service.EnrollmentService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {
    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;
    private final EnrollmentMapper enrollmentMapper;

    private final EnrollmentRepo enrollmentRepo;


    public EnrollmentServiceImpl(EnrollmentRepo enrollmentRepo, EnrollmentMapper enrollmentMapper,
                                 StudentRepo studentRepo,
                                 CourseRepo courseRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.enrollmentMapper = enrollmentMapper;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public ApiResponse<EnrollmentResponseDTO> createEnrollment(EnrollmentRequestDTO enrollmentRequestDTO) {
        Student student = studentRepo.findById(enrollmentRequestDTO.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Course course = courseRepo.findById(enrollmentRequestDTO.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        if (enrollmentRequestDTO.getCompletionDate() != null &&
                enrollmentRequestDTO.getCompletionDate().isBefore(enrollmentRequestDTO.getEnrollmentDate())) {
            throw new IllegalArgumentException("Completion date cannot be before enrollment date");
        }

        Enrollment enrollment = enrollmentMapper.toEntity(enrollmentRequestDTO);
        enrollment.setStatus(EnrollmentStatus.ENROLLED);
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        Enrollment savedEnrollment = enrollmentRepo.save(enrollment);

        EnrollmentResponseDTO enrollmentResponseDTO = enrollmentMapper.toDTO(savedEnrollment);
        return ApiResponse.<EnrollmentResponseDTO>builder()
                .success(true)
                .message("Created Successfully")
                .data(enrollmentResponseDTO)
                .build();
    }

    @Override
    public ApiResponse<List<EnrollmentResponseDTO>> getEnrollments() {
        List<EnrollmentResponseDTO> enrollmentResponseDTOS = enrollmentRepo.findAll().stream().map(enrollmentMapper::toDTO).toList();
        return ApiResponse.<List<EnrollmentResponseDTO>>builder()
                .success(true)
                .message("Retrieve all Enrollments")
                .data(enrollmentResponseDTOS)
                .build();
    }

    @Override
    public ApiResponse<List<EnrollmentResponseDTO>> getEnrollmentsByStatus(EnrollmentStatus status) {
        List<EnrollmentResponseDTO> enrollmentResponseDTOS =
                enrollmentRepo.findByStatus(status).stream().map(enrollmentMapper::toDTO).toList();
        log.info("Fetched {} courses with status: {}", enrollmentResponseDTOS.size(), status);

        String message = enrollmentResponseDTOS.isEmpty() ? "No courses found with status: " + status : "Fetched courses with status: " + status;
        return ApiResponse.<List<EnrollmentResponseDTO>>builder()
                .success(true)
                .message(message)
                .data(enrollmentResponseDTOS)
                .build();
    }

    @Override
    public ApiResponse<EnrollmentResponseDTO> updateEnrollmentsById(Long id, EnrollmentRequestDTO enrollmentRequestDTO) {
        Enrollment existedEnrollment = enrollmentRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Not found with that id " + id));
        if (enrollmentRequestDTO.getEnrollmentDate() != null) {
            existedEnrollment.setEnrollmentDate(enrollmentRequestDTO.getEnrollmentDate());
        }
        if (enrollmentRequestDTO.getCompletionDate() != null) {
            existedEnrollment.setCompletionDate(enrollmentRequestDTO.getCompletionDate());
        }
        if (enrollmentRequestDTO.getSemester() != null) {
            existedEnrollment.setSemester(enrollmentRequestDTO.getSemester());
        }
        if (enrollmentRequestDTO.getStudentId() != null) {
            Student student = studentRepo.findById(enrollmentRequestDTO.getStudentId())
                    .orElseThrow(() -> new IllegalArgumentException("Student is not found"));
            existedEnrollment.setStudent(student);
        }
        if(enrollmentRequestDTO.getCourseId() != null)
        {
            Course course = courseRepo.findById(enrollmentRequestDTO.getCourseId())
                    .orElseThrow(()-> new IllegalArgumentException("Course is not found"));
            existedEnrollment.setCourse(course);
        }
        Enrollment updatedEnrollment = enrollmentRepo.save(existedEnrollment);
        EnrollmentResponseDTO responseDTO = enrollmentMapper.toDTO(updatedEnrollment);
        return ApiResponse.<EnrollmentResponseDTO>builder()
                .success(true)
                .message("Enrollment updated successfully")
                .data(responseDTO)
                .build();
    }
}
