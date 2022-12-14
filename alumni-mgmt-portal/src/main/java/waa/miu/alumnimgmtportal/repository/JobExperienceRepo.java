package waa.miu.alumnimgmtportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import waa.miu.alumnimgmtportal.entity.JobExperience;

import java.util.List;

@Repository
public interface JobExperienceRepo extends JpaRepository<JobExperience, Integer> {


    List<JobExperience> findByStudent_Id(int studentId);
}
