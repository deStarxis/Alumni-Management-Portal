package waa.miu.alumnimgmtportal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import waa.miu.alumnimgmtportal.entity.dto.JobExperienceDto;
import waa.miu.alumnimgmtportal.service.JobExperienceService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/job-experiences")
@RequiredArgsConstructor
public class JobExperienceController {
    private final JobExperienceService jobExperienceService;
    @GetMapping
    public List<JobExperienceDto> getAllJobExperiences(){
        return jobExperienceService.findAll();
    }
    @GetMapping("/{id}")
    public JobExperienceDto getJobExperience(@PathVariable int id){
        return jobExperienceService.findById(id);
    }
    @PostMapping
    public JobExperienceDto addJobExperience(@RequestBody JobExperienceDto jobExperience){
        return jobExperienceService.addJobExperience(jobExperience);
    }
    @PutMapping("/{id}")
    public JobExperienceDto updateJobExperience(@PathVariable int id, @RequestBody JobExperienceDto jobExperience){
        return jobExperienceService.updateJobExperience(id,jobExperience);
    }
    @DeleteMapping("/{id}")
    public void deleteJobExperience(@PathVariable int id){
        jobExperienceService.deleteJobExperience(id);
    }

    @GetMapping("/getExperiences/{userId}")
    public List<JobExperienceDto> getJobExperiencesByStudentId(@PathVariable int userId ){
        return jobExperienceService.findByStudentId(userId);
    }
}
