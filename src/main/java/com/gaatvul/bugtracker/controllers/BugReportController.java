package com.gaatvul.bugtracker.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gaatvul.bugtracker.DTOs.BugReportDTO;
import com.gaatvul.bugtracker.DTOs.CommentDTO;
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
    public String viewReport(@PathVariable int id, @ModelAttribute("newComment") CommentEntity comment, Model model) {

        model.addAttribute("bugReport", bugReportService.getBugReportById(id));
        model.addAttribute("reportComments", bugReportService.getBugReportCommentsWithId(id));
        model.addAttribute("bugReportChanges", bugReportService.loadListOfBugReportChangesWithId(id));

        return "bugReportView";
    }

    @PostMapping(value = "/bugReports/view/{id}")
    public String addNewCommentToReport(@PathVariable int id, @Valid @ModelAttribute("newComment") CommentDTO comment,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("bugReport", bugReportService.getBugReportById(id));
            model.addAttribute("reportComments", bugReportService.getBugReportCommentsWithId(id));
            model.addAttribute("bugReportChanges", bugReportService.loadListOfBugReportChangesWithId(id));

            return "bugReportView";

        }

        bugReportService.saveNewCommentToDatabase(mapFormDataToCommentDTO(comment, id));

        return "redirect:/bugReports/view/{id}";
    }

    private CommentDTO mapFormDataToCommentDTO(CommentDTO formData, int report_id) {

        CommentDTO mappedComment = new CommentDTO();

        mappedComment.setCommentText(formData.getCommentText());
        mappedComment.setUserFullName("TestAccount Developer");
        mappedComment.setReport_id(report_id);

        return mappedComment;
    }

    @GetMapping(value = "/bugReports/new")
    public String createNewBugReport(Model model) {

        BugReportDTO newBugReport = new BugReportDTO();

        model.addAttribute("newBugReport", newBugReport);
        model.addAttribute("allProjects", bugReportService.loadListOfAllProjects());
        model.addAttribute("existingUsers", bugReportService.loadListofExistingUsers());

        return "newBugReport";
    }

    @PostMapping(value = "/bugReports/new")
    public String saveNewBugReportToDatabase(@Valid @ModelAttribute("newBugReport") BugReportDTO bugReportFromModel,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("newBugReport", bugReportFromModel);
            model.addAttribute("allProjects", bugReportService.loadListOfAllProjects());
            model.addAttribute("existingUsers", bugReportService.loadListofExistingUsers());

            return "newBugReport";
        }

        bugReportService.saveNewBugReportToDatabase(bugReportFromModel);

        return "redirect:/bugReports";
    }

    @GetMapping(value = "/bugReports/edit/{id}")
    public String loadEditPage(@PathVariable int id, Model model) {

        model.addAttribute("editableBugReport", bugReportService.getBugReportById(id));
        model.addAttribute("allProjects", bugReportService.loadListOfAllProjects());
        model.addAttribute("existingUsers", bugReportService.loadListofExistingUsers());

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

                return "editBugReportView";
            }
        
        bugReportService.saveEditedBugReportToDatabase(editedBugReport);

        return "redirect:/bugReports/view/{id}";
    }

}
