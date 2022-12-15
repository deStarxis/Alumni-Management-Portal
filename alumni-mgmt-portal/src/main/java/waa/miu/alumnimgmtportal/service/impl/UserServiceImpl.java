package waa.miu.alumnimgmtportal.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import waa.miu.alumnimgmtportal.entity.Student;
import waa.miu.alumnimgmtportal.entity.Verification;
import waa.miu.alumnimgmtportal.entity.Person;
import waa.miu.alumnimgmtportal.entity.Faculty;
import waa.miu.alumnimgmtportal.entity.Role;
import waa.miu.alumnimgmtportal.entity.dto.PasswordDto;
import waa.miu.alumnimgmtportal.entity.dto.PasswordResetDto;
import waa.miu.alumnimgmtportal.entity.dto.RegisterDto;
import waa.miu.alumnimgmtportal.entity.dto.RoleDto;
import waa.miu.alumnimgmtportal.model.LoginRequest;
import waa.miu.alumnimgmtportal.model.LoginResponse;
import waa.miu.alumnimgmtportal.repository.PersonRepo;
import waa.miu.alumnimgmtportal.repository.RoleRepo;
import waa.miu.alumnimgmtportal.repository.VerificationRepo;
import waa.miu.alumnimgmtportal.security.JwtHelper;
import waa.miu.alumnimgmtportal.security.MyUserDetails;
import waa.miu.alumnimgmtportal.service.FacultyService;
import waa.miu.alumnimgmtportal.service.RoleService;
import waa.miu.alumnimgmtportal.service.StudentService;
import waa.miu.alumnimgmtportal.service.UserService;

import javax.mail.internet.MimeMessage;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final RoleRepo roleRepo;
    private final String STUDENT_ROLE = "Student";
    private final String FACULTY_ROLE =  "Faculty";
    private final ModelMapper modelMapper;
    private final StudentService studentService;
    private final FacultyService facultyService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
//    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtHelper jwtHelper;
    private final VerificationRepo verificationRepo;

    private final PersonRepo personRepo;
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;

    @Value("${spring.clientBaseURL}") private String clientBaseURL;

    @Override
    public void register(RegisterDto person) {
        decoratePerson(person);
        if(person.getRole().getRole().equalsIgnoreCase(STUDENT_ROLE)){
            registerStudent(person);
        }else if(person.getRole().getRole().equalsIgnoreCase(FACULTY_ROLE)){
            registerFaculty(person);
        }
    }

    @Override
    public String sendPasswordResetMail(PasswordResetDto message) {
        int personId = getUser(message.getRecipient());
        UUID token = saveToken(personId).getToken();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage,"UTF-8");
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(message.getRecipient());
            mimeMessageHelper.setText(getHtml(clientBaseURL, token));
            mimeMessageHelper.setSubject("Reset Your Password");
            javaMailSender.send(mimeMessage);
            return "Mail Sent Successfully";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String resetPassword(String token, PasswordDto passwordDto) {
        try {
            Person person;
            UUID uuid = UUID.fromString(token);
            int personId = verificationRepo.findVerificationByToken(uuid).getPersonId();
            person = personRepo.findById(personId).get();
            person.setPassword(bCryptPasswordEncoder.encode(passwordDto.getPassword()));
            personRepo.save(person);
            return "Password Reset Successfully";
        }catch (Exception ex){
            return ex.getMessage();
        }
    }

    public int getUser(String username){
        return personRepo.findPersonByUsernameIgnoreCase(username).getId();
    }
    private Verification saveToken(int personId){
        Verification verification = new Verification();
        verification.setPersonId(personId);
        verification.setToken(UUID.randomUUID());
        verificationRepo.save(verification);
        return verification;
    }
    private void decoratePerson(RegisterDto person){
        String password = person.getPassword();
        person.setPassword(bCryptPasswordEncoder.encode(password));
        person.setRoles(new ArrayList<>(Arrays.asList(person.getRole())));
//        Role role = roleService.findRoleByRole(person.getRole());
//        List<Role> roles = new ArrayList<>(Arrays.asList(role));
//        person.setRoles(roles);
    }

    private void registerStudent(RegisterDto person){
        Student student =  modelMapper.map(person, Student.class);//not beig mapped by modelMapper
        studentService.save(student);
    }

    private void registerFaculty(RegisterDto person){
        Faculty faculty = modelMapper.map(person, Faculty.class);
        facultyService.save(faculty);
    }


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication result;
        try {
            result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            log.info("Bad Credentials");
            throw e;
        } catch (Exception ex) {
            log.info(ex.getMessage());
            throw ex;
        }
        final String accessToken = jwtHelper.generateToken(loginRequest.getUsername(),
                new HashMap<String, Object>() {{
                    put("firstname", ((MyUserDetails) result.getPrincipal()).getFirstname());
                    put("lastname", ((MyUserDetails) result.getPrincipal()).getLastname());
                    put("sub", loginRequest.getUsername());
                    put("userId",((MyUserDetails) result.getPrincipal()).getUserId());
                    put("rol",mapListofRole(((MyUserDetails) result.getPrincipal()).getRoles()));
                }}
        );
        var loginResponse = new LoginResponse(accessToken);
        return loginResponse;
    }

    private String getHtml(String clientBaseURL, UUID uuid){
        return String.format(
                """
                 Click this link to reset your password : \"%s%s\"
                 """,clientBaseURL,uuid.toString()
        ) ;
    }

    public List<RoleDto> findAllRoles()
    {

        return roleRepo.findAll().stream().filter(r->!r.isDeleted()).filter(r->!"Admin".equals(r.getRole())).map(p -> modelMapper.map(p, RoleDto.class)).toList();
    }


    private List<RoleDto> mapListofRole(List<Role> roles)
    {
        return roles.stream().map(r->modelMapper.map(r,RoleDto.class)).toList();
    }
}
