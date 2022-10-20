package com.gaatvul.bugtracker.DAOs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.gaatvul.bugtracker.Entities.BugReportEntity;
import com.gaatvul.bugtracker.Rowmappers.BugReportRowMapper;

@Repository
public class BugReportDAOImpl implements BugReportDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<BugReportEntity> loadListOfBugReports() {
        List<BugReportEntity> listOfBugReportsInDatabase = new ArrayList<>();

        // SimpleJdbcCall retrieveAllBugReports = new SimpleJdbcCall(jdbcTemplate).withSchemaName("bug-tracker")
        //         .withProcedureName("retrieve_all_bug_reports");

        // retrieveAllBugReports.execute();

        String sqlToRetrieveAllBugReports = "CALL retrieve_all_bug_reports()";

        listOfBugReportsInDatabase = jdbcTemplate.query(sqlToRetrieveAllBugReports, new BugReportRowMapper());

        return listOfBugReportsInDatabase;
    }

}
