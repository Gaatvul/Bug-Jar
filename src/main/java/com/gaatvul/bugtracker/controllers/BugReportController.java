package com.gaatvul.bugtracker.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gaatvul.bugtracker.DTOs.CommentDTO;
import com.gaatvul.bugtracker.DTOs.UserAccountDTO;
import com.gaatvul.bugtracker.Entities.BugReportEntity;
import com.gaatvul.bugtracker.Entities.CommentEntity;
import com.gaatvul.bugtracker.POJOs.BugReport;
import com.gaatvul.bugtracker.services.BugReportService;
import com.gaatvul.bugtracker.services.UserDetailsServiceImpl;

@Controller
public class BugReportController {

    @Autowired
    private BugReportService bugReportService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping(value = "/bugReports")
    public String showListOfBugReports(Model model) {

        List<BugReportEntity> listOfBugReports = bugReportService.loadListOfBugReports();

        model.addAttribute("listOfBugReports", listOfBugReports);
        model.addAttribute("userDetails", getLoggedInUserAccountDetails());

        return "viewAllBugReports";
    }

    @GetMapping(value = "/bugReports/view/{id}")
    public String viewReport(@PathVariable int id, @ModelAttribute("newComment") CommentEntity comment, Model model) {

        model.addAttribute("bugReport", bugReportService.getBugReportById(id));
        model.addAttribute("reportComments", bugReportService.getBugReportCommentsWithId(id));
        model.addAttribute("bugReportChanges", bugReportService.loadListOfBugReportChangesWithId(id));
        model.addAttribute("lastChanged", bugReportService.loadLastChangeTimeStamp(id));
        model.addAttribute("userDetails", getLoggedInUserAccountDetails());

        return "bugReportView";
    }

    @PostMapping(value = "/bugReports/view/{id}")
    public String addNewCommentToReport(@PathVariable int id, @Valid @ModelAttribute("newComment") CommentDTO comment,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("bugReport", bugReportService.getBugReportById(id));
            model.addAttribute("reportComments", bugReportService.getBugReportCommentsWithId(id));
            model.addAttribute("bugReportChanges", bugReportService.loadListOfBugReportChangesWithId(id));
            model.addAttribute("userDetails", getLoggedInUserAccountDetails());

            return "bugReportView";

        }

        bugReportService.saveNewCommentToDatabase(mapFormDataToCommentDTO(comment.getCommentText(), id));

        return "redirect:/bugReports/view/{id}";
    }

    private CommentDTO mapFormDataToCommentDTO(String commentText, int id) {

        CommentDTO mappedComment = new CommentDTO();

        mappedComment.setCommentText(commentText);
        mappedComment.setUserFullName(getLoggedInUserAccountDetails().getFullName());
        mappedComment.setReport_id(id);

        return mappedComment;
    }

    @GetMapping(value = "/bugReports/new")
    public String createNewBugReport(Model model) {

        BugReport newBugReport = new BugReport();

        model.addAttribute("newBugReport", newBugReport);
        model.addAttribute("allProjects", bugReportService.loadListOfAllProjects());
        model.addAttribute("existingUsers", bugReportService.loadListofExistingUsers());
        model.addAttribute("userDetails", getLoggedInUserAccountDetails());

        return "newBugReport";
    }

    @PostMapping(value = "/bugReports/new")
    public String saveNewBugReportToDatabase(@Valid @ModelAttribute("newBugReport") BugReport bugReportFromModel,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("newBugReport", bugReportFromModel);
            model.addAttribute("allProjects", bugReportService.loadListOfAllProjects());
            model.addAttribute("existingUsers", bugReportService.loadListofExistingUsers());
            model.addAttribute("userDetails", getLoggedInUserAccountDetails());

            return "newBugReport";
        }

        bugReportFromModel.setReporter(getLoggedInUserAccountDetails().getFullName());

        bugReportService.saveNewBugReportToDatabase(bugReportFromModel);

        return "redirect:/bugReports";
    }

    @GetMapping(value = "/bugReports/edit/{id}")
    public String loadEditPage(@PathVariable int id, Model model) {

        model.addAttribute("editableBugReport", bugReportService.getBugReportById(id));
        model.addAttribute("allProjects", bugReportService.loadListOfAllProjects());
        model.addAttribute("existingUsers", bugReportService.loadListofExistingUsers());
        model.addAttribute("userDetails", getLoggedInUserAccountDetails());

        return "editBugReportView";
    }

    @PostMapping(value = "/bugReports/edit/{id}")
    public String saveEdit(@PathVariable int id,
            @Valid @ModelAttribute("editableBugReport") BugReportEntity editedBugReport, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("editableBugReport", editedBugReport);
            model.addAttribute("allProjects", bugReportService.loadListOfAllProjects());
            model.addAttribute("existingUsers", bugReportService.loadListofExistingUsers());
            model.addAttribute("userDetails", getLoggedInUserAccountDetails());

            return "editBugReportView";
        }

        bugReportService.saveEditedBugReportToDatabase(editedBugReport);

        return "redirect:/bugReports/view/{id}";
    }

    private UserAccountDTO getLoggedInUserAccountDetails() {
        return userDetailsService.loadUserAccountDetailsByUsername(getCurrentAuthentication().getName());
    }

    private Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
