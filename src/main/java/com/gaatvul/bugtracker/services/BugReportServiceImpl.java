package com.gaatvul.bugtracker.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gaatvul.bugtracker.DAOs.BugReportDAO;
import com.gaatvul.bugtracker.DTOs.BugReportDTO;
import com.gaatvul.bugtracker.DTOs.CommentDTO;
import com.gaatvul.bugtracker.DTOs.UserAccountDTO;
import com.gaatvul.bugtracker.Entities.BugReportEntity;
import com.gaatvul.bugtracker.Entities.ChangeEntity;
import com.gaatvul.bugtracker.Entities.CommentEntity;
import com.gaatvul.bugtracker.POJOs.Change;

@Service
public class BugReportServiceImpl implements BugReportService {

    @Autowired
    private BugReportDAO bugReportDAO;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public List<BugReportEntity> loadListOfBugReports() {

        return bugReportDAO.loadListOfBugReports();
    }

    @Override
    public BugReportEntity getBugReportById(int id) {

        return bugReportDAO.getBugReportById(id);
    }

    @Override
    public List<CommentEntity> getBugReportCommentsWithId(int id) {

        return bugReportDAO.getBugReportCommentsWithId(id);
    }

    @Override
    public List<String> loadListOfAllProjects() {

        return bugReportDAO.loadListOfAllProjects();
    }

    @Override
    public void saveNewCommentToDatabase(CommentDTO addedComment) {

        bugReportDAO.saveNewCommentToDatabase(addedComment);
    }

    @Override
    public void saveNewBugReportToDatabase(BugReportDTO bugReportToBeSaved) {

        bugReportDAO.saveNewBugReportToDatabase(bugReportToBeSaved);
    }

    @Override
    public List<String> loadListofExistingUsers() {

        return bugReportDAO.loadListofExistingUsers();
    }

    @Override
    public void saveEditedBugReportToDatabase(BugReportEntity editedBugReport) {

        BugReportEntity existingBugReport = bugReportDAO.getBugReportById(editedBugReport.getId());

        findChangesInBugReport(editedBugReport, existingBugReport);

        bugReportDAO.saveEditedBugReportToDatabase(editedBugReport);
    }

    private void findChangesInBugReport(BugReportEntity editedBugReport, BugReportEntity existingBugReport) {

        List<Change> attributeChanges = new ArrayList<>();
        HashMap<String, String> existingBugReportAttributes = existingBugReport.getAllAttributes();
        HashMap<String, String> editedBugReportAttributes = editedBugReport.getAllAttributes();

        for (Map.Entry<String, String> entry : existingBugReportAttributes.entrySet()) {

            String userFullName = getLoggedInUserAccountDetails().getFirstName() + " "
                    + getLoggedInUserAccountDetails().getLastName();

            if (entry.getValue() == null) {
                entry.setValue("null");
            }

            if (!entry.getValue().equals(editedBugReportAttributes.get(entry.getKey()))) {

                attributeChanges.add(
                        new Change(entry.getKey(), entry.getValue(),
                                editedBugReportAttributes.get(entry.getKey()), userFullName,
                                existingBugReport.getId()));
            }
        }

        saveChangesToDatabase(attributeChanges);
    }

    private void saveChangesToDatabase(List<Change> attributeChanges) {

        bugReportDAO.saveChangesToDatabase(attributeChanges);
    }

    @Override
    public List<ChangeEntity> loadListOfBugReportChangesWithId(int id) {

        return bugReportDAO.loadListOfBugReportChangesWithId(id);
    }

    @Override
    public List<Integer> getCriticalityCount() {

        return bugReportDAO.getCriticalityCount();
    }

    @Override
    public List<Integer> getCategoryCount() {

        return bugReportDAO.getCategoryCount();
    }

    @Override
    public List<Integer> getStatusCount() {

        return bugReportDAO.getStatusCount();
    }

    @Override
    public List<Integer> getPriorityCount() {

        return bugReportDAO.getPriorityCount();
    }

    private UserAccountDTO getLoggedInUserAccountDetails() {
        return userDetailsService.loadUserAccountDetailsByUsername(getCurrentAuthentication().getName());
    }

    private Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
