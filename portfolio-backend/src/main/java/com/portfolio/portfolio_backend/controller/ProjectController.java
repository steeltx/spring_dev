package com.portfolio.portfolio_backend.controller;

import com.portfolio.portfolio_backend.dto.ProjectDto;
import com.portfolio.portfolio_backend.dto.ProjectMapper;
import com.portfolio.portfolio_backend.model.Project;
import com.portfolio.portfolio_backend.service.FileStorageService;
import com.portfolio.portfolio_backend.service.IProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final IProjectService projectService;
    private final FileStorageService fileStorageService;

    @GetMapping
    public String getAll(Model model) {
        List<ProjectDto> projects = projectService.findAll().stream().map(ProjectMapper::toDto).toList();
        model.addAttribute("projects", projects);
        return "projects/list";
    }

    @GetMapping("/new-project")
    public String showForm(Model model){
        model.addAttribute("projectDto", new ProjectDto());
        return "projects/form-project";
    }

    @PostMapping("/save")
    public String saveProject(@Valid @ModelAttribute("projectDto") ProjectDto projectDto, @RequestParam("file") MultipartFile file){
        try {
            String imageUrl = fileStorageService.storeFile(file);
            projectDto.setImageUrl(imageUrl);
            Project project = ProjectMapper.toProject(projectDto);
            projectService.save(project);
            return "redirect:/projects";
        } catch (IOException e) {
            return "error-page";
        }
    }

}
