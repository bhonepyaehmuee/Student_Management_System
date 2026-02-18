package com.example.demo.dto.response;

import com.example.demo.enums.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDTO extends AuditableResponseDTO
{
    private  long id;
    private String courseCode;
    private String courseName;
    private String description;
    private CourseStatus status;

}
