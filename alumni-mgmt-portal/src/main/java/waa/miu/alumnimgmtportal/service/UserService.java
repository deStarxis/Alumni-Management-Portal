package waa.miu.alumnimgmtportal.service;

import waa.miu.alumnimgmtportal.entity.dto.PasswordDto;
import waa.miu.alumnimgmtportal.entity.dto.PasswordResetDto;
import waa.miu.alumnimgmtportal.entity.dto.RegisterDto;
import waa.miu.alumnimgmtportal.entity.dto.RoleDto;
import waa.miu.alumnimgmtportal.model.LoginRequest;
import waa.miu.alumnimgmtportal.model.LoginResponse;

import java.util.List;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
    void register(RegisterDto person);
    List<RoleDto> findAllRoles();
    String sendPasswordResetMail(PasswordResetDto message);
    String resetPassword(String token, PasswordDto password);
}
