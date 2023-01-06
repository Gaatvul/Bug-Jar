package com.gaatvul.bugtracker.DTOs;

import javax.validation.Valid;

import com.gaatvul.bugtracker.POJOs.UserProfile;

public class UpdateUserProfileDTO {

    long id;

    @Valid
    private UserProfile userProfile;

    public UpdateUserProfileDTO() {
        this.userProfile = new UserProfile();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    
    public String getFullName() {
        return userProfile.getFullName();
    }

    public String getEmailAddress() {
        return userProfile.getEmailAddress();
    }

    public void setEmailAddress(String emailAddress) {
        userProfile.setEmailAddress(emailAddress);
    }
   
}
