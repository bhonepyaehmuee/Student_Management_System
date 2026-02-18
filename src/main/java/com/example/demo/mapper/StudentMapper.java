package com.example.demo.mapper;

import com.example.demo.dto.request.StudentRequestDTO;
import com.example.demo.dto.response.StudentResponseDTO;
import com.example.demo.enums.RoleStatus;
import com.example.demo.enums.StudentStatus;
import com.example.demo.model.Role;
import com.example.demo.model.Student;
import org.springframework.stereotype.Component;

import java.util.Set;


public class StudentMapper {

    //from RequestDTo to Entity
    public  static  Student mapToEntity(StudentRequestDTO studentRequestDTO)
    {
        Student student = new Student();

        student.setName(studentRequestDTO.getName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setUsername(studentRequestDTO.getUsername());
//        need to do the password explicitly
        student.setPassword(studentRequestDTO.getPassword());
        student.setPhoneNumber(studentRequestDTO.getPhoneNumber());
        student.setAddress(studentRequestDTO.getAddress());
        student.setDateOfBirth(studentRequestDTO.getDateOfBirth());
        student.setStudentRegNumber(studentRequestDTO.getStudentRegNumber());
//        Set<Role>
//        student.setRoles(studentRequestDTO.getRoleIds());
        return  student;
    }

    //from Entity to ResponseDTO
    public static StudentResponseDTO mapToDTO(Student student)
    {
        return StudentResponseDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .username(student.getUsername())
                .phoneNumber(student.getPhoneNumber())
                .address(student.getAddress())
                .dateOfBirth(student.getDateOfBirth())
                .createdAt(student.getCreatedAt())
                .updatedAt(student.getUpdatedAt())
                .createdBy(student.getCreatedBy())
                .updatedBy(student.getUpdatedBy())
                .build();

//        responseDTO.setId(student.getId());
//
//        responseDTO.setStatus(student.getStatus());
////        responseDTO.setRole(student.getR());
////        responseDTO.setActive(studentb .getActive());
//
//        responseDTO.setCreatedAt(student.getCreatedAt());
//        responseDTO.setUpdatedAt(student.getUpdatedAt());
//        responseDTO.setCreatedBy(student.getCreatedBy());
//        responseDTO.setUpdatedBy(student.getUpdatedBy());
//
//        responseDTO.setName(student.getName());
//        responseDTO.setEmail(student.getEmail());
//        responseDTO.setUsername(student.getUsername());
//        responseDTO.setPhoneNumber(student.getPhoneNumber());
//        responseDTO.setAddress(student.getAddress());
//        responseDTO.setDateOfBirth(student.getDateOfBirth());
//        responseDTO.setStudentRegNumber(student.getStudentRegNumber());
//        return responseDTO;
    }
}
