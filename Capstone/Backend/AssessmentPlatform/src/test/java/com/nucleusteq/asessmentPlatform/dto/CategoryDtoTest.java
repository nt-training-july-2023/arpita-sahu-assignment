package com.nucleusteq.asessmentPlatform.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CategoryDtoTest {

    CategoryDto categoryDto;

    @BeforeEach
    void init() {
        categoryDto = new CategoryDto();
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(0, categoryDto.getCategoryId());
        assertEquals(null, categoryDto.getTitle());
        assertEquals(null, categoryDto.getDescription());
        categoryDto.setCategoryId(1);
        categoryDto.setTitle("GK");
        categoryDto.setDescription("GK Description");
        assertEquals(1, categoryDto.getCategoryId());
        assertEquals("GK", categoryDto.getTitle());
        assertEquals("GK Description", categoryDto.getDescription());
    }

    @Test
    void testDefaultConstructor() {
        CategoryDto categoryDto = new CategoryDto();
        assertEquals(0, categoryDto.getCategoryId());
        assertEquals(null, categoryDto.getTitle());
        assertEquals(null, categoryDto.getDescription());
    }

    @Test
    void testParameterisedConstructor() {
        CategoryDto categoryDto = new CategoryDto(1, "GK", "GK Category");
        assertEquals(1, categoryDto.getCategoryId());
        assertEquals("GK", categoryDto.getTitle());
        assertEquals("GK Category", categoryDto.getDescription());
    }
}
