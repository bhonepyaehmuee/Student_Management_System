package com.example.demo.controller;

import com.example.demo.dto.request.CourseRequestDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.enums.CourseStatus;
import com.example.demo.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/course")
@Tag(name = "Course API" , description = "CRUD operations for managing courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService)
    {
        this.courseService = courseService;
    }
    @Operation(summary = "crate the course here")
    @PostMapping("/")
    public ResponseEntity<ApiResponse<CourseResponseDTO>> createCourse(@Valid @RequestBody CourseRequestDTO courseRequestDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourseInfo(courseRequestDTO));
    }

    @Operation(summary = "Retrieve all courses")
    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<CourseResponseDTO>>> retrieveCourse()
    {
        return ResponseEntity.ok().body(courseService.getCourses());
    }
    @Operation(summary = "Retrieve courses depends on status")
    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<CourseResponseDTO>>>  getActiveCourses(CourseStatus status)
    {
        return ResponseEntity.ok().body(courseService.getCourseByStatus(status));
    }

    @Operation(summary = "Update the course's information")
    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<CourseResponseDTO>> updateCourseById(@PathVariable Long id, @Valid @RequestBody CourseRequestDTO courseRequestDTO)
    {
        return ResponseEntity.ok().body(courseService.updateCourseInfo(id, courseRequestDTO));
    }

    @Operation(summary = "change the status to inactive")
    @PatchMapping("/{id}/status")
    public  ResponseEntity<ApiResponse<CourseResponseDTO>> changeStatus(@PathVariable Long id,CourseStatus status)
    {
        return  ResponseEntity.ok().body(courseService.changeStatusById(id, status));
    }

}
