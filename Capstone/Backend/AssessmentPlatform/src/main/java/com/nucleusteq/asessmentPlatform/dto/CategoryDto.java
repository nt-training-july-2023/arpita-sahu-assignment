package com.nucleusteq.asessmentPlatform.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing a category.
 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    /**
     * The unique identifier of the category.
     */
    private int categoryId;

    /**
     * The title of the category.
     */
    private String title;

    /**
     * The description of the category.
     */
    private String description;

    /**
     * Copy constructor for the CategoryDto class.
     *
     * @param category The CategoryDto object to create a copy from.
     */
    public CategoryDto(final CategoryDto category) {
        this.categoryId = category.categoryId;
        this.title = category.title;
        this.description = category.description;
    }

    /**
     * Copy constructor for the CategoryDto class.
     * @param cId          The CategoryDto object to create a copy from.
     * @param catTitle        The CategoryDto object to create a copy from.
     * @param cDescription The CategoryDto object to create a copy from.
     */
    public CategoryDto(final int cId, final String catTitle,
            final String cDescription) {
        super();
        this.categoryId = cId;
        this.title = catTitle;
        this.description = cDescription;
    }
}