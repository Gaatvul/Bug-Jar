package com.gaatvul.bugtracker.DAOs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gaatvul.bugtracker.DTOs.BugReportDTO;
import com.gaatvul.bugtracker.DTOs.CommentDTO;
import com.gaatvul.bugtracker.Entities.BugReportEntity;
import com.gaatvul.bugtracker.Entities.CommentEntity;
import com.gaatvul.bugtracker.Rowmappers.BugReportRowMapper;
import com.gaatvul.bugtracker.Rowmappers.CommentRowMapper;
import com.gaatvul.bugtracker.Rowmappers.ExistingUsersRowMapper;
import com.gaatvul.bugtracker.Rowmappers.ProjectRowMapper;

@Repository
public class BugReportDAOImpl implements BugReportDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<BugReportEntity> loadListOfBugReports() {

        List<BugReportEntity> listOfBugReportsInDatabase = new ArrayList<>();

        String sqlToRetrieveAllBugReports = "CALL retrieve_all_bug_reports();";

        listOfBugReportsInDatabase = jdbcTemplate.query(sqlToRetrieveAllBugReports, new BugReportRowMapper());

        return listOfBugReportsInDatabase;
    }

    @Override
    public BugReportEntity getBugReportById(int id) {

        BugReportEntity requestedBugReport = new BugReportEntity();

        String sqlToFetchBugReportById = "CALL select_bug_report_by_id(?);";

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

        String sqlToRetrieveAllProjects = "SELECT * FROM projects;";

        allProjects = jdbcTemplate.query(sqlToRetrieveAllProjects, new ProjectRowMapper());

        return allProjects;
    }

    @Override
    public void saveNewCommentToDatabase(CommentDTO addedComment) {

        String sqlToInsertNewCommentIntoDatabase = "INSERT INTO report_comments (comment, report_id, account_id, date_created) values"
                + " ((?),(?),(SELECT (account_id) FROM user_accounts WHERE first_name=(?) AND last_name=(?)), current_time);";

        jdbcTemplate.update(sqlToInsertNewCommentIntoDatabase, extractArgumentsFromComment(addedComment));
    }

    private Object[] extractArgumentsFromComment(CommentDTO comment) {

        Object[] sqlArgs = { comment.getCommentText(), comment.getReport_id(),
                comment.getUserFirstName(), comment.getUserSecondName() };

        return sqlArgs;
    }

    @Override
    public void saveNewBugReportToDatabase(BugReportDTO bugReportToBeSaved) {

        String sqlToInsertNewBugReportIntoDatabase = "CALL insert_new_bug_report(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sqlToInsertNewBugReportIntoDatabase, extractArgumentsFromBugReport(bugReportToBeSaved));

    }

    private Object[] extractArgumentsFromBugReport(BugReportDTO bugReport) {

        Object[] sqlArgs = { bugReport.getTitle(), bugReport.getDescription(), bugReport.getProjectAssignedTo(),
                bugReport.getReporter(), bugReport.getAssignee(), bugReport.getOwner(), bugReport.getCriticality(),
                bugReport.getCategory(), bugReport.getPriority(), bugReport.getStatus() };

        return sqlArgs;
    }

    @Override
    public List<String> loadListofExistingUsers() {

        List<String> existingUsers = new ArrayList<>();

        String sqlToRetrieveExistingUsers = "SELECT concat(first_name, \" \", last_name) as existing_users FROM user_accounts;";

        existingUsers = jdbcTemplate.query(sqlToRetrieveExistingUsers, new ExistingUsersRowMapper());

        return existingUsers;
    }

}
