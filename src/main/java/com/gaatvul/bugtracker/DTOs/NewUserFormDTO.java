package com.gaatvul.bugtracker.DTOs;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewUserFormDTO {

    @NotEmpty(message = "Uh oh, looks like there's no first name filled in!")
    @NotNull(message = "Uh oh, looks like this field is null")
    private String firstName;

    @NotNull(message = "Uh oh, looks like this field is null")
    private String lastName;

    @NotEmpty(message = "Uh oh, looks like there's no email address filled in!")
    @Email(message = "Uh oh, looks like your email address is not quite correct!")
    @NotNull(message = "Uh oh, looks like this field is null")
    private String emailAddress;

    @NotEmpty(message = "Uh oh, looks like there's no password filled in!")
    @NotNull(message = "Uh oh, looks like this field is null")
    private String password;
    
    @NotEmpty(message = "Uh oh, looks like there's no confirm password filled in!")
    @NotNull(message = "Uh oh, looks like this field is null")
    private String confirmPassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

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

    public boolean isSamePassword() {
        return (password.equals(confirmPassword));
    }

}
