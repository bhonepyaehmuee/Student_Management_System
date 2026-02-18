package com.example.demo.mapper;

import com.example.demo.dto.request.InstructorRequestDTO;
import com.example.demo.dto.response.InstructorResponseDTO;
import com.example.demo.model.Instructor;

public class InstructorMapper {
    public static Instructor mapToEntity(InstructorRequestDTO instructorRequestDTO)
    {
      return  Instructor.builder()
              .name(instructorRequestDTO.getName())
              .email(instructorRequestDTO.getEmail())
              .dateOfBirth(instructorRequestDTO.getDateOfBirth())
              .username(instructorRequestDTO.getUsername())
              .password(instructorRequestDTO.getPassword())
              .phoneNumber(instructorRequestDTO.getPhoneNumber())
              .address(instructorRequestDTO.getAddress())
              .instructorCode(instructorRequestDTO.getInstructorCode())
              .build();
    }

    public static InstructorResponseDTO mapToDTO(Instructor instructor)
    {
        return InstructorResponseDTO.builder()
                .id(instructor.getId())
                .name(instructor.getName())
                .email(instructor.getEmail())
                .username(instructor.getUsername())
                .phoneNumber(instructor.getPhoneNumber())
                .address(instructor.getAddress())
                .dateOfBirth(instructor.getDateOfBirth())
                .instructorCode(instructor.getInstructorCode())
                .instructorStatus(instructor.getStatus())
                .createdAt(instructor.getCreatedAt())
                .updatedAt(instructor.getUpdatedAt())
                .createdBy(instructor.getCreatedBy())
                .updatedBy(instructor.getUpdatedBy())
                .build();
    }
}
