package waa.miu.alumnimgmtportal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import waa.miu.alumnimgmtportal.entity.Student;
import waa.miu.alumnimgmtportal.entity.dto.StudentDto;
import waa.miu.alumnimgmtportal.model.StudentPerState;
import waa.miu.alumnimgmtportal.repository.StudentRepo;
import waa.miu.alumnimgmtportal.security.MyUserDetails;
import waa.miu.alumnimgmtportal.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final ModelMapper modelMapper;

    public Student save(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public List<StudentDto> findAll(String filter, String value) {
        List<StudentDto> students = new ArrayList<>();
        var role=((MyUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getRoles().stream().findFirst().get();
        if(role.getRole().equalsIgnoreCase("admin")){
            students = findAllStudents(filter, value);
        }else if(role.getRole().equalsIgnoreCase("faculty")){
            students = findActiveStudents(filter, value);
        }
        return students;
    }

    @Override
    public List<StudentPerState> noOfStudentsPerState() {
        return studentRepo.noOfJobsPerState();
    }

    private List<StudentDto> getDtoList(List<Student> students) {
        return students.stream().map(p -> {
            return getDto(p);
        }).toList();
    }

    private StudentDto getDto(Student user) {
                return modelMapper.map(user, StudentDto.class);

    }

    private List<StudentDto> findActiveStudents(String filter, String value){
        List<StudentDto> students = findAllStudents(filter, value)
                .stream()
                .filter(s-> s.isActive() == true).toList();
        return students;
    }


    private List<StudentDto> findAllStudents(String filter, String value){
        List<Student> students;
        switch (filter.toLowerCase()) {
            case "all":
                students = studentRepo.findAll();
                break;
            case "state":
                students = studentRepo.findByAddress_StateIgnoreCase(value);
                break;
            case "city":
                var state_city = value.split(".");
                students = studentRepo.findByAddress_StateIgnoreCaseAndAddress_CityIgnoreCase(state_city[0], state_city[1]);
                break;
            case "major":
                students = studentRepo.findByMajorContainingIgnoreCase(value);
                break;
            case "name":
                students = studentRepo.findByFirstnameContaining(value);
                break;
            case "universityid":
                students = studentRepo.findByUniversityId(Integer.parseInt(value));
                break;
            default:
                students = studentRepo.findAll();
                break;
        }
        return getDtoList(students);
    }

}
