package com.gaatvul.bugtracker.Rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

public class ExistingUsersRowMapper implements RowMapper<String> {

    @Override
    @Nullable
    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        return rs.getString("existing_users");
    }

}
