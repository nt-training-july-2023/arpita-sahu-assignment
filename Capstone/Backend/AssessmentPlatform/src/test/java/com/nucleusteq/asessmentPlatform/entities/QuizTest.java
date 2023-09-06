package com.nucleusteq.asessmentPlatform.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuizTest {

	Quiz quiz;
	
	@BeforeEach
    void init() {
        quiz = new Quiz();
    }
	
	   @Test
	    void testGettersAndSetters() {
	        assertEquals(0, quiz.getQuizId());
	        assertEquals(null, quiz.getTitle());
	        assertEquals(null, quiz.getDescription());
	        
	       
	        
	        quiz.setQuizId(1);
	        quiz.setTitle("mcq1");
	        quiz.setDescription("contains java based mcq");
	        

	        
	        assertEquals(1, quiz.getQuizId());
	        assertEquals("mcq1", quiz.getTitle());
	        assertEquals("contains java based mcq", quiz.getDescription());
	        
	       

	    }
	   
	   @Test
	    void testDefaultConstructor() {
	    	 Quiz quiz = new Quiz();
	    	 assertEquals(0, quiz.getQuizId());
		     assertEquals(null, quiz.getTitle());
		     assertEquals(null, quiz.getDescription());
		     
	    }
	   
	   @Test
	    void testParameterisedConstructor() {
		   Quiz quiz = new Quiz(1,"mcq1","contains java based mcq",4);
		   assertEquals(1, quiz.getQuizId());
	       assertEquals("mcq1", quiz.getTitle());
	       assertEquals("contains java based mcq", quiz.getDescription());
	       
	       
	    }
	   

}
