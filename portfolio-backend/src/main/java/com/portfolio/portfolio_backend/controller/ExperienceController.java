package com.portfolio.portfolio_backend.controller;

import com.portfolio.portfolio_backend.dto.ExperienceDto;
import com.portfolio.portfolio_backend.mapper.ExperienceMapper;
import com.portfolio.portfolio_backend.model.Experience;
import com.portfolio.portfolio_backend.service.IExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/experience")
@RequiredArgsConstructor
public class ExperienceController {

    private final IExperienceService experienceService;

    @GetMapping
    public String listExperience(Model model){
        List<Experience> experienceList = experienceService.findAll();
        List<ExperienceDto> dtos = experienceList.stream().map(ExperienceMapper::toDto).toList();
        model.addAttribute("experience",dtos);
        return "experience/list-experience";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model){
        ExperienceDto experience = new ExperienceDto();
        experience.setStartDate(LocalDate.now());
        model.addAttribute("experienceDto", experience);
        return "experience/form-experience";
    }

    @PostMapping("/save")
    public String saveExperience(@Valid @ModelAttribute("experienceDto") ExperienceDto experienceDto, BindingResult result,
                                 RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "experience/form-experience";
        }
        try{
            Experience experience = ExperienceMapper.toEntity(experienceDto);
            experienceService.save(experience);
            redirectAttributes.addFlashAttribute("message","Guardado correcto");
            return "redirect:/experience";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error","Error al guardar: "+e.getMessage());
            return "redirect:/experience";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        Optional<Experience> experienceOptional = experienceService.findById(id);
        if(experienceOptional.isPresent()){
            ExperienceDto experienceDto = ExperienceMapper.toDto(experienceOptional.get());
            model.addAttribute("experienceDto",experienceDto);
            return "experience/form-experience";
        }else{
            model.addAttribute("error","Experiencia no encontrada con ID : "+id);
            return "redirect:/experience";
        }
    }

    @GetMapping("/personal/{personalInfoId}")
    public String listExperienceByPersonalInfoId(@PathVariable Long id, Model model){
        List<Experience> experienceList = experienceService.findExperienceByPersonalInfoId(id);
        List<ExperienceDto> dto = experienceList.stream().map(ExperienceMapper::toDto).toList();
        model.addAttribute("experience",dto);
        return "experience/list-experience";
    }

    @PostMapping("/delete/{id}")
    public String deleteExperience(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try{
            experienceService.deleteById(id);
            redirectAttributes.addFlashAttribute("message","Experiencia eliminada con exito");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error","Error al eliminar la experiencia");
        }
        return "redirect:/experience";
    }

}
