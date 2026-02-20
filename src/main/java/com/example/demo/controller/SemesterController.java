package com.example.demo.controller;

import com.example.demo.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name="Semester API", description = "Semester management")
@RequestMapping("/api/semester")
public class SemesterController {
    @Operation(summary = "C")
    @GetMapping("/")
    public ApiResponse<Seme>
}
