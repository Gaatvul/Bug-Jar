package com.gaatvul.bugtracker.services;

import java.util.List;

import com.gaatvul.bugtracker.DTOs.BugReportDTO;
import com.gaatvul.bugtracker.DTOs.CommentDTO;
import com.gaatvul.bugtracker.Entities.BugReportEntity;
import com.gaatvul.bugtracker.Entities.ChangeEntity;
import com.gaatvul.bugtracker.Entities.CommentEntity;

public interface BugReportService {

    List<BugReportEntity> loadListOfBugReports();

    BugReportEntity getBugReportById(int id);

    List<CommentEntity> getBugReportCommentsWithId(int id);

    List<String> loadListOfAllProjects();

    void saveNewCommentToDatabase(CommentDTO addedComment);

    void saveNewBugReportToDatabase(BugReportDTO bugReportToBeSaved);

    List<String> loadListofExistingUsers();

    void saveEditedBugReportToDatabase(BugReportEntity editedBugReport);

    List<ChangeEntity> loadListOfBugReportChangesWithId(int id);

}
