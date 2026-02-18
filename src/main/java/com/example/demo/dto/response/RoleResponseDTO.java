package com.example.demo.dto.response;

import com.example.demo.model.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class RoleResponseDTO
{
    private Long id;

    private String name;
    private String description;
    private Boolean status;


    private LocalDateTime createdAt;
    private  LocalDateTime updatedAt;
    private  String createdBy;
    private String updatedBy;


}
