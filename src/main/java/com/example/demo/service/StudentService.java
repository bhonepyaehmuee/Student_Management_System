package com.example.demo.service;

import com.example.demo.dto.request.StudentRequestDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.StudentResponseDTO;
import com.example.demo.enums.StudentStatus;

import java.util.List;


public interface StudentService {
    public ApiResponse<StudentResponseDTO> createStudent(StudentRequestDTO requestDTO);
    public   ApiResponse<StudentResponseDTO> updateStudentById(Long studentId, StudentRequestDTO studentRequestDTO);
    public  ApiResponse<StudentResponseDTO> deleteStudent(Long id);
    public ApiResponse<List<StudentResponseDTO>> getAllStudents();
    public ApiResponse<List<StudentResponseDTO>> getAllActiveStudents(StudentStatus status);

    ApiResponse<StudentResponseDTO> changeStudentStatus(Long id);
}
