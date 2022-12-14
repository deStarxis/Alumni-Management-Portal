package waa.miu.alumnimgmtportal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import waa.miu.alumnimgmtportal.entity.dto.AddressDto;
import waa.miu.alumnimgmtportal.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
@CrossOrigin
public class AddressController {
    private  final AddressService addressService;
    @GetMapping
    public List<AddressDto> findAll(){
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public AddressDto findById(@PathVariable int id){
        return addressService.findById(id);
    }

    @PostMapping
    public AddressDto save(@RequestBody AddressDto company){
        return addressService.save(company);


    }

    @DeleteMapping("/{id}")
    public void Delete(@PathVariable int id){
        addressService.deleteById(id);
    }


    @PutMapping("/{id}")
    public AddressDto update(@PathVariable int id, @RequestBody AddressDto AddressDto){
        return addressService.update(id,AddressDto);

    }


}
