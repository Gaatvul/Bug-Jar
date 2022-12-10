package com.gaatvul.bugtracker.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gaatvul.bugtracker.DTOs.NewUserFormDTO;
import com.gaatvul.bugtracker.DTOs.UserAccountDTO;
import com.gaatvul.bugtracker.services.UserDetailsServiceImpl;

@Controller
public class UserAccountController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping(value = "/signup")
    public String getSignUpForm(Model model) {

        NewUserFormDTO newUser = new NewUserFormDTO();

        model.addAttribute("newUser", newUser);

        return "newUserAccount";
    }

    @PostMapping(value = "/signup")
    public String signUpNewUser(@Valid @ModelAttribute("newUser") NewUserFormDTO newUser, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("newUser", newUser);

            return "newUserAccount";
        }

        System.out.println("New User created");
        userDetailsService.saveNewUserToDatabase(newUser);

        return "redirect:/login";
    }

    @GetMapping(value = "/profile")
    public String getUserProfile(Model model) {

        model.addAttribute("userDetails", getLoggedInUserAccountDetails());

        return "userProfileView";
    }

    private UserAccountDTO getLoggedInUserAccountDetails() {
        return userDetailsService.loadUserAccountDetailsByUsername(getCurrentAuthentication().getName());
    }

    private Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
