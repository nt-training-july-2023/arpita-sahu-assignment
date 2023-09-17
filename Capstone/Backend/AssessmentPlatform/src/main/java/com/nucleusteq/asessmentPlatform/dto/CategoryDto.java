package com.nucleusteq.asessmentPlatform.dto;

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
    private String title;

    /**
     * The description of the category.
     */
    private String description;

//    /**
//     * Copy constructor for the CategoryDto class.
//     * @param cId          The CategoryDto object to create a copy from.
//     * @param catTitle     The CategoryDto object to create a copy from.
//     * @param cDescription The CategoryDto object to create a copy from.
//     */
//    public CategoryDto(final int cId, final String catTitle,
//            final String cDescription) {
//        super();
//        this.categoryId = cId;
//        this.title = catTitle;
//        this.description = cDescription;
//    }
}
