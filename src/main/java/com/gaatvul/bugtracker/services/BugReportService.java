package com.gaatvul.bugtracker.services;

import java.util.List;

import com.gaatvul.bugtracker.Entities.BugReportEntity;
import com.gaatvul.bugtracker.Entities.CommentEntity;

public interface BugReportService {

    List<BugReportEntity> loadListOfBugReports();

    BugReportEntity getBugReportById(int id);

    List<CommentEntity> getBugReportCommentsWithId(int id);

}
