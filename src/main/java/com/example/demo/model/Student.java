package com.example.demo.model;

import com.example.demo.enums.StudentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@DynamicUpdate
@Table(name = "students")
@DiscriminatorValue("STUDENT")
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User
{
    @Column (nullable = false,unique = true)
    private String studentRegNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudentStatus status;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments;
}
