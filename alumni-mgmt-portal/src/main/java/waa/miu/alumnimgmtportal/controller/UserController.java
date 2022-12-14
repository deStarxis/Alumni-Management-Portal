package waa.miu.alumnimgmtportal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.miu.alumnimgmtportal.entity.dto.PasswordDto;
import waa.miu.alumnimgmtportal.entity.dto.PasswordResetDto;
import waa.miu.alumnimgmtportal.entity.dto.RegisterDto;
import waa.miu.alumnimgmtportal.entity.dto.RoleDto;
import waa.miu.alumnimgmtportal.model.LoginRequest;
import waa.miu.alumnimgmtportal.service.UserService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        var loginResponse = userService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterDto registerDto){
        userService.register(registerDto);
    }

    @PostMapping("/sendPasswordResetLink")
    public String sendPasswordResetMail(@RequestBody PasswordResetDto details)
    {
        return userService.sendPasswordResetMail(details);
    }

    @GetMapping("/roles")
    public List<RoleDto> findAllRoles(){
        return userService.findAllRoles();
    }
    @PostMapping("/reset-password/{resetToken}")
    public String resetPassword(@PathVariable String resetToken, @RequestBody PasswordDto password){
        return userService.resetPassword(resetToken,password);
    }

}
