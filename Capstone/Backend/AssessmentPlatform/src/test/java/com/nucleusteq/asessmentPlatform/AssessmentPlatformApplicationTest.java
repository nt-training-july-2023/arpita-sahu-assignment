package com.nucleusteq.asessmentPlatform;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
//class AssessmentPlatformApplicationTest {
    
   
    @SpringBootTest
    public class AssessmentPlatformApplicationTest {

        @Test
        public void testMainMethod() {
            // Arrange: Set up any necessary test data or configurations
            
            // Act: Call the main method
            AssessmentPlatformApplication.main(new String[] {});
            
            // Assert: Check if the application ran successfully (no exceptions)
            // You can add more specific assertions based on your application's behavior
        }

    @Test
    public void testModelMapper() {
//        AssessmentPlatformApplication application = new AssessmentPlatformApplication();
        ModelMapper modelMapper = new ModelMapper();
        assertNotNull(modelMapper);
    }
}
