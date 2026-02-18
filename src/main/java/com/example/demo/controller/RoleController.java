package com.example.demo.controller;

import com.example.demo.dto.request.RoleRequestDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.RoleResponseDTO;
import com.example.demo.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController
{
    @Autowired
    RoleService roleService;

    @Operation(summary = "Get All Roles")
    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleResponseDTO>>> getAllRoles()
    {
        return ResponseEntity.ok(roleService.getAllRoles());

    }
    @Operation(summary = "Retrieve only Active Roles")
    @GetMapping("/active")
    public  ResponseEntity<ApiResponse<List<RoleResponseDTO>>> getAllActiveRoles()
    {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getActiveRoles());
    }

    @Operation(summary = "Create the Role")
    @PostMapping
    public ResponseEntity<ApiResponse<RoleResponseDTO>> createRole( @Valid @RequestBody RoleRequestDTO roleRequestDTO)
    {
        return  ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(roleRequestDTO));
    }

    @Operation(summary ="Update the Role")
    @PatchMapping("/{id}")
    public  ResponseEntity<ApiResponse<RoleResponseDTO>> updateRole(
            @PathVariable Long id, @RequestBody @Valid RoleRequestDTO roleRequestDTO)
    {
        return ResponseEntity.ok(roleService.updateRole(id, roleRequestDTO));
    }

    @Operation(summary = "Change the Status")
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<RoleResponseDTO>> deleteRole(@PathVariable long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.deleteRole(id));
    }


}
