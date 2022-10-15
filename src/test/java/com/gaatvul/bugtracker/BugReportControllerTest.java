package com.gaatvul.bugtracker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class BugReportControllerTest {
    
    @Test
    public void showStudents_shouldReturnListOfStudents() throws Exception{
        
    }
}
