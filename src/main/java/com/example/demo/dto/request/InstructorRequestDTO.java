package com.example.demo.dto.request;

import com.example.demo.enums.InstructorStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InstructorRequestDTO extends UserRequestDTO {

    @NotBlank(message = "Instructor code is required")
    @Pattern(regexp = "^(?!INST-00212$).*", message = "Instructor code INST-00212 is not allowed")
    private String instructorCode;

    @NonNull
    private InstructorStatus status;

}
