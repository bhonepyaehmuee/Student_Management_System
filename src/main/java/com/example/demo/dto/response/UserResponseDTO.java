package com.example.demo.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO extends AuditableResponseDTO{
    private  Long id;
    private String name;
    private String email;
    private String username;

    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;


}
