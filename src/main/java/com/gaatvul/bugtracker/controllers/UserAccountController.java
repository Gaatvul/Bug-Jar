package com.gaatvul.bugtracker.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gaatvul.bugtracker.DTOs.UserAccountDTO;
import com.gaatvul.bugtracker.DTOs.NewUserFormDTO;
import com.gaatvul.bugtracker.DTOs.UpdatePasswordDTO;
import com.gaatvul.bugtracker.DTOs.UpdateUserProfileDTO;
import com.gaatvul.bugtracker.services.UserDetailsServiceImpl;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String signUpNewUser(@Valid @ModelAttribute("newUser") NewUserFormDTO newUser,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("newUser", newUser);

            return "newUserAccount";
        }

        userDetailsService.saveNewUserToDatabase(newUser);

        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String getUserProfile(Model model) {

        model.addAttribute("userDetails", getLoggedInUserAccountDetails());

        return "userProfileView";
    }

    @GetMapping("/profile/edit-profile")
    public String getEditProfilePage(Model model) {

        model.addAttribute("userDetails", getLoggedInUserAccountDetails());

        return "editUserProfileView";
    }

    @PostMapping("/profile/update-profile-changes")
    public String updateUserProfile(
            @Valid @ModelAttribute("userDetails") UpdateUserProfileDTO updatedUserProfile,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("userDetails", getLoggedInUserAccountDetails());

            return "editUserProfileView";
        }

        updatedUserProfile.setId(getLoggedInUserAccountDetails().getId());

        userDetailsService.updateUserProfile(updatedUserProfile);

        return "redirect:/profile?profile-update-success";
    }

    @GetMapping(value = "/profile/update-password")
    public String getUpdateUserPasswordPage(Model model) {

        model.addAttribute("userDetails", getLoggedInUserAccountDetails());
        model.addAttribute("userPassword", new UpdatePasswordDTO());

        return "editUserPasswordView";
    }

    @PostMapping(value = "/profile/update-password")
    public String updateUserPassword(
            @Valid @ModelAttribute("userPassword") UpdatePasswordDTO updatedUserPassword,
            BindingResult bindingResult, Model model) {

        if (!updatedUserPassword.getOldPassword().equals(getLoggedInUserAccountDetails().getPassword())) {
            bindingResult.addError(new FieldError("userPassword", "oldPassword", null, 
                    false, null, null, "Incorrect Password"));
        }

        if (updatedUserPassword.isMismatchedWithConfirm()) {
            bindingResult.addError(new FieldError("userPassword", "newPassword", null,
                    false, null, null, "New Passwords do not match"));
            bindingResult.addError(new FieldError("userPassword", "confirmNewPassword", null,
                    false, null, null, "New Passwords do not match"));
        }

        if (bindingResult.hasErrors()) {

            model.addAttribute("userDetails", getLoggedInUserAccountDetails());
            model.addAttribute("userPassword", updatedUserPassword);
            bindingResult.getAllErrors().forEach(System.out::println);

            return "editUserPasswordView";
        }

        return "redirect:/profile";
    }

    private UserAccountDTO getLoggedInUserAccountDetails() {
        return userDetailsService.loadUserAccountDetailsByUsername(getCurrentAuthentication().getName());
    }

    private Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
