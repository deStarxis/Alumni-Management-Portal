package waa.miu.alumnimgmtportal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import waa.miu.alumnimgmtportal.entity.Address;
import waa.miu.alumnimgmtportal.entity.Person;
import waa.miu.alumnimgmtportal.entity.dto.PersonDto;
import waa.miu.alumnimgmtportal.helper.EmailService;
import waa.miu.alumnimgmtportal.repository.AddressRepo;
import waa.miu.alumnimgmtportal.repository.FacultyRepo;
import waa.miu.alumnimgmtportal.repository.PersonRepo;
import waa.miu.alumnimgmtportal.repository.StudentRepo;
import waa.miu.alumnimgmtportal.security.MyUserDetails;
import waa.miu.alumnimgmtportal.service.PersonService;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepo personRepo;
    private final StudentRepo studentRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JavaMailSender javaMailSender;

    private final ModelMapper modelMapper;
    private final String STUDENT_ROLE = "Student";
    private final String FACULTY_ROLE = "Faculty";
    private final String ADMIN_ROLE = "Admin";


    private final FacultyRepo facultyRepo;
    private final AddressRepo addressRepo;

    @Override
    public Person findPersonByUsername(String username) {
        return personRepo.findPersonByUsernameIgnoreCase(username);
    }

    @Override
    public PersonDto findById() {
        PersonDto personDto = new PersonDto();
        int userId = ((MyUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getUserId();
        Person person = personRepo.findById(userId).get();
        if (person.getRoles().stream().findFirst().get().getRole().equalsIgnoreCase(STUDENT_ROLE)) {
            personDto = modelMapper.map(studentRepo.findById(userId).get(), PersonDto.class);
        } else if (person.getRoles().stream().findFirst().get().getRole().equalsIgnoreCase(FACULTY_ROLE)) {
            personDto = modelMapper.map(facultyRepo.findById(userId).get(), PersonDto.class);
        } else if (person.getRoles().stream().findFirst().get().getRole().equalsIgnoreCase(ADMIN_ROLE)) {
            personDto = modelMapper.map(personRepo.findById(userId).get(), PersonDto.class);
        }
        return personDto;
    }

    @Override
    public PersonDto getById(int id) {
        var person=personRepo.findById(id).orElseThrow(()->new RuntimeException("Person not found"));;
        return modelMapper.map(person,PersonDto.class);
    }

    @Override
    public void updateAddress(int id, Address address) {
        Person person = personRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        Address add = addressRepo.save(address);
        person.setId(id);
        person.setAddress(add);
        personRepo.save(person);


    }

    @Override
    public void changeStatus(int id, Boolean status) {
        Person person = personRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        person.setActive(status ? false : true);
        person.setId(id);
        personRepo.save(person);

    }

    @Override
    public String sendPasswordResetEmail(int id,String recipient) {
        EmailService emailService = new EmailService(javaMailSender);
        Person person;
        String password = "12345";
        person = personRepo.findById(id).get();
        person.setPassword(bCryptPasswordEncoder.encode(password));
        personRepo.save(person);
        String message = "Your password has been reset successfully";
        String message2 = "Your new password is".concat(password).concat(". Change it after logging in.");
        String finalMessage = message.concat(" "+message2);
        return emailService.sendEmail(recipient,"Password Reset",finalMessage);
    }


}
