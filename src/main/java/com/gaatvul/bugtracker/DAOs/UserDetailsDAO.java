package com.gaatvul.bugtracker.DAOs;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.gaatvul.bugtracker.DTOs.NewUserFormDTO;
import com.gaatvul.bugtracker.DTOs.UpdateUserProfileAsAdminDTO;
import com.gaatvul.bugtracker.DTOs.UpdateUserProfileDTO;
import com.gaatvul.bugtracker.DTOs.UserAccountDTO;

public interface UserDetailsDAO {
    
    public UserDetails retrieveUserByEmailAddress(String emailAddress);

    public UserAccountDTO retrieveUserAccountDetailsByEmailAddress(String emailAddress);

    public void saveNewUserToDatabase(NewUserFormDTO newUser);

    public void updateUserProfile(UpdateUserProfileDTO updatedUserProfile);

    public List<UserAccountDTO> loadAllUserAccounts();

    public UserAccountDTO loadUserAccountById(int id);

    public List<String> loadAllTeams();

    public void updateUserProfileAsAdmin(UpdateUserProfileAsAdminDTO updatedUserProfile);
}
