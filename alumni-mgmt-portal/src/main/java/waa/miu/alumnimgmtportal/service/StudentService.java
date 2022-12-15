package waa.miu.alumnimgmtportal.service;

import waa.miu.alumnimgmtportal.entity.Student;
import waa.miu.alumnimgmtportal.entity.dto.StudentDto;
import waa.miu.alumnimgmtportal.model.StudentPerState;

import java.util.List;

public interface StudentService {
    Student save(Student student);

    List<StudentDto> findAll(String string, String value);
    List<StudentPerState> noOfStudentsPerState();
}
