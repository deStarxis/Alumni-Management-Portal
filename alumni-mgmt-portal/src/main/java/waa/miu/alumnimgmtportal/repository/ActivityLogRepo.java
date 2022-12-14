package waa.miu.alumnimgmtportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import waa.miu.alumnimgmtportal.entity.ActivityLog;

@Repository
public interface ActivityLogRepo extends JpaRepository<ActivityLog, Integer> {
}
