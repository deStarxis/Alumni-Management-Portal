package waa.miu.alumnimgmtportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import waa.miu.alumnimgmtportal.entity.Student;
import waa.miu.alumnimgmtportal.model.JobCount;
import waa.miu.alumnimgmtportal.model.StudentPerState;

import java.util.List;


@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    List<Student> findByMajorContainingIgnoreCase(String major);
    List<Student> findByFirstnameContaining(String name);
    List<Student> findByUniversityId(int universityId);
    List<Student> findByAddress_StateIgnoreCase(String state);

    List<Student> findByAddress_StateIgnoreCaseAndAddress_CityIgnoreCase(String state, String city);

    @Query(value =
            "SELECT "+
                    " new waa.miu.alumnimgmtportal.model.StudentPerState(count(s.id),s.address.state)" +
                    "FROM Student s inner join Address as a on s.address.id=a.id " +
                    "GROUP By s.address.state"
    )
    List<StudentPerState> noOfJobsPerState();
}
