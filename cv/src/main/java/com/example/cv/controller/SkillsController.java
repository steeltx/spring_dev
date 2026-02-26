package com.example.cv.controller;

import com.example.cv.model.Skill;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/skills")
public class SkillsController {

    private final List<Skill> skills = new ArrayList<>();

//    @GetMapping
//    public String showSkills(Model model){
//        model.addAttribute("skills",skills);
//        return "skills";
//    }

    @GetMapping
    public String showSkills(@RequestParam(defaultValue = "", required = false) String filter, Model model){
        List<Skill> skillsFilter = skills.stream()
                .filter(skill -> skill.getName().toLowerCase()
                .contains(filter.toLowerCase())).toList();
        model.addAttribute("skills",skillsFilter);
        model.addAttribute("filter",filter);
        return "skills";
    }

    @GetMapping("/id/{index}")
    public String showSkillDetail(@PathVariable int index, Model model){
        if(index>=0 && index< skills.size()){
            Skill skill = skills.get(index);
            model.addAttribute("skill",skill);
            return "skill-detail";
        }
        return "redirect:/skills";
    }

    @GetMapping("/filter/{name}/{level}")
    public String showFilteredSkill(@PathVariable String name, @PathVariable String level, Model model){
        List<Skill> skillsFilter = skills.stream()
                .filter(skill -> skill.getName().equalsIgnoreCase(name)
                        && skill.getLevel().equalsIgnoreCase(level))
                .toList();
        model.addAttribute("skills",skillsFilter);
        model.addAttribute("filterMessage","Filtro:"+name+"-"+level);
        return "skills";
    }

//    @GetMapping("/name/{name}")
//    public String showFilteredSkill(@PathVariable String name, Model model){
//        List<Skill> skillsFilter = skills.stream()
//                .filter(skill -> skill.getName().equalsIgnoreCase(name))
//                .toList();
//        if(skillsFilter.isEmpty()){
//            model.addAttribute("filterMessage","No se encontraron resultados para: "+name);
//            return "forward:/skills";
//        }
//        model.addAttribute("skills",skillsFilter);
//        model.addAttribute("filterMessage","Filtro:"+name);
//        return "skills";
//    }

//    @GetMapping("/name/{name}")
//    public String showFilteredSkill(@PathVariable String name, RedirectAttributes redirectAttributes){
//        List<Skill> skillsFilter = skills.stream()
//                .filter(skill -> skill.getName().equalsIgnoreCase(name))
//                .toList();
//        if(skillsFilter.isEmpty()){
//            redirectAttributes.addFlashAttribute("filterMessage","No se encontraron resultados para: "+name);
//            return "redirect:/skills?filter="+name;
//        }
//        redirectAttributes.addFlashAttribute("filterMessage","Filtro:"+name);
//        return "redirect:/skills?filter="+name;
//    }

    @GetMapping("/name/{name}")
    public String showFilteredSkill(@PathVariable String name, RedirectAttributes redirectAttributes){
        boolean hasResults = skills.stream().anyMatch(skill -> skill.getName().equalsIgnoreCase(name));
        if(!hasResults){
            redirectAttributes.addAttribute("filterMessage","Sin resultados para : "+name);
        }else{
            redirectAttributes.addAttribute("filterMessage","Filtro : "+name);
        }
        return "redirect:/skills?filter="+name;
    }

    @GetMapping("/new")
    public String showForm(Model model){
        model.addAttribute("skill", new Skill());
        return "add-skill";
    }

    @PostMapping("/add")
    public String addSkill(@ModelAttribute Skill skill){
        skills.add(skill);
        return "redirect:/skills";
    }

}
