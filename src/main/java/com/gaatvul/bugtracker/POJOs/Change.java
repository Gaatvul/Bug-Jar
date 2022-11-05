package com.gaatvul.bugtracker.POJOs;

public class Change {

    String attributeName, existingValue, newValue, user;
    int report_id;

    public Change(String attributeName, String existingValue, String newValue, String user, int report_id) {
        this.attributeName = attributeName;
        this.existingValue = existingValue;
        this.newValue = newValue;
        this.user = user;
        this.report_id = report_id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getExistingValue() {
        return existingValue;
    }

    public void setExistingValue(String existingValue) {
        this.existingValue = existingValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    
    
}
