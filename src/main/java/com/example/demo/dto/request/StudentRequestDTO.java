    package com.example.demo.dto.request;

    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Pattern;
    import lombok.Data;

    @Data
    public class StudentRequestDTO extends UserRequestDTO{

        @NotBlank(message = "Student Registration Number is mandatory need!!")
        @Pattern(
                regexp = "^R\\d{1,23}$",
                message = "Registration number must start with 'R' followed by 1 to 23 digits"
        )
        private String studentRegNumber;
//        private StudentStatus status =StudentStatus.ACTIVE;
    }


