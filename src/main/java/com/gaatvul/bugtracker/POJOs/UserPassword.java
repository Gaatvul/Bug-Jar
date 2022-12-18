package com.gaatvul.bugtracker.POJOs;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

@Component
public class UserPassword {
    
    @NotBlank(message = "Uh oh, looks like password is invalid!")
    private String password;

    @NotBlank(message = "Uh oh, looks like confirm password is invalid")
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean passwordIsMismatchedWithConfirm() {
        return (!password.equals(confirmPassword));
    }

}
