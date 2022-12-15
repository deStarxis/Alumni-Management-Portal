package waa.miu.alumnimgmtportal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import waa.miu.alumnimgmtportal.entity.JobExperience;
import waa.miu.alumnimgmtportal.entity.Student;
import waa.miu.alumnimgmtportal.entity.dto.JobExperienceDto;
import waa.miu.alumnimgmtportal.repository.JobExperienceRepo;
import waa.miu.alumnimgmtportal.security.MyUserDetails;
import waa.miu.alumnimgmtportal.service.JobExperienceService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobExperienceServiceImpl implements JobExperienceService {
    private final ModelMapper modelMapper;
    private final JobExperienceRepo jobExperienceRepo;

    @Override
    public List<JobExperienceDto> findAll() {
        List<JobExperienceDto> jobExperiences = jobExperienceRepo.findAll().stream().filter(jobExperience -> !jobExperience.isDeleted()).map(jobExperience -> modelMapper.map(jobExperience, JobExperienceDto.class)).toList();
        return jobExperiences;
    }

    @Override
    public JobExperienceDto addJobExperience(JobExperienceDto jobExperience) {
        int studentId = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        Student student = new Student();
        student.setId(studentId);
        JobExperience jobExperience1 = modelMapper.map(jobExperience, JobExperience.class);
        jobExperience1.setStudent(student);
        try {
            jobExperienceRepo.save(jobExperience1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return modelMapper.map(jobExperience1, JobExperienceDto.class);
    }

    @Override
    public JobExperienceDto updateJobExperience(int id, JobExperienceDto jobExperience) {
        JobExperience jobExperience1 = jobExperienceRepo.findById(id).orElseThrow(() -> new RuntimeException("Job Experience Not found"));
        jobExperience1 = modelMapper.map(jobExperience, JobExperience.class);
        jobExperience1.setId(id);
        jobExperienceRepo.save(jobExperience1);
        return modelMapper.map(jobExperience1, JobExperienceDto.class);
    }

    @Override
    public void deleteJobExperience(int id) {
        JobExperience jobExperience = jobExperienceRepo.findById(id).orElseThrow(() -> new RuntimeException("Job Experience Not Found"));
        jobExperience.setDeleted(true);
        jobExperienceRepo.save(jobExperience);
    }

    @Override
    public JobExperienceDto findById(int id) {
        JobExperience jobExperience = jobExperienceRepo.findById(id).filter(jobExperience1 -> !jobExperience1.isDeleted()).orElseThrow(() -> new RuntimeException("Job Experience Not found"));
        return modelMapper.map(jobExperience, JobExperienceDto.class);
    }

    @Override
    public List<JobExperienceDto> findByStudentId(int studentId) {
        return jobExperienceRepo.findByStudent_Id(studentId)
                .stream()
                .filter(je -> !je.isDeleted())
                .map(je -> modelMapper.map(je, JobExperienceDto.class)).toList();

    }
}