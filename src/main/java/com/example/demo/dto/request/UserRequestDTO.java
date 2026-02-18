package com.example.demo.dto.request;

import com.example.demo.model.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserRequestDTO
{
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    private String username;
    @NotBlank(message = "Password is required")
    @Size (min = 8, message = "Password must be at least 8 characters")
    private String password;
    @NotBlank
    @Pattern(
            regexp = "^09 [0-9]{7,9}$",
            message = "Phone number must start with 09 followed by 7 to 9 digits, e.g., 091234567, 0912345678, 09123456789"
    )
    private String phoneNumber;
    private String address;
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

//    @NotBlank(message = "At least one role ID is required")
//    private Set<@Positive Long> roleIds;
}
