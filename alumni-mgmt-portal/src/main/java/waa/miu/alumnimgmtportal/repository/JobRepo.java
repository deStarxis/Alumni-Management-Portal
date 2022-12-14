package waa.miu.alumnimgmtportal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import waa.miu.alumnimgmtportal.entity.Job;
import waa.miu.alumnimgmtportal.entity.dto.JobDto;
import waa.miu.alumnimgmtportal.entity.dto.JobSimpleDto;
import waa.miu.alumnimgmtportal.model.JobCount;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<Job, Integer> {
    List<Job> findByAddress_StateIgnoreCase(String state);
    List<Job> findByAddress_StateIgnoreCaseAndAddress_CityContainingIgnoreCase(String state, String city);
    List<Job> findByCompany_NameContainingIgnoreCase(String companyName);
    List<Job> findByStudent_Id(int studentId);
    Page<Job> findAll(Pageable pageable);
    List<Job> findJobsByTags_TagEquals(String tag);


    @Query(value =
            "SELECT "+
                    " new waa.miu.alumnimgmtportal.model.JobCount(count(j.id), j.address.city, j.address.state)" +
                    "FROM Job j inner join Address as a on j.address.id=a.id " +
                    "GROUP By j.address.city,j.address.state"
    )
    List<JobCount> noOfJobsPerAddress();


}
