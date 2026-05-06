package com.portfolio.portfolio_backend.rest;

import com.portfolio.portfolio_backend.model.PersonalInfo;
import com.portfolio.portfolio_backend.service.IPersonalInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/info")
public class PersonalInfoRestController {

    private final IPersonalInfoService personalInfoService;

    public PersonalInfoRestController(IPersonalInfoService personalInfoService) {
        this.personalInfoService = personalInfoService;
    }

    @GetMapping("/all")
    public List<PersonalInfo> getAllPersonalInfo(){
        return personalInfoService.findAll();
    }

    @GetMapping("/{id}")
    public PersonalInfo getPersonalInfoById(@PathVariable Long id){
        Optional<PersonalInfo> info = personalInfoService.findById(id);
        if(info.isPresent()){
            return info.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID no encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<PersonalInfo> createPersonalInfo(@RequestBody PersonalInfo personalInfo){
        PersonalInfo newPersonalInfo = personalInfoService.save(personalInfo);
        return new ResponseEntity<>(newPersonalInfo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public PersonalInfo update(@PathVariable Long id, @RequestBody PersonalInfo personalInfo){
        personalInfo.setId(id);
        return personalInfoService.save(personalInfo);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        personalInfoService.deleteById(id);
    }

}
