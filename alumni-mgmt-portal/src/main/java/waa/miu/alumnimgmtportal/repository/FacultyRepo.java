package waa.miu.alumnimgmtportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import waa.miu.alumnimgmtportal.entity.Faculty;

@Repository
public interface FacultyRepo extends JpaRepository<Faculty, Integer> {
}
