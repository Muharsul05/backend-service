package ru.magarusik.microservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.magarusik.microservice.entity.UserEntity;
import ru.magarusik.microservice.service.UserService;

@Controller
@AllArgsConstructor
public class RegistrationController {
    @Autowired
    private UserService userService;
    @GetMapping("/registration")
    public String getRegistrationPage(Model model){
        model.addAttribute("user",new UserEntity());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") UserEntity userEntity, Model model){
        userService.saveUser(userEntity);
        return "redirect::/login";
    }
}
