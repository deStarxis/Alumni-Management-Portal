package waa.miu.alumnimgmtportal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import waa.miu.alumnimgmtportal.entity.JobApplication;
import waa.miu.alumnimgmtportal.entity.JobApplicationId;

import java.util.List;

@Repository
public interface JobApplicationRepo extends JpaRepository<JobApplication, JobApplicationId> {
    Page<JobApplication> findAll(Pageable pageable);
    List<JobApplication> findByStudent_Id(int id);
}
