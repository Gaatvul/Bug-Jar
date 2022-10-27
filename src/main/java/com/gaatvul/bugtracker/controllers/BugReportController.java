package com.gaatvul.bugtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gaatvul.bugtracker.Entities.BugReportEntity;
import com.gaatvul.bugtracker.Entities.CommentEntity;
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
        model.addAttribute("reportComments", bugReportService.getBugReportCommentsWithId(id));

        return "bugReportView";
    }

    @PostMapping(value = "/bugReports/view/{id}/addComment")
    public String addNewCommentToReport(@PathVariable int id, @ModelAttribute("newComment") CommentEntity comment,
            Model model) {
        
        CommentEntity addedComment = new CommentEntity();
        addedComment.setCommentText(comment.getCommentText());
        addedComment.setCommenter_name("TestAccount Owner");

        return "redirect:/bugReports/view/{id}";
    }

    @GetMapping(value="/bugReports/new")
    public String createNewBugReport(Model model) {
        
        BugReportEntity newBugReport = new BugReportEntity();

        model.addAttribute("newBugReport", newBugReport);
        model.addAttribute("allProjects", bugReportService.loadListOfAllProjects());
        
        
        return "newBugReport";
    }
    

}
