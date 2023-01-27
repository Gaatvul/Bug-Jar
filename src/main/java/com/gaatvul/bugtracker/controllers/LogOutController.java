package com.gaatvul.bugtracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogOutController {

    @GetMapping(value = "/logout")
    public String getLoginForm() {
        return "logOutView";
    }
    
}