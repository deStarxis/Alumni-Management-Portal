package waa.miu.alumnimgmtportal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import waa.miu.alumnimgmtportal.entity.Address;
import waa.miu.alumnimgmtportal.entity.dto.PersonDto;
import waa.miu.alumnimgmtportal.service.PersonService;
@CrossOrigin
@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor

public class PersonController {
    private final PersonService personService;

    @GetMapping("/profile")
    public PersonDto findById(){
        var x = personService.findById();
        return x;

    }

    @PutMapping("/updateAddress/{id}")
    public void updateAddress(@PathVariable int id, @RequestBody Address address){
        personService.updateAddress(id,address);
    }


    @PutMapping("/changeStatus/{id}/active/{status}")
public void changeStatus(@PathVariable int id, @PathVariable Boolean status){
        personService.changeStatus(id,status);
    }

    @GetMapping("/{id}")
    public PersonDto getById(@PathVariable int id){
        return personService.getById(id);
    }

    @PostMapping("/send-password-reset-email/{id}/{recipient}")
    public String sendPasswordResetEmail(@PathVariable int id,@PathVariable String recipient){
        return personService.sendPasswordResetEmail(id,recipient);
    }
}

