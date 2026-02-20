package com.example.demo.service.impl;

import com.example.demo.constants.RoleConstants;
import com.example.demo.dto.request.InstructorRequestDTO;
import com.example.demo.dto.request.InstructorStatusRequestDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.InstructorResponseDTO;
import com.example.demo.enums.InstructorStatus;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.GlobalExceptionsHandler;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.InstructorMapper;
import com.example.demo.model.Instructor;
import com.example.demo.model.Role;
import com.example.demo.repository.InstructorRepo;
import com.example.demo.repository.RoleRepo;
import com.example.demo.service.InstructorService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepo instructorRepo;
    private final RoleRepo roleRepo;
    public InstructorServiceImpl(InstructorRepo instructorRepo,RoleRepo roleRepo)
    {
        this.instructorRepo = instructorRepo;
        this.roleRepo = roleRepo;
    }

    public static  final String  CREATE_SUCCESS = "Successfully Create Instructor!!";

    @Override
    public ApiResponse<InstructorResponseDTO> createInstructor(InstructorRequestDTO instructorRequestDTO) {

        if (instructorRepo.existsByInstructorCode(instructorRequestDTO.getInstructorCode())) {
            throw new DuplicateResourceException(
                    "Instructor Code is already have in the system!"
            );
        }
        if (instructorRepo.existsByUsername(instructorRequestDTO.getUsername())) {
            throw new DuplicateResourceException("Username already exists");
        }
        if (instructorRepo.existsByEmail(instructorRequestDTO.getEmail())) {
            log.warn("Attempt to update instructor with existing email: {}",instructorRequestDTO.getEmail());

            throw new DuplicateResourceException(
                    "Email is already used!"
            );

        }


        Instructor instructor = InstructorMapper.mapToEntity(instructorRequestDTO);
        instructor.setStatus(InstructorStatus.ACTIVE);
       Role roleInstructor = (roleRepo.findByName(RoleConstants.INSTRUCTOR)
                .orElseThrow(()->
                        new IllegalStateException("Instructor Role is not found. Please Created it first!!"))
                );
        instructor.setRoles(Set.of(roleInstructor));
        Instructor newInstructor = instructorRepo.save(instructor);
        InstructorResponseDTO instructorResponseDTO = InstructorMapper.mapToDTO(newInstructor);
        instructorResponseDTO.setMessage("Retrieved!!");
        return  ApiResponse.<InstructorResponseDTO>builder()
                .success(true)
                .message(CREATE_SUCCESS)
                .data(instructorResponseDTO)
                .build();

    }

    @Override
    public ApiResponse<List<InstructorResponseDTO>> getAllInstructor() {

        List<InstructorResponseDTO> instructorResponseDTOList = instructorRepo.findAll()
                .stream()
                .map(InstructorMapper::mapToDTO)
                .collect(Collectors.toList());

        return ApiResponse.<List<InstructorResponseDTO>>builder()
                .success(true)
                .message("SUCCESSFULLY RETRIEVE DATA")
                .data(instructorResponseDTOList)
                .build();
    }

    @Override
    public ApiResponse<List<InstructorResponseDTO>> getInstructorByStatus(InstructorStatus status)
    {
       List<InstructorResponseDTO> instructorResponseDTOList = instructorRepo.findInstructorByStatus(status)
                .stream().map(InstructorMapper::mapToDTO).toList();

        return ApiResponse.<List<InstructorResponseDTO>>builder()
                .success(true)
                .message("SUCCESSFULLY GET "+ status + " Instructors")
                .data(instructorResponseDTOList)
                .build();
    }

    @Override
    public ApiResponse<InstructorResponseDTO> updateInstructorInfo(Long id, InstructorRequestDTO instructorRequestDTO)
    {
        Instructor existedInstructor = instructorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor is not found with this id!! --->" +id));
        if(instructorRequestDTO.getEmail() != null &&
        !instructorRequestDTO.getEmail().equals(existedInstructor.getEmail())
           && instructorRepo.existsByEmail(instructorRequestDTO.getEmail()))
        {
            log.warn("Attempt to update instructor {} with existing email: {}", id, instructorRequestDTO.getEmail());

            log.debug("Here is the email ");
            throw  new DuplicateResourceException("Email is already exited!!");
        }
        if(instructorRequestDTO.getName() != null)
        {
            existedInstructor.setName(instructorRequestDTO.getName());
        }
        if(instructorRequestDTO.getEmail() != null)
        {
            existedInstructor.setEmail(instructorRequestDTO.getEmail());
        }
        if(instructorRequestDTO.getUsername() != null)
        {
            existedInstructor.setUsername(instructorRequestDTO.getUsername());
        }
        if(instructorRequestDTO.getPassword() != null)
        {
            existedInstructor.setPassword(instructorRequestDTO.getPassword());
        }
        if(instructorRequestDTO.getPhoneNumber() != null)
        {
            existedInstructor.setPhoneNumber(instructorRequestDTO.getPhoneNumber());
        }
        if(instructorRequestDTO.getAddress() != null)
        {
            existedInstructor.setAddress(instructorRequestDTO.getAddress());
        }
        if(instructorRequestDTO.getDateOfBirth() != null)
        {
            existedInstructor.setDateOfBirth(instructorRequestDTO.getDateOfBirth());
        }
        if(instructorRequestDTO.getInstructorCode() != null)
        {
            existedInstructor.setInstructorCode(instructorRequestDTO.getInstructorCode());
        }
        existedInstructor.onUpdate();
        InstructorResponseDTO instructorResponseDTO = InstructorMapper.mapToDTO(instructorRepo.save(existedInstructor));
        instructorResponseDTO.setMessage("Updated Here!!");
        return ApiResponse.<InstructorResponseDTO>builder()
                .success(true)
                .message("Successfully update The instructor's info")
                .data(instructorResponseDTO)
                .build();
    }

    @Override
    public ApiResponse<InstructorResponseDTO> changeInstructorStatus(Long id, InstructorStatus newStatus) {

        Instructor existedInstructor = instructorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Instructor not found with id: " + id
                ));

        InstructorStatus currentStatus = existedInstructor.getStatus();


        if (!currentStatus.canTransitionTo(newStatus)) {
            throw new IllegalStateException(
                    "Cannot change status from " + currentStatus + " to " + newStatus
            );
        }

        if (currentStatus == newStatus) {
            throw new IllegalStateException(
                    "Instructor is already in status: " + newStatus
            );
        }

        existedInstructor.setStatus(newStatus);
        existedInstructor.onUpdate();

        Instructor updated = instructorRepo.save(existedInstructor);

        log.debug("Instructor soft-deleted with id {}", id);
        InstructorResponseDTO instructorResponseDTO = InstructorMapper.mapToDTO(updated);
        instructorResponseDTO.setMessage("Successfully Changed the status");

        return ApiResponse.<InstructorResponseDTO>builder()
                .success(true)
                .message("Instructor status changed from " + currentStatus + " to " + newStatus)
                .data(instructorResponseDTO)
                .build();
    }


}
