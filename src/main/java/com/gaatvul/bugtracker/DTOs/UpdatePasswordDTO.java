package com.gaatvul.bugtracker.DTOs;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.gaatvul.bugtracker.POJOs.UserPassword;

public class UpdatePasswordDTO {

    private long id;

    @NotBlank(message = "Uh oh, looks like there's no password filled in!")
    private String oldPassword;

    @Valid
    private UserPassword userPassword;

    public UpdatePasswordDTO() {
        this.userPassword = new UserPassword();
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
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

    public UserPassword getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(UserPassword userPassword) {
        this.userPassword = userPassword;
    }

}
