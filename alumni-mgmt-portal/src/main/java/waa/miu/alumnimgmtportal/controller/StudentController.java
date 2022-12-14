package waa.miu.alumnimgmtportal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import waa.miu.alumnimgmtportal.entity.Student;
import waa.miu.alumnimgmtportal.entity.dto.StudentDto;
import waa.miu.alumnimgmtportal.model.StudentPerState;
import waa.miu.alumnimgmtportal.service.StudentService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/students")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_FACULTY','ROLE_ADMIN')")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentDto> findAll(
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam(required = false, defaultValue = "") String value
    ) {
        var students = studentService.findAll(filter, value);
        return students;
    }

    @GetMapping("/perState")
    public List<StudentPerState> noOfStudentsPerState(){
       return studentService.noOfStudentsPerState();
    }
}
