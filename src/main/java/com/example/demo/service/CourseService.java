package com.example.demo.service;

import com.example.demo.dto.request.CourseRequestDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.enums.CourseStatus;
import jakarta.validation.Valid;

import java.util.List;

public interface CourseService {
    ApiResponse<CourseResponseDTO> createCourseInfo(CourseRequestDTO courseRequestDTO);

    ApiResponse<List<CourseResponseDTO>> getCourses();

    ApiResponse<List<CourseResponseDTO>> getCourseByStatus(CourseStatus status);

    ApiResponse<CourseResponseDTO> updateCourseInfo(Long id, @Valid CourseRequestDTO courseRequestDTO);

    ApiResponse<CourseResponseDTO> changeStatusById(Long id,CourseStatus targetStatus);
}
