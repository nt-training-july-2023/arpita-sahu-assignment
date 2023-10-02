package com.nucleusteq.asessmentPlatform.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing a category.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    /**
     * The unique identifier of the category.
     */
    private int categoryId;

    /**
     * The title of the category.
     */
    @NotBlank(message = "Category title is required")
    private String title;

    /**
     * The description of the category.
     */
    @NotBlank(message = "Category description is required")
    private String description;

}
