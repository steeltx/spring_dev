package com.portfolio.portfolio_backend.controller;

import com.portfolio.portfolio_backend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final IPersonalInfoService personalInfoService;
    private final IEducationService educationService;
    private final ISkillService skillService;
    private final IExperienceService experienceService;
    private final IProjectService projectService;

    @GetMapping("/")
    public String showIndex(Model model){
        System.out.println("Mostrando el inicio");
        model.addAttribute("personalInfo", personalInfoService.findAll().getFirst());
        model.addAttribute("experience", experienceService.findAll());
        model.addAttribute("education", educationService.findAll());
        model.addAttribute("skills", skillService.findAll());
        model.addAttribute("projects", projectService.findAll());
        return "index";
    }

}
