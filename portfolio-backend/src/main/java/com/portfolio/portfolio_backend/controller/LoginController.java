package com.portfolio.portfolio_backend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model,
                                Authentication authentication){
        if(authentication != null){
           return "redirect:/education"; 
        }
        if(error != null){
            model.addAttribute("error","Datos incorrectos");
        }
        return "auth/form-login";
    }

}
