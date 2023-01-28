package com.gaatvul.bugtracker.DTOs;

import javax.validation.Valid;

import com.gaatvul.bugtracker.POJOs.UserProfile;

public class UpdateUserProfileAsAdminDTO {

    private long id;
    @Valid private UserProfile userProfile;

    private String team;
    private String role;

    public UpdateUserProfileAsAdminDTO() {
        this.userProfile = new UserProfile();
    }
    
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
