package com.gaatvul.bugtracker.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        BugReportEntity existingBugReport = bugReportDAO.getBugReportById(editedBugReport.getId());

        findChangesInBugReport(editedBugReport, existingBugReport);

        bugReportDAO.saveEditedBugReportToDatabase(editedBugReport);
    }

    private void findChangesInBugReport(BugReportEntity editedBugReport, BugReportEntity existingBugReport) {

        List<Change> attributeChanges = new ArrayList<>();
        HashMap<String, String> existingBugReportAttributes = existingBugReport.getAllAttributes();
        HashMap<String, String> editedBugReportAttributes = editedBugReport.getAllAttributes();

        for (Map.Entry<String, String> entry : existingBugReportAttributes.entrySet()) {

            if (!entry.getValue().equals(editedBugReportAttributes.get(entry.getKey()))) {

                attributeChanges.add(
                        new Change(entry.getKey(), entry.getValue(),
                                editedBugReportAttributes.get(entry.getKey()), "TestAccount Developer",
                                existingBugReport.getId()));
            }
        }

        saveChangesToDatabase(attributeChanges);
    }

    private void saveChangesToDatabase(List<Change> attributeChanges) {

        bugReportDAO.saveChangesToDatabase(attributeChanges);
    }

}
