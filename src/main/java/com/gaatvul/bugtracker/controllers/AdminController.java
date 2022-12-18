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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gaatvul.bugtracker.DTOs.UpdateUserProfileDTO;
import com.gaatvul.bugtracker.DTOs.UserAccountDTO;
import com.gaatvul.bugtracker.services.UserDetailsServiceImpl;




@Controller
public class AdminController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping(value = "/allUserAccounts")
    public String getAllUserAccountsPage(Model model) {

        model.addAttribute("userDetails", getLoggedInUserAccountDetails());
        model.addAttribute("userAccounts", userDetailsService.loadAllUserAccounts());

        return "allUserAccounts";
    }

    @GetMapping(value="/allUserAccounts/view/{id}")
    public String getUserProfilePage(@PathVariable int id, Model model) {

        model.addAttribute("userDetails", getLoggedInUserAccountDetails());
        model.addAttribute("accountDetails", userDetailsService.loadUserAccountById(id));

        return "userProfileAsAdminView";
    }

    @GetMapping(value="/allUserAccounts/edit/{id}")
    public String geteditUserProfilePage(@PathVariable int id, Model model) {

        model.addAttribute("userDetails", getLoggedInUserAccountDetails());
        model.addAttribute("accountDetails", userDetailsService.loadUserAccountById(id));
        model.addAttribute("allTeams", userDetailsService.loadAllTeams());

        return "editUserProfileAsAdminView";
    }

    @PostMapping(value="/allUserAccounts/save/{id}")
    public String saveUpdatedUserProfile(@Valid @ModelAttribute("accountDetails") UpdateUserProfileDTO updatedUserProfile,
    BindingResult bindingResult, @PathVariable int id, Model model) {
        
        
        
        return "redirect: /allUserAccounts";
    }
    
    //TODO: create POST mapping for saving changes to account details.
    

    // TODO: Use retrieve all accounts -> filter list for Teams & roles
    

    private UserAccountDTO getLoggedInUserAccountDetails() {
        return userDetailsService.loadUserAccountDetailsByUsername(getCurrentAuthentication().getName());
    }

    private Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
