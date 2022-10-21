package com.gaatvul.bugtracker.Rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.gaatvul.bugtracker.Entities.BugReportEntity;

public class BugReportRowMapper implements RowMapper<BugReportEntity>  {

    @Override
    @Nullable
    public BugReportEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        BugReportEntity bugReport = new BugReportEntity();

        bugReport.setId(rs.getInt("report_id"));
        bugReport.setTitle(rs.getString("report_title"));
        bugReport.setDescription(rs.getString("description"));
        bugReport.setProjectAssignedTo(rs.getString("project_name"));
        bugReport.setReporter(rs.getString("reporter"));
        bugReport.setAssignee(rs.getString("assignee"));
        bugReport.setCriticality(rs.getString("criticality_name"));
        bugReport.setCategory(rs.getString("category_name"));
        bugReport.setPriority(rs.getString("priority_name"));
        bugReport.setStatus(rs.getString("status_name"));
        bugReport.setCreatedOn(rs.getObject("date_created", Timestamp.class));

        return bugReport;
    }

}
