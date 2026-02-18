package com.example.demo.service;

import com.example.demo.dto.request.RoleRequestDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.RoleResponseDTO;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface RoleService {
    ApiResponse< List<RoleResponseDTO>> getAllRoles();
    ApiResponse<List<RoleResponseDTO>> getActiveRoles();
    ApiResponse<RoleResponseDTO> createRole(RoleRequestDTO requestDTO);
    ApiResponse<RoleResponseDTO> updateRole(Long id,RoleRequestDTO requestDTO);
    ApiResponse<RoleResponseDTO> deleteRole(Long id);
}
