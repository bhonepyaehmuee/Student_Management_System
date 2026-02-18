package com.example.demo.repository;

import com.example.demo.enums.CourseStatus;
import com.example.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Long> {
    @Query("select c from Course c where c.status = :status")
    List<Course> findByActiveCourse(CourseStatus status);

    boolean existsByCourseCode(String courseCode);
}
