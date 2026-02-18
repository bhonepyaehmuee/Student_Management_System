package com.example.demo.mapper;

import com.example.demo.dto.request.EnrollmentRequestDTO;
import com.example.demo.dto.response.EnrollmentResponseDTO;
import com.example.demo.model.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @org.mapstruct.Builder(disableBuilder = true))
public interface EnrollmentMapper {
  @Mapping(source = "student.id", target = "studentId")
  @Mapping(source = "student.name", target = "studentName")
  @Mapping(source = "course.id", target = "courseId")
  @Mapping(source = "course.courseName", target = "courseName")
  @Mapping(source = "course.courseCode", target = "courseCode")
  EnrollmentResponseDTO toDTO(Enrollment enrollment);
  Enrollment toEntity(EnrollmentRequestDTO enrollmentRequestDTO);
}
