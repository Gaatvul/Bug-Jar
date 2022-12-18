package com.gaatvul.bugtracker.DTOs;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.gaatvul.bugtracker.POJOs.UserPassword;
import com.gaatvul.bugtracker.POJOs.UserProfile;

@Component
public class NewUserFormDTO {

    @Valid
    private UserProfile userProfile;
    @Valid
    private UserPassword userPassword;

    public NewUserFormDTO() {
        this.userProfile = new UserProfile();
        this.userPassword = new UserPassword();
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
    
    public String getFirstName() {
        return userProfile.getFirstName();
    }

    public void setFirstName(String firstName) {
        userProfile.setFirstName(firstName);
    }

    public String getLastName() {
        return userProfile.getLastName();
    }

    public void setLastName(String lastName) {
        userProfile.setLastName(lastName);
    }

    public String getEmailAddress() {
        return userProfile.getEmailAddress();
    }

    public void setEmailAddress(String emailAddress) {
        userProfile.setEmailAddress(emailAddress);
    }

    public UserPassword getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(UserPassword userPassword) {
        this.userPassword = userPassword;
    }

    public String getPassword() {
        return userPassword.getPassword();
    }

    public void setPassword(String password) {
        userPassword.setPassword(password);
    }

    public String getConfirmPassword() {
        return userPassword.getConfirmPassword();
    }

    public void setConfirmPassword(String confirmPassword) {
        userPassword.setConfirmPassword(confirmPassword);
    }

    public boolean passwordIsMismatchedWithConfirm() {
        return userPassword.passwordIsMismatchedWithConfirm();
    }

}
