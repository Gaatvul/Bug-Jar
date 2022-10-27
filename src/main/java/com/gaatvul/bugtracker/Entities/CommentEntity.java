package com.gaatvul.bugtracker.Entities;

import java.sql.Timestamp;

public class CommentEntity {

    private String commentText, commenter_name;
    private Timestamp createdOn;

    public String getCommentText() {
        return commentText;
    }
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
    public String getCommenter_name() {
        return commenter_name;
    }
    public void setCommenter_name(String commenter_name) {
        this.commenter_name = commenter_name;
    }
    public Timestamp getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "CommentEntity [commentText=" + commentText + ", commenter_name=" + commenter_name + ", createdOn="
                + createdOn + "]";
    }

}
