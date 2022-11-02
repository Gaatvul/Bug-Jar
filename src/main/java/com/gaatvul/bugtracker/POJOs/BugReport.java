package com.gaatvul.bugtracker.POJOs;

import org.springframework.stereotype.Component;

@Component
public class BugReport {

    private String title, description, projectAssignedTo, assignee, reporter, owner, criticality, category, status,
            priority;

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "BugReport [title=" + title + ", description=" + description + ", projectAssignedTo=" + projectAssignedTo
                + ", assignee=" + assignee + ", reporter=" + reporter + ", owner=" + owner + ", criticality="
                + criticality + ", category=" + category + ", status=" + status + ", priority=" + priority + "]";
    }

}
