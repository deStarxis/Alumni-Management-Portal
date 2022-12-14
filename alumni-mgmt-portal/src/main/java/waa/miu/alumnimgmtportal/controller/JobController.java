package waa.miu.alumnimgmtportal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import waa.miu.alumnimgmtportal.entity.JobApplication;
import waa.miu.alumnimgmtportal.entity.dto.JobApplicationDto;
import waa.miu.alumnimgmtportal.entity.Tag;
import waa.miu.alumnimgmtportal.entity.dto.JobDto;
import waa.miu.alumnimgmtportal.entity.dto.JobSimpleDto;
import waa.miu.alumnimgmtportal.model.JobCount;
import waa.miu.alumnimgmtportal.repository.JobRepo;
import waa.miu.alumnimgmtportal.service.JobService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_FACULTY')")
public class JobController {
    private final JobService jobService;
    private final JobRepo jobRepo;

    @GetMapping
    public List<JobSimpleDto> getAllJobs(@RequestParam(required = false, defaultValue = "") String filter,
                                         @RequestParam(required = false, defaultValue = "") String value) {
        var jobs = jobService.findAll(filter, value);
        return jobs;
    }

    @GetMapping("/my-jobs")
    public List<JobSimpleDto> getMyJobs(){
        var jobs = jobService.findMyJobs();
        return jobs;
    }

    @GetMapping("/{id}")
    public JobDto getJob(@PathVariable int id) {
        return jobService.findById(id);
    }

    @PostMapping
    public JobDto addJob(@RequestBody JobDto job) {
        return jobService.addJob(job);
    }

    @PutMapping("/{id}")
    public JobDto updateJob(@PathVariable int id, @RequestBody JobDto job) {
        return jobService.updateJob(id, job);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable int id) {
        jobService.deleteJob(id);
    }
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_FACULTY','ROLE_ADMIN')")
    @GetMapping("/last-ten")
    public List<JobSimpleDto> getLastTenJobs() {
        return jobService.getLastTenJobs();
    }

    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_FACULTY','ROLE_ADMIN')")
    @GetMapping("/last-ten-applied")
    public List<JobApplication> getLastTenAppliedJobs() {
        var resp = jobService.getLastTenAppliedJobs();
        return  resp;
    }

    @GetMapping("/tags/{filter}")
    public List<Tag> findTagsbyTag(@PathVariable String filter) {
        var resp = jobService.findTagsByTag(filter);
        return  resp;
    }

    @PostMapping("/{jobId}/apply/{studentId}")
    public JobApplication applyJob(@PathVariable int jobId, @PathVariable int studentId){
        var res = jobService.applyJob(jobId, studentId);
        return res;
    }

    @PostMapping("/send-email/{recipient}/{status}")
    public String sendJobApplicationEmail(@PathVariable String recipient,@PathVariable String status){
        return jobService.sendJobApplicationEmail(recipient,status);
    }

    @PostMapping("/send-notification/{recipient}/{status}")
    public String sendJobApplicationNotificationEmail(@PathVariable String recipient,@PathVariable String status){
        return jobService.sendJobApplicationNotificationEmail(recipient,status);
    }
    @GetMapping("/my-applied-jobs")
    public List<JobApplicationDto> myJobApplications(){
        return jobService.myJobApplications();
    }

    @GetMapping("/per-location")
    public List<JobCount> noOfJobsPerLocation(){
        return jobService.noOfJobsPerLocation();
    }
}
