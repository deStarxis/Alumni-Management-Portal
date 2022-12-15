package waa.miu.alumnimgmtportal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import waa.miu.alumnimgmtportal.entity.Faculty;
import waa.miu.alumnimgmtportal.repository.FacultyRepo;
import waa.miu.alumnimgmtportal.service.FacultyService;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepo facultyRepo;

    public Faculty save(Faculty faculty){
        return facultyRepo.save(faculty);
    }

}
