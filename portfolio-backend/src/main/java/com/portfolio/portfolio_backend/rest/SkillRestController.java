package com.portfolio.portfolio_backend.rest;

import com.portfolio.portfolio_backend.model.Skill;
import com.portfolio.portfolio_backend.service.ISkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skill")
public class SkillRestController {

    private final ISkillService skillService;

    public SkillRestController(ISkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<Skill> findAll(){
        return skillService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> findById(@PathVariable Long id){
        Optional<Skill> skill =  skillService.findById(id);
        if(skill.isPresent()){
            return new ResponseEntity<>(skill.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/personal/{id}")
    public List<Skill> findByPersonalInfoId(@PathVariable("id") Long personalInfoId){
        return skillService.findSkillByPersonalInfoId(personalInfoId);
    }

    @PostMapping
    public Skill save(@RequestBody Skill skill){
        return skillService.save(skill);
    }

    @PutMapping("/{id}")
    public Skill update(@PathVariable Long id, @RequestBody Skill skill){
        skill.setId(id);
        return skillService.save(skill);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        skillService.deleteById(id);
    }
}
