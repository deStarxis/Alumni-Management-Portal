package waa.miu.alumnimgmtportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import waa.miu.alumnimgmtportal.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    Role findRoleByRoleIgnoreCase(String role);
}
