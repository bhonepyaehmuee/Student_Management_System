package com.example.demo.service.impl;
import com.example.demo.constants.RoleConstants;
import com.example.demo.dto.request.StudentRequestDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.StudentResponseDTO;
import com.example.demo.enums.StudentStatus;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.Role;
import com.example.demo.model.Student;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.StudentRepo;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService
{
    private  final StudentRepo studentRepo;
    private final RoleRepo roleRepo;
//    private final  PasswordEncoder passwordEncoder;

    public static final String ROLE_STUDENT = "ROLE_STUDENT";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_INSTRUCTOR = "ROLE_INSTRUCTOR";

    private static final String Retrieve_SUCCESS = "Successfully Retrieved Student";
    private static final String CREATE_SUCCESS = "Successfully Created Student";
    private static final String UPDATE_SUCCESS = "Successfully Updated Student";
    private static final String SOFT_DELETE_SUCCESS = "Successfully Changed the Student's Status";
    //Create the Student;
    public ApiResponse<StudentResponseDTO> createStudent(StudentRequestDTO requestDTO)
    {

        if (studentRepo.existsByEmail(requestDTO.getEmail())) {
            throw new DuplicateResourceException("\n Email is already used!");
        }

        if (studentRepo.existsByStudentRegNumber(requestDTO.getStudentRegNumber())) {
            throw new DuplicateResourceException("\n Student number already exists!");
        }
        /*
        1.RequestDTO --> Entity
        2.save the Entity
        3.Entity --> ResponseDTO
         */
        Student student = StudentMapper.mapToEntity(requestDTO);
        student.setStatus(StudentStatus.ACTIVE);

        //For Roles set up
//        Set<Role> roles = new HashSet<>(roleRepo.findAllById(requestDTO.getRoleIds()));
//        log.info("here is the role data {}",roles);
////           Long roleList = roleRepo.findById(requestDTO.getRoleIds());
//           if(roles.size() != requestDTO.getRoleIds().size())
//           {
//            throw new ResourceNotFoundException("Roles cannot found!!!");
//           }
//           student.setRoles(roles);

      Role roleStudent = roleRepo.findByName(RoleConstants.STUDENT)
              .orElseThrow(()->
                      new IllegalStateException("STUDENT is not Found. Please Create the Role First!")
              );

        log.info("\n here is the Role is ->{}",roleStudent.getId());
        student.setRoles(Set.of(roleStudent));

        StudentResponseDTO responseDTO = StudentMapper.mapToDTO(studentRepo.save(student));
        responseDTO.setMessage("Created Students Successfully!!");
        return ApiResponse.<StudentResponseDTO>builder()
                .success(true)
                .message(CREATE_SUCCESS)
                .data(responseDTO)
                .build();

    }


    public ApiResponse<List<StudentResponseDTO>> getAllStudents() {

       List<StudentResponseDTO> studentResponseDTOList = studentRepo.findAll().stream().map(StudentMapper::mapToDTO).toList();
        return ApiResponse.<List<StudentResponseDTO>>builder()
                .success(true)
                .message(Retrieve_SUCCESS)
                .data(studentResponseDTOList)
                .build();

//        return studentRepo.findAll().stream().map(StudentMapper::mapToDTO).toList();

    }

    @Override
    public ApiResponse<List<StudentResponseDTO>> getAllActiveStudents(StudentStatus status)
    {

     List<StudentResponseDTO> studentResponseDTOList = studentRepo.getStudentsActive(StudentStatus.ACTIVE).stream().map(StudentMapper::mapToDTO).toList();
      return  ApiResponse.<List<StudentResponseDTO>>builder()
              .success(true)
              .message(Retrieve_SUCCESS)
              .data(studentResponseDTOList)
              .build();
    }

    @Override
    public ApiResponse<StudentResponseDTO> changeStudentStatus(Long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Can't find the id"));
    student.setStatus(StudentStatus.INACTIVE);
    student.onUpdate();
    StudentResponseDTO studentResponseDTO =StudentMapper.mapToDTO(studentRepo.save(student));
        return ApiResponse.<StudentResponseDTO>builder().success(true).message(SOFT_DELETE_SUCCESS).data(studentResponseDTO).build();
    }


    public ApiResponse<StudentResponseDTO> updateStudentById(Long studentId, StudentRequestDTO studentRequestDTO)
    {
       Student student =   studentRepo.findById(studentId)
               .orElseThrow(() -> new ResourceNotFoundException("Student is not found with this id!! --->" +studentId));
        log.info("\n here we can find the Student by ID: {} ", student.getEmail());

        if(studentRequestDTO.getEmail() !=null &&
                !studentRequestDTO.getEmail().equals(student.getEmail())
                && studentRepo.existsByEmail(studentRequestDTO.getEmail()))
        {
            log.info("\n here is the email id={},email={}",student.getId(),studentRequestDTO.getEmail());
           throw  new DuplicateResourceException("Email is already existed!!");
        }

        if(studentRequestDTO.getEmail() !=null)
        {
            student.setEmail(studentRequestDTO.getEmail());
        }
        if(studentRequestDTO.getName() != null )
        {
            student.setName(studentRequestDTO.getName());
        }
        if(studentRequestDTO.getUsername() != null )
        {
            student.setUsername(studentRequestDTO.getUsername());
        }
//        if(studentRequestDTO.getPassword() != null )
//        {
//            student.setPassword(studentRequestDTO.getPassword());
//        }
        if(studentRequestDTO.getPhoneNumber() != null )
        {
            student.setPhoneNumber(studentRequestDTO.getPhoneNumber());
        }
        if(studentRequestDTO.getAddress() != null )
        {
            student.setAddress(studentRequestDTO.getAddress());
        }
        if(studentRequestDTO.getDateOfBirth() != null )
        {
            student.setDateOfBirth(studentRequestDTO.getDateOfBirth());
        }

        if(studentRequestDTO.getStudentRegNumber() != null
            && !studentRequestDTO.getStudentRegNumber().equals(student.getStudentRegNumber())
                && studentRepo.existsByStudentRegNumber(studentRequestDTO.getStudentRegNumber()))
        {
            throw new DuplicateResourceException("Student Registration Number is already Existed!!");
        }
        if(studentRequestDTO.getStudentRegNumber() != null )
        {
            student.setStudentRegNumber(studentRequestDTO.getStudentRegNumber());
        }


//         Student updateStudent =  studentRepo.save(student);
        StudentResponseDTO responseDTO = StudentMapper.mapToDTO(studentRepo.save(student));

//        responseDTO.setMessage(UPDATE_SUCCESS);

        return ApiResponse.<StudentResponseDTO>builder()
                .success(true)
                .message(UPDATE_SUCCESS)
                .data(responseDTO)
                .build();
    }

   public ApiResponse<StudentResponseDTO>  deleteStudent(Long id) {
        Student student =   studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Student is not found with this id!! --->" +id));
        log.info("Soft deleting student with email: {}", student.getEmail());

//        student.setActive(false);
        student.setStatus(StudentStatus.INACTIVE);
        student.onUpdate();
        StudentResponseDTO responseDTO = StudentMapper.mapToDTO(studentRepo.save(student));
//        responseDTO.setMessage(SOFT_DELETE_SUCCESS);
       return ApiResponse.<StudentResponseDTO>builder()
               .success(true)
               .message(SOFT_DELETE_SUCCESS)
               .data(responseDTO)
               .build();
    }


}
