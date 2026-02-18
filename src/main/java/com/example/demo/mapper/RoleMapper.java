package com.example.demo.mapper;


import com.example.demo.dto.request.RoleRequestDTO;
import com.example.demo.dto.response.RoleResponseDTO;
import com.example.demo.model.Role;

public class RoleMapper {
    public static RoleResponseDTO mapToDTO(Role role)
    {
        return RoleResponseDTO.builder()
                .id(role.getId())
                .name(role.getName().toUpperCase())
                .description(role.getDescription())
                .status(role.getStatus())
                .createdAt(role.getCreatedAt())
                .updatedAt(role.getUpdatedAt())
                .createdBy(role.getCreatedBy())
                .updatedBy(role.getUpdatedBy())
                .build();
    }

    public static Role mapToEntity(RoleRequestDTO roleRequestDTO)
    {
        return Role.builder()
                .name(roleRequestDTO.getName().toUpperCase())
                .description(roleRequestDTO.getDescription())
                .build();

    }
}
