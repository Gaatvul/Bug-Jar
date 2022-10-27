package com.gaatvul.bugtracker.Entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gaatvul.bugtracker.POJOs.BugReport;

@Component
public class BugReportEntity extends BugReport {

    private int id;
    private Timestamp createdOn;
    private List<CommentEntity> comments = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

}
