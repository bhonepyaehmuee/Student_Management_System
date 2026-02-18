package com.example.demo.mapper;

import com.example.demo.dto.request.CourseRequestDTO;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CourseMapper {
    public abstract Course toEntity(CourseRequestDTO courseRequestDTO);
//    @Mapping(target = "status", source = "status")
    public abstract CourseResponseDTO toDTO(Course course);
}
