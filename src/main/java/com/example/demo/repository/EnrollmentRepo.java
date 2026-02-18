package com.example.demo.repository;

import com.example.demo.enums.EnrollmentStatus;
import com.example.demo.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment,Long>
{
    @Query("select e from Enrollment e where e.status=:status")
    List<Enrollment> findByStatus(EnrollmentStatus status);
}
