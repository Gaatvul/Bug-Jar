package com.gaatvul.bugtracker.Entities;

import java.sql.Timestamp;

public class CommentEntity {

    private String comment, commenter_name;
    private Timestamp createdOn;

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
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
        return "CommentEntity [comment=" + comment + ", commenter_name=" + commenter_name + ", createdOn="
                + createdOn + "]";
    }

}
