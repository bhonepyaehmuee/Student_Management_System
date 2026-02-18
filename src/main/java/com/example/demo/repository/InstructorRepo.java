package com.example.demo.repository;

import com.example.demo.enums.InstructorStatus;
import com.example.demo.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor, Long>
{
//    Optional<Instructor> findByName(String name);
    boolean existsByEmail(String email);
    boolean existsByInstructorCode(String instructorCode);
    boolean existsByUsername(String username);

    @Query("select i from Instructor i where i.status = :status ")
    List<Instructor> findInstructorByStatus(@Param("status") InstructorStatus status);

}
