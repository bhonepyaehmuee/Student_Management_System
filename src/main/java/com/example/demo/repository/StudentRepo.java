package com.example.demo.repository;

import com.example.demo.dto.response.StudentResponseDTO;
import com.example.demo.enums.StudentStatus;
import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student,Long> {
    boolean existsByEmail(String email);
    boolean existsByStudentRegNumber(String studentRegNumber);

    @Query("Select s from Student s where s.status = :status")
    List<Student> getStudentsActive(@Param ("status")StudentStatus status);
}
