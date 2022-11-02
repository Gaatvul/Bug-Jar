package com.gaatvul.bugtracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaatvul.bugtracker.DAOs.BugReportDAO;
import com.gaatvul.bugtracker.DTOs.BugReportDTO;
import com.gaatvul.bugtracker.DTOs.CommentDTO;
import com.gaatvul.bugtracker.Entities.BugReportEntity;
import com.gaatvul.bugtracker.Entities.CommentEntity;

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
    
}
