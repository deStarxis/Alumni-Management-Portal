package waa.miu.alumnimgmtportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import waa.miu.alumnimgmtportal.entity.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Integer> {
}
