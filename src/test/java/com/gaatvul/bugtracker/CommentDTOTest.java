package com.gaatvul.bugtracker;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.gaatvul.bugtracker.DTOs.CommentDTO;

public class CommentDTOTest {

    CommentDTO comment1 = new CommentDTO();
    CommentDTO comment2 = new CommentDTO();
    CommentDTO comment3 = new CommentDTO();

    @Before
    public void setUp() {

        comment1.setUserFullName("Brandon Flowers");
        comment2.setUserFullName("Moby Dick");
        comment3.setUserFullName("Alexandria Osario Cortez");
    }

    @Test
    public void setNames_ShouldReturnArrayOfFirstAndSecondName() throws Exception {

        String[] expectedArray = { "Brandon", "Flowers" };

        assertArrayEquals(expectedArray, comment1.getNames());
    }

    @Test
    public void setNames_ShouldReturnMiddleAndLastNameTogether() throws Exception {

        String[] expectedArray = { "Alexandria", "Osario Cortez" };

        assertArrayEquals(expectedArray, comment3.getNames());
    }

    @Test
    public void getUserFirstName_ShouldReturnUserFirstName() throws Exception {

        assertEquals("Brandon", comment1.getUserFirstName());
        assertEquals("Moby", comment2.getUserFirstName());

    }

    @Test
    public void getUserSecondName_ShouldReturnUserSecondName() throws Exception {

        assertEquals("Flowers", comment1.getUserSecondName());
        assertEquals("Dick", comment2.getUserSecondName());
        assertEquals("Osario Cortez", comment3.getUserSecondName());
    }

}
