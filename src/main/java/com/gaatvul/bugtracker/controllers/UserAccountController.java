package com.gaatvul.bugtracker.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gaatvul.bugtracker.DTOs.NewUserFormDTO;
import com.gaatvul.bugtracker.DTOs.UpdatePasswordDTO;
import com.gaatvul.bugtracker.DTOs.UpdateUserProfileDTO;
import com.gaatvul.bugtracker.services.UserDetailsServiceImpl;

@Controller
public class UserAccountController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static String notEnoughPrivilagesMessage = "Unable to complete action. Your account does not have enough privilages.";

    @GetMapping(value = "/signup")
    public String getSignUpForm(Model model) {

        model.addAttribute("newUser", new NewUserFormDTO());

        return "newUserAccount";
    }

    @PostMapping(value = "/signup")
    public String signUpNewUser(@Valid @ModelAttribute("newUser") NewUserFormDTO newUser,
            BindingResult bindingResult, Model model) {

        if (newUser.passwordIsMismatchedWithConfirm()) {
            bindingResult.addError(new FieldError("newUser", "userPassword.password", null,
                    false, null, null, "New Passwords do not match"));
            bindingResult.addError(new FieldError("newUser", "userPassword.confirmPassword", null,
                    false, null, null, "New Passwords do not match"));
        }

        if (bindingResult.hasErrors()) {

            model.addAttribute("newUser", newUser);

            return "newUserAccount";
        }

        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);

        userDetailsService.saveNewUserToDatabase(newUser);

        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String getUserProfile(Model model) {

        model.addAttribute("userDetails", userDetailsService.getLoggedInUserAccountDetails());

        return "userProfileView";
    }

    @GetMapping("/profile/edit-profile")
    public String getEditProfilePage(Model model) {

        UpdateUserProfileDTO updatedUserProfile = new UpdateUserProfileDTO();
        updatedUserProfile.setFirstName(userDetailsService.getLoggedInUserAccountDetails().getFirstName());
        updatedUserProfile.setLastName(userDetailsService.getLoggedInUserAccountDetails().getLastName());
        updatedUserProfile.setEmailAddress(userDetailsService.getLoggedInUserAccountDetails().getEmailAddress());

        model.addAttribute("userDetails", userDetailsService.getLoggedInUserAccountDetails());
        model.addAttribute("updatedUserProfile", updatedUserProfile);

        return "editUserProfileView";
    }

    @PostMapping("/profile/update-profile-changes")
    public String updateUserProfile(
            @Valid @ModelAttribute("updatedUserProfile") UpdateUserProfileDTO updatedUserProfile,
            BindingResult bindingResult, Model model) {

        if ("None".equals(userDetailsService.getLoggedInUserAccountDetails().getRole()) || "admin.demo@gmail.com"
                .equalsIgnoreCase(userDetailsService.getLoggedInUserAccountDetails().getEmailAddress())) {
            bindingResult.addError(
                    new FieldError("userDetails", "userProfile.emailAddress", null, false,
                            null, null, notEnoughPrivilagesMessage));
        }

        updatedUserProfile.setId(userDetailsService.getLoggedInUserAccountDetails().getId());

        if (bindingResult.hasErrors()) {

            model.addAttribute("userDetails", userDetailsService.getLoggedInUserAccountDetails());
            model.addAttribute("updatedUserProfile", updatedUserProfile);

            return "editUserProfileView";
        }

        userDetailsService.updateUserProfile(updatedUserProfile);

        return "redirect:/profile?profile-update-success";
    }

    @GetMapping(value = "/profile/update-password")
    public String getUpdateUserPasswordPage(Model model) {

        model.addAttribute("userDetails", userDetailsService.getLoggedInUserAccountDetails());
        model.addAttribute("userPassword", new UpdatePasswordDTO());

        return "editUserPasswordView";
    }

    @PostMapping(value = "/profile/update-password")
    public String updateUserPassword(
            @Valid @ModelAttribute("userPassword") UpdatePasswordDTO updatedUserPassword,
            BindingResult bindingResult, Model model) {

        if ("None".equals(userDetailsService.getLoggedInUserAccountDetails().getRole()) || "admin.demo@gmail.com"
                .equalsIgnoreCase(userDetailsService.getLoggedInUserAccountDetails().getEmailAddress())) {
            bindingResult.addError(
                    new FieldError("userPassword", "confirmPassword", null,
                            false, null, null, notEnoughPrivilagesMessage));
        }

        updatedUserPassword.setId(userDetailsService.getLoggedInUserAccountDetails().getId());

        if (!passwordEncoder.matches(updatedUserPassword.getOldPassword(),
                userDetailsService.getLoggedInUserAccountDetails().getPassword())) {
            bindingResult.addError(new FieldError("userPassword", "oldPassword", null,
                    false, null, null, "Incorrect Password"));
        }

        if (updatedUserPassword.passwordIsMismatchedWithConfirm()) {
            bindingResult.addError(new FieldError("userPassword", "password", null,
                    false, null, null, "New Passwords do not match"));
            bindingResult.addError(new FieldError("userPassword", "confirmPassword", null,
                    false, null, null, "New Passwords do not match"));
        }

        if (bindingResult.hasErrors()) {

            model.addAttribute("userDetails", userDetailsService.getLoggedInUserAccountDetails());
            model.addAttribute("userPassword", updatedUserPassword);

            return "editUserPasswordView";
        }

        String encodedPassword = passwordEncoder.encode(updatedUserPassword.getPassword());
        updatedUserPassword.setPassword(encodedPassword);

        String encodedConfirmPassword = passwordEncoder.encode(updatedUserPassword.getConfirmPassword());
        updatedUserPassword.setConfirmPassword(encodedConfirmPassword);

        userDetailsService.updateUserPassword(updatedUserPassword);

        return "redirect:/profile";
    }

}
