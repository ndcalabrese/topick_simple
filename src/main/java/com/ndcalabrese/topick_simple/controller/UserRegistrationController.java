package com.ndcalabrese.topick_simple.controller;

import com.ndcalabrese.topick_simple.dto.UserRegistrationDto;
import com.ndcalabrese.topick_simple.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserRegistrationController {
    private final UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {

        return new UserRegistrationDto();
    }

    @GetMapping("/signup")
    public String showSignupPage() {

        return "signup";
    }

    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("user")
                                      UserRegistrationDto registrationDto) {
        userService.save(registrationDto);

        return "redirect:/login?SignupSuccess";
    }

}
