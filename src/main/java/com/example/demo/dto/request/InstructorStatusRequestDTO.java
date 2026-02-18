package com.example.demo.dto.request;

import com.example.demo.enums.InstructorStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorStatusRequestDTO {
    @NotNull
    private InstructorStatus status;
}
