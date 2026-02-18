package com.example.demo.service.impl;

import com.example.demo.dto.request.CourseRequestDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.enums.CourseStatus;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.GlobalExceptionsHandler;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepo;
import com.example.demo.service.CourseService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    CourseRepo courseRepo;

    @Override
    public ApiResponse<CourseResponseDTO> createCourseInfo(CourseRequestDTO courseRequestDTO) {

        Course course = courseMapper.toEntity(courseRequestDTO);
        if(courseRepo.existsByCourseCode(course.getCourseCode()))
        {
            throw  new DuplicateResourceException("Course code is already existed!!");
        }
        course.setStatus(CourseStatus.ACTIVE);
        log.info("here is status {}",course.getStatus());
        Course savedCourse = courseRepo.save(course);
        CourseResponseDTO courseResponseDTO = courseMapper.toDTO(savedCourse);
        log.warn("Data is here courseName = {} , Course Code ={}",courseRequestDTO.getCourseName(),courseRequestDTO.getCourseCode());

        courseResponseDTO.setMessage("Successfully Created!!");
        return ApiResponse.<CourseResponseDTO>builder()
                .success(true)
                .message("Created the course!!")
                .data(courseResponseDTO)
                .build();

    }

    @Override
    public ApiResponse<List<CourseResponseDTO>> getCourses()
    {
       List<CourseResponseDTO> courseResponseDTOS = courseRepo.findAll().stream().map(courseMapper::toDTO).toList();

        return ApiResponse.<List<CourseResponseDTO>>builder()
                .success(true)
                .message("Fetched all courses!!")
                .data(courseResponseDTOS)
                .build();
    }

    @Override
    public ApiResponse<List<CourseResponseDTO>> getCourseByStatus(CourseStatus status) {
      List<CourseResponseDTO> courseResponseDTOS =
              courseRepo.findByActiveCourse(status).stream().map(courseMapper::toDTO).toList();
        log.info("Fetched {} courses with status: {}", courseResponseDTOS.size(), status);

        String message = courseResponseDTOS.isEmpty() ? "No courses found with status: " + status : "Fetched courses with status: " + status;
        return ApiResponse.<List<CourseResponseDTO>>builder()
                .success(true)
                .message(message)
                .data(courseResponseDTOS)
                .build();
    }

    @Override
    public ApiResponse<CourseResponseDTO> updateCourseInfo(Long id, CourseRequestDTO courseRequestDTO) {
        Course existedCourse = courseRepo.findById(id)
                .orElseThrow(() ->
                        new GlobalExceptionsHandler.ResourceNotFoundException("Can't found course using this id"));
        if (courseRequestDTO.getCourseCode() != null &&
                !courseRequestDTO.getCourseCode().equals(existedCourse.getCourseCode()) &&
                courseRepo.existsByCourseCode(courseRequestDTO.getCourseCode())) {
            throw new DuplicateResourceException("Course code already exists!");
        }

        if (courseRequestDTO.getCourseCode() != null) {
            existedCourse.setCourseCode(courseRequestDTO.getCourseCode());
        }

        if (courseRequestDTO.getCourseName() != null) {
            existedCourse.setCourseName(courseRequestDTO.getCourseName());
        }
        if(courseRequestDTO.getDescription() != null)
        {
            existedCourse.setDescription(courseRequestDTO.getDescription());
        }
        Course savedCourse = courseRepo.save(existedCourse);
        CourseResponseDTO courseResponseDTO = courseMapper.toDTO(savedCourse);
        return ApiResponse.<CourseResponseDTO>builder()
                .success(true)
                .message("Course updated successfully")
                .data(courseResponseDTO)
                .build();
    }

    @Override
    public ApiResponse<CourseResponseDTO> changeStatusById(Long id,CourseStatus targetStatus) {
     Course course = courseRepo.findById(id)
                .orElseThrow(()->
                        new GlobalExceptionsHandler.ResourceNotFoundException("Course is not found by that id "+ id));
        if(course.getStatus() != null && course.getStatus() == targetStatus) {
            return ApiResponse.<CourseResponseDTO>builder()
                    .success(false)
                    .message("Course is already "+targetStatus)
                    .data(courseMapper.toDTO(course))
                    .build();
        }
        CourseStatus oldStatus = course.getStatus();
        course.setStatus(targetStatus);

        Course savedCourse = courseRepo.save(course);
        CourseResponseDTO responseDTO = courseMapper.toDTO(savedCourse);
        responseDTO.setMessage("Course status updated successfully");

        log.info("Course id {} status changed from {} to {}", id, oldStatus, targetStatus);

        return ApiResponse.<CourseResponseDTO>builder()
                .success(true)
                .message("Course status updated successfully")
                .data(responseDTO)
                .build();
    }
}
