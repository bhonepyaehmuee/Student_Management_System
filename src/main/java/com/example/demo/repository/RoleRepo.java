package com.example.demo.repository;

import com.example.demo.enums.StudentStatus;
import com.example.demo.model.Role;
import com.example.demo.model.Student;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Optional<Role> findByName(@NotNull String name);
    List<Role> findByStatusTrue();

//    List<> getStudentsActive(@Param ("status") StudentStatus status);
//    @Query ("Select r from Role r where r.name = :name")
//    Role findByRoleName(@Param("name") String name);

}
