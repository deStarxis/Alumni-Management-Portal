package waa.miu.alumnimgmtportal.service;

import waa.miu.alumnimgmtportal.entity.JobApplication;
import waa.miu.alumnimgmtportal.entity.dto.JobApplicationDto;
import waa.miu.alumnimgmtportal.entity.Tag;
import waa.miu.alumnimgmtportal.entity.dto.JobDto;
import waa.miu.alumnimgmtportal.entity.dto.JobSimpleDto;
import waa.miu.alumnimgmtportal.model.JobCount;

import java.util.List;

public interface JobService {
    List<JobSimpleDto> findAll(String string,String value);
    List<JobSimpleDto> findMyJobs();
    JobDto addJob(JobDto job);
    JobDto updateJob(int id, JobDto job);
    void deleteJob(int id);
    JobDto findById(int id);
    List<JobSimpleDto> getLastTenJobs();
    List<JobApplication> getLastTenAppliedJobs();
    List<Tag> findTagsByTag(String filter);
    JobApplication applyJob(int jobId, int studentId);
    List<JobApplicationDto> myJobApplications();
    String sendJobApplicationEmail(String recipent, String status);

    String sendJobApplicationNotificationEmail(String recipent, String status);
    List<JobCount> noOfJobsPerLocation();
}
