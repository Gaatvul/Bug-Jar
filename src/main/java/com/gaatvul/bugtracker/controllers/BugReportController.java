package com.gaatvul.bugtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gaatvul.bugtracker.Entities.BugReportEntity;
import com.gaatvul.bugtracker.services.BugReportService;

@Controller
public class BugReportController {

    @Autowired
    private BugReportService bugReportService;
    
    @GetMapping("/bugReports")
    public String showListOfBugReports(Model model) {
        
        List<BugReportEntity> listOfBugReports = bugReportService.loadListOfBugReports();

        model.addAttribute("listOfBugReports", listOfBugReports);

        return "viewAllBugReports";
    }

    @GetMapping("/bugReports/view/{id}")
    public String viewReport(@PathVariable int id, Model model) {

        model.addAttribute("bugReport", bugReportService.getBugReportById(id));

        return "bugReportView";
    }

}
