package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleRequestDTO {

    @NotNull
    @NotBlank(message = "Role Name is required")
    private String name;
    private String description;

}
