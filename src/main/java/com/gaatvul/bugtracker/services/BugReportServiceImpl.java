package com.gaatvul.bugtracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaatvul.bugtracker.DAOs.BugReportDAO;
import com.gaatvul.bugtracker.Entities.BugReportEntity;

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
    
}
