package com.gaatvul.bugtracker.DTOs;

import java.sql.Timestamp;

import com.gaatvul.bugtracker.POJOs.UserProfile;

public class UserAccountDTO {

    private long id;
    private UserProfile userProfile;
    private String team;
    private String role;
    private boolean isAdmin;
    private String password;
    private boolean isEnabled;
    private Timestamp dateCreated;

    public UserAccountDTO() {
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

    public boolean isAdmin() {
        return (role.equals("Administrator"));
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

}
