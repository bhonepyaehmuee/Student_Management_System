package com.example.demo.controller;

import com.example.demo.dto.request.StudentRequestDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.StudentResponseDTO;
import com.example.demo.enums.StudentStatus;
import com.example.demo.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/students")
@CrossOrigin(origins = "http://localhost:5173")
@Tag (name = "Student API", description = "Operations for managing students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Operation (summary = "Create a new student")
    @PostMapping("/")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> createStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO)
    {
//        StudentResponseDTO studentResponseDTO = studentService.createStudent(studentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(studentRequestDTO));
    }

    @Operation(summary = "Change the Status Here!!")
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> changeStudentStatus(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.changeStudentStatus(id));
    }

    @Operation(summary = "Get All lists of students ")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<StudentResponseDTO>>> getAllStudents()
    {
//        List<StudentResponseDTO> studentResponseDTOList = studentService.getAllStudents();
        return ResponseEntity.ok().body(studentService.getAllStudents());
    }

    @Operation(summary = "Get all ACTIVE lists of students ")
    @GetMapping("/allActive")
    public ResponseEntity<ApiResponse<List<StudentResponseDTO>>> getAllActiveStudents(StudentStatus status)
    {
//        List<Student> studentResponseDTOList = studentService.getAllActiveStudents();
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.getAllActiveStudents(status));
//        return ;
    }

    @Operation(summary = "Update the Student's Info")
    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> updateStudents(@PathVariable Long id,@Valid @RequestBody StudentRequestDTO studentRequestDTO)
    {
//        StudentResponseDTO responseDTO = studentService.updateStudentById(id,studentRequestDTO);
        return ResponseEntity.ok().body(studentService.updateStudentById(id,studentRequestDTO));
    }
    @Operation(summary = "Delete the Student's Info")
    @PatchMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDTO>>  deleteStudent(@PathVariable Long id)
    {
//        StudentResponseDTO responseDTO =studentService.deleteStudent(id);
        return ResponseEntity.ok().body(studentService.deleteStudent(id));
    }
}
