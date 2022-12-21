package com.gaatvul.bugtracker.DAOs;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.gaatvul.bugtracker.DTOs.NewUserFormDTO;
import com.gaatvul.bugtracker.DTOs.UpdateUserProfileAsAdminDTO;
import com.gaatvul.bugtracker.DTOs.UpdateUserProfileDTO;
import com.gaatvul.bugtracker.DTOs.UserAccountDTO;
import com.gaatvul.bugtracker.Rowmappers.UserAccountDetailsRowmapper;

@Repository
public class UserDetailsDAOImpl implements UserDetailsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails retrieveUserByEmailAddress(String emailAddress) {

        UserAccountDTO userAccount = retrieveUserAccountDetailsByEmailAddress(emailAddress);

        return User
                .withUsername(userAccount.getEmailAddress())
                .password(userAccount.getPassword())
                .roles(userAccount.getRole())
                .build();
    }

    @Override
    public UserAccountDTO retrieveUserAccountDetailsByEmailAddress(String emailAddress)
            throws UsernameNotFoundException {

        String sqlToRetrieveUserByEmailAddress = "call retrieve_account_by_email(?);";

        List<UserAccountDTO> userAccounts = jdbcTemplate.query(sqlToRetrieveUserByEmailAddress,
                new UserAccountDetailsRowmapper(), emailAddress);

        if (userAccounts.isEmpty()) {
            throw new UsernameNotFoundException("Account with [" + emailAddress + "] could not be found");
        }

        return userAccounts.get(0);
    }

    @Override
    public void saveNewUserToDatabase(NewUserFormDTO newUser) {

        String sqlToInsertNewUserIntoDatabase = "CALL insert_new_user(?, ?, ?, ?);";

        jdbcTemplate.update(sqlToInsertNewUserIntoDatabase, ps -> {

            ps.setString(1, newUser.getFirstName());
            ps.setString(2, newUser.getLastName());
            ps.setString(3, newUser.getEmailAddress());
            ps.setString(4, newUser.getPassword());
        });
    }

    @Override
    public void updateUserProfile(UpdateUserProfileDTO updatedUserProfile) {

        String sqlToUpdateUserProfile = "UPDATE user_accounts SET first_name = ?, last_name = ?, email_address = ? "
                + "WHERE account_id = ?";

        jdbcTemplate.update(sqlToUpdateUserProfile, ps -> {

            ps.setString(1, updatedUserProfile.getFirstName());
            ps.setString(2, updatedUserProfile.getLastName());
            ps.setString(3, updatedUserProfile.getEmailAddress());
            ps.setLong(4, updatedUserProfile.getId());
        });
    }

    @Override
    public List<UserAccountDTO> loadAllUserAccounts() {

        String sqlToLoadAllUserAccounts = "call retrieve_all_user_accounts;";

        return jdbcTemplate.query(sqlToLoadAllUserAccounts, new UserAccountDetailsRowmapper());
    }

    @Override
    public UserAccountDTO loadUserAccountById(int id)
            throws UsernameNotFoundException {

        String sqlToLoadAllUserAccounts = "call retrieve_user_account_by_id(?);";

        List<UserAccountDTO> userAccounts = jdbcTemplate.query(sqlToLoadAllUserAccounts,
                new UserAccountDetailsRowmapper(), id);

        if (userAccounts.isEmpty()) {
            throw new UsernameNotFoundException("Account with id: [" + id + "] could not be found");
        }

        return userAccounts.get(0);
    }

    @Override
    public List<String> loadAllTeams() {

        String sqlToLoadAllTeams = "SELECT team_name FROM project_teams";

        return jdbcTemplate.query(sqlToLoadAllTeams, (ResultSet rs, int rowNum) -> rs.getString("team_name"));
    }

    @Override
    public void updateUserProfileAsAdmin(UpdateUserProfileAsAdminDTO updatedUserProfile) {

        String sqlToUpdateUserProfile = "UPDATE user_accounts "
                + "SET first_name = ?, last_name = ?, email_address = ?, "
                + "team_id = (SELECT team_id FROM project_teams WHERE team_name = ?), "
                + "role_id = (SELECT role_id FROM account_roles WHERE role_name = ?) WHERE account_id = ?";

        jdbcTemplate.update(sqlToUpdateUserProfile, ps -> {
            ps.setString(1, updatedUserProfile.getFirstName());
            ps.setString(2, updatedUserProfile.getLastName());
            ps.setString(3, updatedUserProfile.getEmailAddress());
            ps.setString(4, updatedUserProfile.getTeam());
            ps.setString(5, updatedUserProfile.getRole());
            ps.setLong(6, updatedUserProfile.getId());
        });
    }

}
