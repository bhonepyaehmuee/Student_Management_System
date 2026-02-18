package com.example.demo.service.impl;

import com.example.demo.dto.request.RoleRequestDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.RoleResponseDTO;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepo;
import com.example.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    private static final String CREATE_SUCCESS = "Created Role Successfully!! ";
    private static final String GET_SUCCESS = "Retrieved Role Successfully!! ";
     private static final String SUCCESS_UPDATE = "Successfully Updated!!";
     private static final String SUCCESS_DELETE = "Successfully Deleted!!";


    @Override
    public ApiResponse<List<RoleResponseDTO>> getAllRoles() {
        List<RoleResponseDTO> responseDTO = roleRepo.findAll().stream().map(RoleMapper::mapToDTO).toList();

        return ApiResponse.<List<RoleResponseDTO>>builder().
                success(true)
                .data(responseDTO)
                .message(GET_SUCCESS)
                .build();
    }

    @Override
    public ApiResponse<List<RoleResponseDTO>> getActiveRoles() {
        List<Role> role = roleRepo.findByStatusTrue();
        log.warn("here is hte role from Repo {}",role);
        List<RoleResponseDTO> roleResponseDTOList = roleRepo.findByStatusTrue()
                .stream()
                .map(RoleMapper::mapToDTO)
                .toList();
        return ApiResponse.<List<RoleResponseDTO>>builder()
                .success(true)
                .message(SUCCESS_DELETE)
                .data(roleResponseDTOList)
                .build();
    }

    @Override
    public ApiResponse<RoleResponseDTO> createRole(RoleRequestDTO requestDTO) {
//      Optional<Role> existedRole =
        roleRepo.findByName(requestDTO.getName()).
                ifPresent(role ->
                {
                     throw new RuntimeException("Role already exists: " + requestDTO.getName());
                });
//      if (existedRole.isPresent())
//      {
//          throw new RuntimeException( ""Already have that "+requestDTO.getName()+ " existed!!");
//      }
        Role role = RoleMapper.mapToEntity(requestDTO);
        role.setStatus(true);
        Role savedRole = roleRepo.save(role);
        RoleResponseDTO roleResponseDTO = RoleMapper.mapToDTO(savedRole);

        return ApiResponse.<RoleResponseDTO>builder()
                .success(true)
                .message(CREATE_SUCCESS)
                .data(roleResponseDTO)
                .build();
    }

    @Transactional
    @Override
    public ApiResponse<RoleResponseDTO> updateRole(Long id, RoleRequestDTO requestDTO) {
        Role exsitingRole = roleRepo.findById(id).orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        if (requestDTO.getName() != null && roleRepo.findByName(requestDTO.getName()).isPresent()) {
            throw new RuntimeException("Role name is already existed " + requestDTO.getName());
        }
        if (requestDTO.getName() != null) {
            exsitingRole.setName(requestDTO.getName());
        }
        if (requestDTO.getDescription() != null) {
            exsitingRole.setDescription(requestDTO.getDescription());
        }
        exsitingRole.onUpdate();
//            Role updatedRole = roleRepo.save(exsitingRole);
        log.debug("here are the data {}{}", requestDTO.getName(), requestDTO.getDescription());


        Role updatedRole = roleRepo.save(exsitingRole);
        RoleResponseDTO roleResponseDTO = RoleMapper.mapToDTO(updatedRole);

        return ApiResponse.<RoleResponseDTO>builder().success(true).message(SUCCESS_UPDATE).data(roleResponseDTO).build();
    }

    @Override
    public ApiResponse<RoleResponseDTO> deleteRole(Long id) {
        Role role = roleRepo.findById(id).orElseThrow(() -> new RuntimeException("Role not found " + id));

        role.setStatus(!role.getStatus());
        role.onUpdate();
        RoleResponseDTO roleResponseDTO = RoleMapper.mapToDTO(roleRepo.save(role));
//        roleResponseDTO.setMessage(SUCCESS_DELETE);
        return ApiResponse.<RoleResponseDTO>builder().success(true).message(SUCCESS_DELETE).data(roleResponseDTO).build();
    }

}
