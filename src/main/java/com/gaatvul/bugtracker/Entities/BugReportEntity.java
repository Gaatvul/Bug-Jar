package com.gaatvul.bugtracker.Entities;

import java.sql.Timestamp;

public class BugReportEntity {

    private int id;
    private String title, description, projectAssignedTo, assignee, reporter, criticality, category, priority, status;
    private Timestamp createdOn;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getProjectAssignedTo() {
        return projectAssignedTo;
    }
    public void setProjectAssignedTo(String projectAssignedTo) {
        this.projectAssignedTo = projectAssignedTo;
    }
    public String getAssignee() {
        return assignee;
    }
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
    public String getReporter() {
        return reporter;
    }
    public void setReporter(String reporter) {
        this.reporter = reporter;
    }
    public String getCriticality() {
        return criticality;
    }
    public void setCriticality(String criticality) {
        this.criticality = criticality;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public Timestamp getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "BugReportEntity [id=" + id + ", title=" + title + ", description=" + description
                + ", projectAssignedTo=" + projectAssignedTo + ", assignee=" + assignee + ", reporter=" + reporter
                + ", criticality=" + criticality + ", category=" + category + ", priority=" + priority + ", status="
                + status + ", createdOn=" + createdOn + "]";
    }

    

    

}
