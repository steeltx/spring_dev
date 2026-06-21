package com.portfolio.portfolio_backend.mapper;

import com.portfolio.portfolio_backend.dto.PersonalInfoDto;
import com.portfolio.portfolio_backend.model.PersonalInfo;

public class PersonalInfoMapper {

    public static PersonalInfoDto toDto(PersonalInfo personalInfo){
        if(personalInfo == null){
            return null;
        }
        PersonalInfoDto dto = new PersonalInfoDto();
        dto.setId(personalInfo.getId());
        dto.setFirstName(personalInfo.getFirstName());
        dto.setLastName(personalInfo.getLastName());
        dto.setTitle(personalInfo.getTitle());
        dto.setProfileDescription(personalInfo.getProfileDescription());
        dto.setProfileImageUrl(personalInfo.getProfileImageUrl());
        dto.setYearsOfExperience(personalInfo.getYearsOfExperience());
        dto.setEmail(personalInfo.getEmail());
        dto.setPhone(personalInfo.getPhone());
        dto.setLinkedinUrl(personalInfo.getLinkedinUrl());
        dto.setGithubUrl(personalInfo.getGithubUrl());
        return dto;
    }

    public static PersonalInfo toEntity(PersonalInfoDto dto){
        if(dto == null){
            return null;
        }
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setId(dto.getId());
        personalInfo.setFirstName(dto.getFirstName());
        personalInfo.setLastName(dto.getLastName());
        personalInfo.setTitle(dto.getTitle());
        personalInfo.setProfileDescription(dto.getProfileDescription());
        personalInfo.setProfileImageUrl(dto.getProfileImageUrl());
        personalInfo.setYearsOfExperience(dto.getYearsOfExperience());
        personalInfo.setEmail(dto.getEmail());
        personalInfo.setPhone(dto.getPhone());
        personalInfo.setLinkedinUrl(dto.getLinkedinUrl());
        personalInfo.setGithubUrl(dto.getGithubUrl());
        return personalInfo;
    }

}
