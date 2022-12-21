package com.gaatvul.bugtracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gaatvul.bugtracker.DAOs.UserDetailsDAOImpl;
import com.gaatvul.bugtracker.DTOs.NewUserFormDTO;
import com.gaatvul.bugtracker.DTOs.UpdateUserProfileAsAdminDTO;
import com.gaatvul.bugtracker.DTOs.UpdateUserProfileDTO;
import com.gaatvul.bugtracker.DTOs.UserAccountDTO;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsDAOImpl userAccountDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userAccountDAO.retrieveUserByEmailAddress(username);
    }

    public UserAccountDTO loadUserAccountDetailsByUsername(String username) throws UsernameNotFoundException {

        return userAccountDAO.retrieveUserAccountDetailsByEmailAddress(username);
    }

    public void saveNewUserToDatabase(NewUserFormDTO newUser) {

        userAccountDAO.saveNewUserToDatabase(newUser);
    }

    public void updateUserProfile(UpdateUserProfileDTO updatedUserProfile) {

        userAccountDAO.updateUserProfile(updatedUserProfile);
    }

    public List<UserAccountDTO> loadAllUserAccounts() {
        return userAccountDAO.loadAllUserAccounts();
    }

    public UserAccountDTO loadUserAccountById(int id) {
        return userAccountDAO.loadUserAccountById(id);
    }

    public List<String> loadAllTeams() {
        return userAccountDAO.loadAllTeams();
    }

    public void updateUserProfileAsAdmin(UpdateUserProfileAsAdminDTO updatedUserProfile) {
        userAccountDAO.updateUserProfileAsAdmin(updatedUserProfile);
    }

}
