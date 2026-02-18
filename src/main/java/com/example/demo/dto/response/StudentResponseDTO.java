package com.example.demo.dto.response;

import com.example.demo.enums.RoleStatus;
import com.example.demo.enums.StudentStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class  StudentResponseDTO extends UserResponseDTO{

    private String studentRegNumber;
    private StudentStatus status;

}
