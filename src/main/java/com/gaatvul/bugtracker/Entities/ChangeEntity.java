package com.gaatvul.bugtracker.Entities;

import java.sql.Timestamp;

import com.gaatvul.bugtracker.POJOs.Change;

public class ChangeEntity extends Change {

    private Timestamp createdOn;

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
}
