package com.portfolio.portfolio_backend.mapper;

import com.portfolio.portfolio_backend.dto.ProjectDto;
import com.portfolio.portfolio_backend.model.Project;

public class ProjectMapper {

    public static ProjectDto toDto(Project project){
        if(project == null){
            return null;
        }
        ProjectDto dto = new ProjectDto();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setDescription(project.getDescription());
        dto.setImageUrl(project.getImageUrl());
        dto.setProjectUrl(project.getProjectUrl());
        dto.setPersonalInfoId(project.getPersonalInfoId());
        return dto;
    }

    public static Project toProject(ProjectDto dto){
        if (dto == null) {
            return null;
        }
        Project project = new Project();
        project.setId(dto.getId());
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setImageUrl(dto.getImageUrl());
        project.setProjectUrl(dto.getProjectUrl());
        project.setPersonalInfoId(dto.getPersonalInfoId());
        return project;
    }

}
