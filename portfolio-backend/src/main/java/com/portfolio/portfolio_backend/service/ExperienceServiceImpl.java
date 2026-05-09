package com.portfolio.portfolio_backend.service;

import com.portfolio.portfolio_backend.exception.ValidationException;
import com.portfolio.portfolio_backend.model.Experience;
import com.portfolio.portfolio_backend.repository.IExperienceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceServiceImpl implements IExperienceService {

    private final IExperienceRepository experienceRepository;
    private final Validator validator;

    public ExperienceServiceImpl(IExperienceRepository experienceRepository, Validator validator) {
        this.experienceRepository = experienceRepository;
        this.validator = validator;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Experience> findById(Long id) {
        return experienceRepository.findById(id);
    }

    @Override
    @Transactional
    public Experience save(Experience experience) {
        BindingResult result = new BeanPropertyBindingResult(experience,"experience");
        validator.validate(experience, result);
        if(result.hasErrors()){
            throw new ValidationException(result);
        }
        return experienceRepository.save(experience);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        experienceRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Experience> findExperienceByPersonalInfoId(Long personalInfoId) {
        return experienceRepository.findByPersonalInfoId(personalInfoId);
    }
}
