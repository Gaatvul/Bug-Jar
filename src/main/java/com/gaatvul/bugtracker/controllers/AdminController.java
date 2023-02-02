package com.gaatvul.bugtracker.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gaatvul.bugtracker.DTOs.UpdateUserProfileAsAdminDTO;
import com.gaatvul.bugtracker.services.UserDetailsServiceImpl;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static String notEnoughPrivilagesMessage = 
        "Unable to complete action. Your account does not have enough privilages.";

    @GetMapping(value = "/allUserAccounts")
    public String getAllUserAccountsPage(Model model) {

        model.addAttribute("userDetails", userDetailsService.getLoggedInUserAccountDetails());
        model.addAttribute("userAccounts", userDetailsService.loadAllUserAccounts());

        return "allUserAccounts";
    }

    @GetMapping(value = "/allUserAccounts/view/{id}")
    public String getUserProfilePage(@PathVariable int id, Model model) {

        model.addAttribute("userDetails", userDetailsService.getLoggedInUserAccountDetails());
        model.addAttribute("accountDetails", userDetailsService.loadUserAccountById(id));

        return "userProfileAsAdminView";
    }

    @GetMapping(value = "/allUserAccounts/edit/{id}")
    public String geteditUserProfilePage(@PathVariable int id, Model model) {

        model.addAttribute("userDetails", userDetailsService.getLoggedInUserAccountDetails());
        model.addAttribute("accountDetails", userDetailsService.loadUserAccountById(id));
        model.addAttribute("allTeams", userDetailsService.loadAllTeams());

        return "editUserProfileAsAdminView";
    }

    @PostMapping(value = "/allUserAccounts/save/{id}")
    public String saveUpdatedUserProfile(
            @Valid @ModelAttribute("accountDetails") UpdateUserProfileAsAdminDTO updatedUserProfile,
            BindingResult bindingResult, @PathVariable int id, Model model) {

        if ("admin.demo@gmail.com"
                .equalsIgnoreCase(userDetailsService.getLoggedInUserAccountDetails().getEmailAddress())) {
            bindingResult.addError(new FieldError("accountDetails",
                    "userProfile.lastName", null, false, null,
                    null, notEnoughPrivilagesMessage));
        }

        if (bindingResult.hasErrors()) {

            model.addAttribute("userDetails", userDetailsService.getLoggedInUserAccountDetails());
            model.addAttribute("accountDetails", updatedUserProfile);
            model.addAttribute("allTeams", userDetailsService.loadAllTeams());

            return "editUserProfileAsAdminView";
        }

        userDetailsService.updateUserProfileAsAdmin(updatedUserProfile);

        return "redirect:/admin/allUserAccounts";
    }
}
