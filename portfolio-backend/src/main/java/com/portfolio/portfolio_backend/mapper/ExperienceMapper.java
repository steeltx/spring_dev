package com.portfolio.portfolio_backend.mapper;

import com.portfolio.portfolio_backend.dto.ExperienceDto;
import com.portfolio.portfolio_backend.model.Experience;

public class ExperienceMapper {

    public static ExperienceDto toDto(Experience experience){
        if(experience == null) {
            return null;
        }
        ExperienceDto dto = new ExperienceDto();
        dto.setId(experience.getId());
        dto.setJobTitle(experience.getJobTitle());
        dto.setDescription(experience.getDescription());
        dto.setCompanyName(experience.getCompanyName());
        dto.setStartDate(experience.getStartDate());
        dto.setEndDate(experience.getEndDate());
        dto.setPersonalInfoId(experience.getPersonalInfoId());
        return dto;
    }

    public static Experience toEntity(ExperienceDto dto){
        if(dto == null) {
            return null;
        }
        Experience experience = new Experience();
        experience.setId(dto.getId());
        experience.setJobTitle(dto.getJobTitle());
        experience.setDescription(dto.getDescription());
        experience.setCompanyName(dto.getCompanyName());
        experience.setStartDate(dto.getStartDate());
        experience.setEndDate(dto.getEndDate());
        experience.setPersonalInfoId(dto.getPersonalInfoId());
        return experience;
    }


}
