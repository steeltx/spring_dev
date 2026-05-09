package com.portfolio.portfolio_backend.service;

import com.portfolio.portfolio_backend.exception.ValidationException;
import com.portfolio.portfolio_backend.model.PersonalInfo;
import com.portfolio.portfolio_backend.repository.IPersonalInfoRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalInfoServiceImpl implements IPersonalInfoService {

    private final IPersonalInfoRepository personalInfoRepository;
    private final Validator validator;

    @Override
    @Transactional
    public PersonalInfo save(PersonalInfo personalInfo) {
        BindingResult result = new BeanPropertyBindingResult(personalInfo,"personalInfo");
        validator.validate(personalInfo, result);
        if(result.hasErrors()){
            throw new ValidationException(result);
        }
        return personalInfoRepository.save(personalInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonalInfo> findById(Long id) {
        return personalInfoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonalInfo> findAll() {
        return personalInfoRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        personalInfoRepository.deleteById(id);
    }
}
