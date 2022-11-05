package com.gaatvul.bugtracker.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaatvul.bugtracker.DAOs.BugReportDAO;
import com.gaatvul.bugtracker.DTOs.BugReportDTO;
import com.gaatvul.bugtracker.DTOs.CommentDTO;
import com.gaatvul.bugtracker.Entities.BugReportEntity;
import com.gaatvul.bugtracker.Entities.CommentEntity;
import com.gaatvul.bugtracker.POJOs.Change;

@Service
public class BugReportServiceImpl implements BugReportService {

    @Autowired
    private BugReportDAO bugReportDAO;

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

        findChanges(editedBugReport);

        bugReportDAO.saveEditedBugReportToDatabase(editedBugReport);

    }

    private void findChanges(BugReportEntity editedBugReport) {

        List<Change> attributeChanges = new ArrayList<>();

        BugReportEntity existingBugReport = bugReportDAO.getBugReportById(editedBugReport.getId());

        if (editedBugReport.getTitle() != existingBugReport.getTitle()) {
            attributeChanges.add(new Change("title", existingBugReport.getTitle(), editedBugReport.getTitle(),
                    "TestAccount Developer", editedBugReport.getId()));
        }
        if (editedBugReport.getDescription() != existingBugReport.getDescription()) {
            attributeChanges.add(new Change("description", existingBugReport.getDescription(),
                    editedBugReport.getDescription(), "TestAccount Developer", editedBugReport.getId()));
        }
        if (editedBugReport.getAssignee() != existingBugReport.getAssignee()) {
            attributeChanges.add(new Change("assignee", existingBugReport.getAssignee(), editedBugReport.getAssignee(),
                    "TestAccount Developer", editedBugReport.getId()));
        }
        if (editedBugReport.getOwner() != existingBugReport.getOwner()) {
            attributeChanges.add(new Change("owner", existingBugReport.getOwner(), editedBugReport.getOwner(),
                    "TestAccount Developer", editedBugReport.getId()));
        }
        if (editedBugReport.getProjectAssignedTo() != existingBugReport.getProjectAssignedTo()) {
            attributeChanges.add(new Change("projectAssignedTo", existingBugReport.getProjectAssignedTo(),
                    editedBugReport.getProjectAssignedTo(), "TestAccount Developer", editedBugReport.getId()));
        }
        if (editedBugReport.getCriticality() != existingBugReport.getCriticality()) {
            attributeChanges.add(new Change("criticality", existingBugReport.getCriticality(),
                    editedBugReport.getCriticality(), "TestAccount Developer", editedBugReport.getId()));
        }
        if (editedBugReport.getCategory() != existingBugReport.getCategory()) {
            attributeChanges.add(new Change("category", existingBugReport.getCategory(), editedBugReport.getCategory(),
                    "TestAccount Developer", editedBugReport.getId()));
        }
        if (editedBugReport.getStatus() != existingBugReport.getStatus()) {
            attributeChanges.add(new Change("status", existingBugReport.getStatus(), editedBugReport.getStatus(),
                    "TestAccount Developer", editedBugReport.getId()));
        }
        if (editedBugReport.getPriority() != existingBugReport.getPriority()) {
            attributeChanges.add(new Change("priority", existingBugReport.getPriority(), editedBugReport.getPriority(),
                    "TestAccount Developer", editedBugReport.getId()));
        }

        saveChangesToDatabase(attributeChanges);
    }

    private void saveChangesToDatabase(List<Change> attributeChanges) {

        bugReportDAO.saveChangesToDatabase(attributeChanges);
    }

}
