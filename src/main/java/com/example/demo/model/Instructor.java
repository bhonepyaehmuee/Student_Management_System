package com.example.demo.model;

import com.example.demo.enums.InstructorStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@Table(name = "instructors")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue("INSTRUCTOR")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Instructor extends User
{
    @Column(nullable = false, unique = true)
    private String instructorCode;

    @Enumerated(EnumType.STRING)
    private InstructorStatus status;

}
