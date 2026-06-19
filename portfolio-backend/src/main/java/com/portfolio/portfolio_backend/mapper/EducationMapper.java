package com.portfolio.portfolio_backend.mapper;

import com.portfolio.portfolio_backend.dto.EducationDto;
import com.portfolio.portfolio_backend.model.Education;

public class EducationMapper {

    public static EducationDto toDto(Education education){
        if(education == null){
            return null;
        }
        EducationDto dto = new EducationDto();
        dto.setId(education.getId());
        dto.setDegree(education.getDegree());
        dto.setDescription(education.getDescription());
        dto.setInstitution(education.getInstitution());
        dto.setStartDate(education.getStartDate());
        dto.setEndDate(education.getEndDate());
        dto.setPersonalInfoId(education.getPersonalInfoId());
        return dto;
    }

    public static Education toEntity(EducationDto dto){
        if (dto == null) {
            return null;
        }
        Education education = new Education();
        education.setId(dto.getId());
        education.setDegree(dto.getDegree());
        education.setDescription(dto.getDescription());
        education.setInstitution(dto.getInstitution());
        education.setStartDate(dto.getStartDate());
        education.setEndDate(dto.getEndDate());
        education.setPersonalInfoId(dto.getPersonalInfoId());
        return education;
    }
}
