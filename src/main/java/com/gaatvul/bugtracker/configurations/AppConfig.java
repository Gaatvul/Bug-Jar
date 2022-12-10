package com.gaatvul.bugtracker.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gaatvul.bugtracker.DAOs.BugReportDAO;
import com.gaatvul.bugtracker.DAOs.BugReportDAOImpl;
import com.gaatvul.bugtracker.DAOs.UserDetailsDAOImpl;
import com.gaatvul.bugtracker.services.BugReportService;
import com.gaatvul.bugtracker.services.BugReportServiceImpl;

@ComponentScan("com.gaatvul.bugtracker")
public class AppConfig {

    @Autowired
    DataSource dataSource;
    
    @Bean(name = "bugReportServiceImpl")
    public BugReportService creatBugReportService() {
        return new BugReportServiceImpl();
    }
    
    @Bean(name = "bugReportDAOImpl")
    public BugReportDAO creatBugReportDAO() {
        return new BugReportDAOImpl();
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate createJdbcTemplate() {
        return new JdbcTemplate();
    }

    @Bean(name = "userAccountDAOImpl")
    public UserDetailsDAOImpl createUserAccountDAO() {
        return new UserDetailsDAOImpl();
    }
}
