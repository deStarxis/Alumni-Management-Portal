package waa.miu.alumnimgmtportal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import waa.miu.alumnimgmtportal.entity.dto.CVDto;
import waa.miu.alumnimgmtportal.service.CVService;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/cvs")
public class CVController {
    private final CVService cvService;
    @GetMapping
    public List<CVDto> getAllCVs(){
        return cvService.findAll();
    }
    @GetMapping("/{id}")
    public CVDto getCV(@PathVariable int id){
        return cvService.findById(id);
    }
    @PostMapping
    public CVDto addCV(@RequestBody CVDto cv){
        return cvService.addCV(cv);
    }
    @PutMapping("/{id}")
    public CVDto updateCV(@PathVariable int id, @RequestBody CVDto cv){
        return cvService.updateCV(id,cv);
    }
    @DeleteMapping("/{id}")
    public void deleteCV(@PathVariable int id){
        cvService.deleteCV(id);
    }
}
