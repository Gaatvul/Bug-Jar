package com.gaatvul.bugtracker.DTOs;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class NewUserFormDTO {

    @NotEmpty(message = "Uh oh, looks like there's no first name filled in!")
    private String firstName;

    private String lastName;

    @NotEmpty(message = "Uh oh, looks like there's no email address filled in!")
    @Email(message = "Uh oh, looks like your email address is not quite correct!")
    private String emailAddress;

    @NotEmpty(message = "Uh oh, looks like there's no password filled in!")
    private String password;

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

}
