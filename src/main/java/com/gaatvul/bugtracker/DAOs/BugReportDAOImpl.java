package com.gaatvul.bugtracker.DAOs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gaatvul.bugtracker.Entities.BugReportEntity;

@Repository
public class BugReportDAOImpl implements BugReportDAO {

    @Override
    public List<BugReportEntity> loadListOfBugReports() {
        List<BugReportEntity> listOfBugReportsInDatabase = new ArrayList<>();
        return listOfBugReportsInDatabase;
    }
    
}
