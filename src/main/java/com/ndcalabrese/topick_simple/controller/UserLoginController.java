package com.ndcalabrese.topick_simple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

    @GetMapping("/login")
    public String showLoginPage() {

        return "login";
    }

//    @PostMapping("/login")
//    public String logInUser() {
//
//        return "redirect:/login?SignupSuccess";
//    }
}
