package com.gaatvul.bugtracker.Rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.gaatvul.bugtracker.Entities.ChangeEntity;

public class ChangeEntityRowMapper implements RowMapper<ChangeEntity> {

    @Override
    @Nullable
    public ChangeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        ChangeEntity change = new ChangeEntity();

        change.setAttributeName(rs.getString("value_type"));
        change.setExistingValue(rs.getString("old_value"));
        change.setNewValue(rs.getString("new_value"));
        change.setUser(rs.getString("account_name"));
        change.setCreatedOn(rs.getTimestamp("date_created"));
        

        return change;
    }

}
