package com.nucleusteq.asessmentPlatform;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AssessmentPlatformApplicationTest {

    @Test
    public void testMainMethod() {
        AssessmentPlatformApplication.main(new String[] {});
    }

    @Test
    public void testModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        assertNotNull(modelMapper);
    }
}
