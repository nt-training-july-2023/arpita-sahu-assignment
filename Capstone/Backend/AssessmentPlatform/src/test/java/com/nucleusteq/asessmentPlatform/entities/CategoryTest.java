package com.nucleusteq.asessmentPlatform.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {

    Category category;

    @BeforeEach
    void init() {
        category = new Category();
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(0, category.getCategoryId());
        assertEquals(null, category.getTitle());
        assertEquals(null, category.getDescription());

        List<Quiz> quizzes = new ArrayList<>();
        Quiz quiz1 = new Quiz();
        Quiz quiz2 = new Quiz();
        quizzes.add(quiz1);
        quizzes.add(quiz2);

        category.setQuizzes(quizzes);

        List<Quiz> retrievedQuizzes = category.getQuizzes();
        assertEquals(quizzes, retrievedQuizzes);
        assertNotSame(quizzes, retrievedQuizzes);

        category.setCategoryId(1);
        category.setTitle("GK");
        category.setDescription("GK Description");
        assertEquals(1, category.getCategoryId());
        assertEquals("GK", category.getTitle());
        assertEquals("GK Description", category.getDescription());
    }

    @Test
    void testDefaultConstructor() {
        Category category = new Category();
        assertEquals(0, category.getCategoryId());
        assertEquals(null, category.getTitle());
        assertEquals(null, category.getDescription());
    }

    @Test
    void testParameterisedConstructor() {
        Category category = new Category(1, "GK", "GK Category");
        assertEquals(1, category.getCategoryId());
        assertEquals("GK", category.getTitle());
        assertEquals("GK Category", category.getDescription());
    }
}
