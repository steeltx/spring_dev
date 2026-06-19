package com.portfolio.portfolio_backend.controller;

import com.portfolio.portfolio_backend.dto.SkillDto;
import com.portfolio.portfolio_backend.mapper.SkillMapper;
import com.portfolio.portfolio_backend.model.Skill;
import com.portfolio.portfolio_backend.service.ISkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/skills")
@RequiredArgsConstructor
public class SkillController {
    private final ISkillService skillService;

    @GetMapping
    public String listSkills(Model model){
        List<Skill> skills = skillService.findAll();
        List<SkillDto> skillDto = skills.stream().map(SkillMapper::toDto).toList();
        model.addAttribute("skills",skillDto);
        return "skills/list-skills";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model){
        model.addAttribute("skillDto",new SkillDto());
        return "skills/form-skill";
    }

    @PostMapping("/save")
    public String saveSkill(@Valid @ModelAttribute("skillDto") SkillDto skillDto, BindingResult result){
        if(result.hasErrors()){
            return "skill/form-skill";
        }
        try{
            Skill skill = SkillMapper.toSkill(skillDto);
            skillService.save(skill);
            return "redirect:/skills";
        }catch (Exception ex){
            return "error-page";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable  Long id, Model model){
        Optional<Skill> skillOptional = skillService.findById(id);
        if(skillOptional.isPresent()){
            SkillDto skillDto = SkillMapper.toDto(skillOptional.get());
            model.addAttribute("skillDto",skillDto);
            return "skills/form-skill";
        }else{
            model.addAttribute("errorMessage","Habilidad no encontrada con ID : "+id);
            return "redirect:/skills";
        }
    }

    @GetMapping("/personal/{personalInfoId}")
    public String listSkillsByPersonalInfoId(@PathVariable  Long personalInfoId, Model model){
        List<Skill> skills = skillService.findSkillByPersonalInfoId(personalInfoId);
        List<SkillDto> skillDto = skills.stream().map(SkillMapper::toDto).toList();
        model.addAttribute("skills",skillDto);
        return "skills/list-skills";
    }

    @PostMapping("/delete/{id}")
    public String deleteSkill(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try{
            skillService.deleteById(id);
            redirectAttributes.addFlashAttribute("message","Habilidad eliminada con exito");
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("error","Error al eliminar la habilidad");
        }
        return "redirect:/skills";
    }

}
