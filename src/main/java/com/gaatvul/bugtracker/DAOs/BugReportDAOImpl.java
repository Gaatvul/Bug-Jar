package com.gaatvul.bugtracker.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gaatvul.bugtracker.DTOs.BugReportDTO;
import com.gaatvul.bugtracker.DTOs.CommentDTO;
import com.gaatvul.bugtracker.Entities.BugReportEntity;
import com.gaatvul.bugtracker.Entities.ChangeEntity;
import com.gaatvul.bugtracker.Entities.CommentEntity;
import com.gaatvul.bugtracker.POJOs.Change;
import com.gaatvul.bugtracker.Rowmappers.BugReportRowMapper;
import com.gaatvul.bugtracker.Rowmappers.ChangeEntityRowMapper;
import com.gaatvul.bugtracker.Rowmappers.CommentRowMapper;
import com.gaatvul.bugtracker.Rowmappers.ExistingUsersRowMapper;
import com.gaatvul.bugtracker.Rowmappers.ProjectRowMapper;

@Repository
public class BugReportDAOImpl implements BugReportDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    @Override
    public void saveEditedBugReportToDatabase(BugReportEntity editedBugReport) {

        String sqlToUpdateBugReportInDatabase = "CALL update_bug_report_with_id(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sqlToUpdateBugReportInDatabase, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                
                ps.setInt(1, editedBugReport.getId());
                ps.setString(2, editedBugReport.getTitle());
                ps.setString(3, editedBugReport.getDescription());
                ps.setString(4, editedBugReport.getProjectAssignedTo());
                ps.setString(5, editedBugReport.getReporter());
                ps.setString(6, editedBugReport.getAssignee());
                ps.setString(7, editedBugReport.getOwner());
                ps.setString(8, editedBugReport.getCriticality());
                ps.setString(9, editedBugReport.getCategory());
                ps.setString(10, editedBugReport.getPriority());
                ps.setString(11, editedBugReport.getStatus());
            }

        });
    }

    @Override
    public void saveChangesToDatabase(List<Change> attributeChanges) {

        String sqlToInsertReportChanges = "INSERT INTO report_changes (value_type, old_value, new_value, report_id, account_id, date_created) VALUES(?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

        jdbcTemplate.batchUpdate(sqlToInsertReportChanges,

                new BatchPreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {

                        ps.setString(1, attributeChanges.get(i).getAttributeName());
                        ps.setString(2, attributeChanges.get(i).getExistingValue());
                        ps.setString(3, attributeChanges.get(i).getNewValue());
                        ps.setInt(4, attributeChanges.get(i).getReport_id());
                        ps.setInt(5, getAccountIdFromName(attributeChanges.get(i).getUser()));
                    }

                    @Override
                    public int getBatchSize() {

                        return attributeChanges.size();
                    }

                });

    }

    public int getAccountIdFromName(String accountName) {

        String sqlToRetrieveAccountIdWithName = "SELECT account_id FROM user_accounts WHERE concat(first_name, \" \", last_name) = ?;";

        List<Integer> account_id = new ArrayList<Integer>();

        account_id = jdbcTemplate.query(sqlToRetrieveAccountIdWithName, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {

                ps.setString(1, accountName);
            }

        }, new RowMapper<Integer>() {

            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {

                return rs.getInt("account_id");
            }

        });

        return account_id.get(0).intValue();
    }

    @Override
    public List<ChangeEntity> loadListOfBugReportChangesWithId(int id) {
        
        List<ChangeEntity> bugReportChanges = new ArrayList<>();

        String sqlToRetrieveListOfBugReportChanges = "CALL retrieve_bug_report_changes_with_report_id(?);";

        bugReportChanges = jdbcTemplate.query(sqlToRetrieveListOfBugReportChanges, new PreparedStatementSetter(){

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {

                ps.setInt(1, id);
            }

        } , new ChangeEntityRowMapper());

        return bugReportChanges;
    }

}
