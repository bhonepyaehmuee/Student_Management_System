package com.example.demo.controller;


import com.example.demo.dto.request.InstructorRequestDTO;
import com.example.demo.dto.request.InstructorStatusRequestDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.InstructorResponseDTO;
import com.example.demo.enums.InstructorStatus;
import com.example.demo.model.Instructor;
import com.example.demo.service.InstructorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructor")

@Tag(name = "Instructor API", description = "Operations for managing instructors")
public class InstructorController {

    @Autowired
    InstructorService instructorService;

    @Operation(summary = "Create the Instructor")
    @PostMapping("/")
    public ResponseEntity<ApiResponse<InstructorResponseDTO>> createInstructor(@RequestBody InstructorRequestDTO instructorRequestDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(instructorService.createInstructor(instructorRequestDTO));
    }

    @Operation(summary = "Get the Instructor")
    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<InstructorResponseDTO>>> getAllInstructor()
    {
        return  ResponseEntity.status(HttpStatus.OK).body(instructorService.getAllInstructor());
    }

    @Operation(summary = "Retrieve Instructor depends on status")
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<InstructorResponseDTO>>> getInstructorByStatus(
            @Parameter(
                    description = "Instructor status"
            )@PathVariable("status") InstructorStatus status)
    {
        return ResponseEntity.status(HttpStatus.OK).body(instructorService.getInstructorByStatus(status));
    }

    @Operation(summary = "Edit the Instructor's Information")
    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<InstructorResponseDTO>> updateInstructor(@PathVariable Long id, @RequestBody InstructorRequestDTO instructorRequestDTO)
    {
        return ResponseEntity.status(HttpStatus.OK).body(instructorService.updateInstructorInfo(id,instructorRequestDTO));
    }

//    @Operation(summary = "Change into INACTIVE status of the instructor's Information")
//    @PatchMapping("/change/{id}")
//    public ResponseEntity<ApiResponse<InstructorResponseDTO>> changeInstructorStatusInfo(@PathVariable Long id)
//    {
//        return ResponseEntity.status(HttpStatus.OK).body(instructorService.changeInstructorStatus(id));
//    }

    @Operation(
            summary = "Change instructor status",
            description = "Update the status of an instructor. Allowed values: ACTIVE, ON_LEAVE, INACTIVE, TERMINATED."
    )
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<InstructorResponseDTO>> changeInstructorStatusInfo(
            @Parameter(description = "Instructor ID", required = true) @PathVariable Long id,
            @RequestBody @Valid InstructorStatusRequestDTO instructorStatusRequestDTO
    )
    {
        return ResponseEntity.status(HttpStatus.OK).body(instructorService.changeInstructorStatus(id,instructorStatusRequestDTO.getStatus()));
    }
}
