package com.gaatvul.bugtracker.Rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.gaatvul.bugtracker.DTOs.UserAccountDTO;

public class UserAccountDetailsRowmapper implements RowMapper<UserAccountDTO> {

    @Override
    @Nullable
    public UserAccountDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        UserAccountDTO userAccountDetails = new UserAccountDTO();

        userAccountDetails.setId(rs.getInt("account_id"));
        userAccountDetails.setFirstName(rs.getString("first_name"));
        userAccountDetails.setLastName(rs.getString("last_name"));
        userAccountDetails.setEmailAddress(rs.getString("email_address"));
        userAccountDetails.setTeam(rs.getString("team_name"));
        userAccountDetails.setRole(rs.getString("role_name"));
        userAccountDetails.setPassword(rs.getString("password"));
        userAccountDetails.setEnabled((rs.getBoolean("enabled")));
        userAccountDetails.setDateCreated(rs.getObject("date_created", Timestamp.class));

        return userAccountDetails;
    }

}
