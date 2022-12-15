package waa.miu.alumnimgmtportal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import waa.miu.alumnimgmtportal.entity.Role;
import waa.miu.alumnimgmtportal.repository.RoleRepo;
import waa.miu.alumnimgmtportal.service.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    @Override
    public Role findRoleByRole(String role) {
        return roleRepo.findRoleByRoleIgnoreCase(role);
    }
}
