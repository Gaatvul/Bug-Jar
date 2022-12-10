package com.gaatvul.bugtracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gaatvul.bugtracker.services.UserDetailsServiceImpl;

@Controller
public class IndexController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping(value = "/")
    public String getIndex(Authentication auth, Model model) {

        model.addAttribute("userDetails", userDetailsService.loadUserAccountDetailsByUsername(auth.getName()));

        return "index";
    }

}
