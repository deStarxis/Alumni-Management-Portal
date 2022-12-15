package waa.miu.alumnimgmtportal.service;

import waa.miu.alumnimgmtportal.entity.Address;
import waa.miu.alumnimgmtportal.entity.Person;
import waa.miu.alumnimgmtportal.entity.dto.PersonDto;
import waa.miu.alumnimgmtportal.entity.dto.RegisterDto;

public interface PersonService {
    Person findPersonByUsername(String username);
    PersonDto findById();

    PersonDto getById(int id);
    void updateAddress(int id, Address address);
    void changeStatus(int id, Boolean status);

    String sendPasswordResetEmail(int id,String recipient);
}
