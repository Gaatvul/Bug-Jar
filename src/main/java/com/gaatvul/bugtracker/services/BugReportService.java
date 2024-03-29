package com.gaatvul.bugtracker.services;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import com.gaatvul.bugtracker.DTOs.CommentDTO;
import com.gaatvul.bugtracker.Entities.BugReportEntity;
import com.gaatvul.bugtracker.Entities.ChangeEntity;
import com.gaatvul.bugtracker.Entities.CommentEntity;
import com.gaatvul.bugtracker.POJOs.BugReport;

public interface BugReportService {

    List<BugReportEntity> loadListOfBugReports();

    BugReportEntity getBugReportById(int id);

    List<CommentEntity> getBugReportCommentsWithId(int id);

    List<String> loadListOfAllProjects();

    void saveNewCommentToDatabase(CommentDTO addedComment);

    void saveNewBugReportToDatabase(@Valid BugReport bugReportFromModel);

    List<String> loadListofExistingUsers();

    void saveEditedBugReportToDatabase(BugReportEntity editedBugReport);

    List<ChangeEntity> loadListOfBugReportChangesWithId(int id);

    Timestamp loadLastChangeTimeStamp(int id);

    List<Integer> getCriticalityCount();

    List<Integer> getCategoryCount();

    List<Integer> getStatusCount();

    List<Integer> getPriorityCount();

}
