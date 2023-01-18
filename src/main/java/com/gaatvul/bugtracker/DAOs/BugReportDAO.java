package com.gaatvul.bugtracker.DAOs;

import java.util.List;

import com.gaatvul.bugtracker.DTOs.CommentDTO;
import com.gaatvul.bugtracker.Entities.BugReportEntity;
import com.gaatvul.bugtracker.Entities.ChangeEntity;
import com.gaatvul.bugtracker.Entities.CommentEntity;
import com.gaatvul.bugtracker.POJOs.BugReport;
import com.gaatvul.bugtracker.POJOs.Change;

public interface BugReportDAO {

    List<BugReportEntity> loadListOfBugReports();

    BugReportEntity getBugReportById(int id);

    List<CommentEntity> getBugReportCommentsWithId(int id);

    List<String> loadListOfAllProjects();

    void saveNewCommentToDatabase(CommentDTO addedComment);

    void saveNewBugReportToDatabase(BugReport bugReportToBeSaved);

    List<String> loadListofExistingUsers();

    void saveEditedBugReportToDatabase(BugReportEntity editedBugReport);

    void saveChangesToDatabase(List<Change> attributeChanges);

    List<ChangeEntity> loadListOfBugReportChangesWithId(int id);

    List<Integer> getCriticalityCount();

    List<Integer> getCategoryCount();

    List<Integer> getStatusCount();

    List<Integer> getPriorityCount();

}
