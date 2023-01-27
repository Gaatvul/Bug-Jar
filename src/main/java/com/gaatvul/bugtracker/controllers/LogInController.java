package com.gaatvul.bugtracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInController {

    @GetMapping(value = "/login")
    public String getLoginForm() {
        return "logInView";
    }
    
}
