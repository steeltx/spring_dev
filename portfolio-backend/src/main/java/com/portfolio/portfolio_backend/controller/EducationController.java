package com.portfolio.portfolio_backend.controller;

import com.portfolio.portfolio_backend.dto.EducationDto;
import com.portfolio.portfolio_backend.mapper.EducationMapper;
import com.portfolio.portfolio_backend.model.Education;
import com.portfolio.portfolio_backend.service.IEducationService;
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
@RequestMapping("/education")
@RequiredArgsConstructor
public class EducationController {

    private final IEducationService educationService;

    @GetMapping
    public String listEducation(Model model){
        List<Education> educationList = educationService.findAll();
        List<EducationDto> dtos = educationList.stream().map(EducationMapper::toDto).toList();
        model.addAttribute("education",dtos);
        return "education/list-education";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model){
        EducationDto education = new EducationDto();
        education.setStartDate(LocalDate.now());
        model.addAttribute("educationDto", education);
        return "education/form-education";
    }

    @PostMapping("/save")
    public String saveEducation(@Valid @ModelAttribute("educationDto") EducationDto educationDto, BindingResult result,
                                 RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "education/form-education";
        }
        try{
            Education education = EducationMapper.toEntity(educationDto);
            educationService.save(education);
            redirectAttributes.addFlashAttribute("message","Guardado correcto");
            return "redirect:/education";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error","Error al guardar: "+e.getMessage());
            return "redirect:/education";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        Optional<Education> educationOptional = educationService.findById(id);
        if(educationOptional.isPresent()){
            EducationDto educationDto = EducationMapper.toDto(educationOptional.get());
            model.addAttribute("educationDto",educationDto);
            return "education/form-education";
        }else{
            model.addAttribute("error","Educación no encontrada con ID : "+id);
            return "redirect:/education";
        }
    }

    @GetMapping("/personal/{personalInfoId}")
    public String listEducationByPersonalInfoId(@PathVariable Long id, Model model){
        List<Education> educationList = educationService.findEducationByPersonalInfoId(id);
        List<EducationDto> dto = educationList.stream().map(EducationMapper::toDto).toList();
        model.addAttribute("education",dto);
        return "education/list-education";
    }

    @PostMapping("/delete/{id}")
    public String deleteEducation(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try{
            educationService.deleteById(id);
            redirectAttributes.addFlashAttribute("message","Educación eliminada con exito");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error","Error al eliminar la educación");
        }
        return "redirect:/education";
    }

}
