package com.gaatvul.bugtracker.DTOs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gaatvul.bugtracker.POJOs.BugReport;

@Component
public class BugReportDTO extends BugReport {
    
    private List<String> allProjects = new ArrayList<>();

    public List<String> getAllProjects() {
        allProjects.add("project1");
        allProjects.add("project2");
        return allProjects;
    }

    public void setAllProjects(List<String> allProjects) {
        this.allProjects = allProjects;
    }

    

}
