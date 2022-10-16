package com.gaatvul.bugtracker.DAOs;

import java.util.List;

import com.gaatvul.bugtracker.Entities.BugReportEntity;

public interface BugReportDAO {

    List<BugReportEntity> loadListOfBugReports();

}
