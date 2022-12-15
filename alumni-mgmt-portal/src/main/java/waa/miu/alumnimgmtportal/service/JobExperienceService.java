package waa.miu.alumnimgmtportal.service;

import waa.miu.alumnimgmtportal.entity.dto.JobDto;
import waa.miu.alumnimgmtportal.entity.dto.JobExperienceDto;

import java.util.List;

public interface JobExperienceService {
    List<JobExperienceDto> findAll();
    JobExperienceDto addJobExperience(JobExperienceDto jobExperience);
    JobExperienceDto updateJobExperience(int id, JobExperienceDto jobExperience);
    void deleteJobExperience(int id);
    JobExperienceDto findById(int id);

    List<JobExperienceDto> findByStudentId(int studentId);
}
