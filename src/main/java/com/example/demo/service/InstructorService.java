package com.example.demo.service;

import com.example.demo.dto.request.InstructorRequestDTO;
import com.example.demo.dto.request.InstructorStatusRequestDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.InstructorResponseDTO;
import com.example.demo.enums.InstructorStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface InstructorService {
    ApiResponse<InstructorResponseDTO> createInstructor(InstructorRequestDTO instructorRequestDTO);

    ApiResponse<List<InstructorResponseDTO>> getAllInstructor();

    ApiResponse<List<InstructorResponseDTO>> getInstructorByStatus(@PathVariable InstructorStatus status);

    ApiResponse<InstructorResponseDTO> updateInstructorInfo(Long id, InstructorRequestDTO instructorRequestDTO);

    ApiResponse<InstructorResponseDTO> changeInstructorStatus(Long id, InstructorStatus instructorStatus);

//    ApiResponse<InstructorResponseDTO> changeInstructorToOn_Leave(Long id);

}
