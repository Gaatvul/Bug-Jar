package com.gaatvul.bugtracker.services;

import java.util.List;

import com.gaatvul.bugtracker.Entities.BugReportEntity;

public interface BugReportService {

    List<BugReportEntity> loadListOfBugReports();

}
