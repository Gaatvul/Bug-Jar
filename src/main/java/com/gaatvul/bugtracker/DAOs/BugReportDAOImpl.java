package com.gaatvul.bugtracker.DAOs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gaatvul.bugtracker.Entities.BugReportEntity;
import com.gaatvul.bugtracker.Entities.CommentEntity;
import com.gaatvul.bugtracker.Rowmappers.BugReportRowMapper;
import com.gaatvul.bugtracker.Rowmappers.CommentRowMapper;
import com.gaatvul.bugtracker.Rowmappers.ProjectRowMapper;

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

        String sqlToRetrieveAllBugReports = "CALL retrieve_all_bug_reports();";

        listOfBugReportsInDatabase = jdbcTemplate.query(sqlToRetrieveAllBugReports, new BugReportRowMapper());

        return listOfBugReportsInDatabase;
    }

    @Override
    public BugReportEntity getBugReportById(int id) {

        BugReportEntity requestedBugReport = new BugReportEntity();

        String sqlToFetchBugReportById = "CALL select_bug_report_by_id(?)";

        System.out.println("Fetching bug report with ID: " + id);

        requestedBugReport = jdbcTemplate.queryForObject(sqlToFetchBugReportById, new BugReportRowMapper(), id);

        return requestedBugReport;
    }

    @Override
    public List<CommentEntity> getBugReportCommentsWithId(int id) {
        
        List<CommentEntity> listOfBugReportComments = new ArrayList<>();

        String sqlToRetrieveAllBugReportComments = "CALL retrieve_all_bug_report_comments_with_id(?);";

        listOfBugReportComments = jdbcTemplate.query(sqlToRetrieveAllBugReportComments, new CommentRowMapper(), id);

        return listOfBugReportComments;
    }

    @Override
    public List<String> loadListOfAllProjects() {
        
        List<String> allProjects = new ArrayList<>();

        String sqlToRetrieveAllProjects = "CALL retrieve_all_projects;";

        allProjects = jdbcTemplate.query(sqlToRetrieveAllProjects, new ProjectRowMapper());

        return allProjects;
    }

}
