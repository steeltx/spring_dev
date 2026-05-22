package com.portfolio.portfolio_backend.service;

import com.portfolio.portfolio_backend.exception.ValidationException;
import com.portfolio.portfolio_backend.model.Skill;
import com.portfolio.portfolio_backend.repository.ISkillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SkillServiceImplTest {
    @Mock
    private ISkillRepository skillRepository;
    @Mock
    private Validator validator;
    @InjectMocks
    private SkillServiceImpl skillService;

    @Test
    void testFindAllReturnsListOfSkills(){
        // arrange
        List<Skill> mockSkills = Arrays.asList(new Skill(), new Skill());
        when(skillRepository.findAll()).thenReturn(mockSkills);

        // action
        List<Skill> skills = skillService.findAll();

        // assert
        assertNotNull(skills);
        assertEquals(2, skills.size());
        verify(skillRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdReturnsSkillWhenFound(){
        Long id = 1L;
        Skill skillMock = new Skill();
        when(skillRepository.findById(id)).thenReturn(Optional.of(skillMock));

        Optional<Skill> skillOptional = skillService.findById(id);

        assertTrue(skillOptional.isPresent());
        assertEquals(skillMock, skillOptional.get());
        verify(skillRepository, times(1)).findById(id);
    }

    @Test
    void testSaveSkillThrowsExceptionWhenInvalid(){
        Skill invalidSkill = new Skill();
        doAnswer(invocationOnMock -> {
            BindingResult result = invocationOnMock.getArgument(1);
            result.rejectValue("name","NotBlank","El nombre no puede estar vacio");
            return null;
        }).when(validator).validate(any(Skill.class), any(BindingResult.class));

        assertThrows(ValidationException.class, () -> skillService.save(invalidSkill), "Debe lanzarse ValidationException" +
                " si el objeto no es valido");
        verify(skillRepository, never()).save(any(Skill.class));
    }

    @Test
    void testSaveSkillSavesValidSkill(){
        // preparacion
        Skill validSkill = new Skill(null,"Java",90,"fab fa-java",1L);
        when(skillRepository.save(any(Skill.class))).thenReturn(validSkill);
        doNothing().when(validator).validate(any(Skill.class),any(BindingResult.class));

        // accion
        Skill savedSkill = skillService.save(validSkill);

        // verificacion
        assertNotNull(savedSkill);
        verify(skillRepository, times(1)).save(validSkill);
    }

}
