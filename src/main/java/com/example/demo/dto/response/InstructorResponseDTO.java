package com.example.demo.dto.response;

import com.example.demo.enums.InstructorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class InstructorResponseDTO extends UserResponseDTO
{
    private  String instructorCode;
    private InstructorStatus instructorStatus;
}
