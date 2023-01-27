package com.gaatvul.bugtracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gaatvul.bugtracker.DTOs.UserAccountDTO;
import com.gaatvul.bugtracker.services.BugReportService;
import com.gaatvul.bugtracker.services.UserDetailsServiceImpl;

@Controller
public class DashboardController {

    @Autowired
    private BugReportService bugReportService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping(value = "/dashboard")
    public String getDashboardPage(Model model) {

        model.addAttribute("userDetails", getLoggedInUserAccountDetails());
        model.addAttribute("reportsByCriticality", bugReportService.getCriticalityCount());
        model.addAttribute("reportsByCategory", bugReportService.getCategoryCount());
        model.addAttribute("reportsByStatus", bugReportService.getStatusCount());
        model.addAttribute("reportsByPriority", bugReportService.getPriorityCount());

        return "userDashboardView";
    }

    private UserAccountDTO getLoggedInUserAccountDetails() {
        return userDetailsService.loadUserAccountDetailsByUsername(getCurrentAuthentication().getName());
    }

    private Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
