package com.gaatvul.bugtracker.DAOs;

import java.util.List;

import com.gaatvul.bugtracker.Entities.BugReportEntity;
import com.gaatvul.bugtracker.Entities.CommentEntity;

public interface BugReportDAO {

    List<BugReportEntity> loadListOfBugReports();

    BugReportEntity getBugReportById(int id);

    List<CommentEntity> getBugReportCommentsWithId(int id);

}
