package com.gaatvul.bugtracker.Rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.gaatvul.bugtracker.Entities.BugReportEntity;

public class BugReportRowMapper implements RowMapper<BugReportEntity>  {

    @Override
    @Nullable
    public BugReportEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        BugReportEntity bugReport = new BugReportEntity();



        return bugReport;
    }

}
