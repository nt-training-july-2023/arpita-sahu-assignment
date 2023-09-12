package com.nucleusteq.asessmentPlatform.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Getter, setter and Constructor.
 */
@Getter
@Setter
@NoArgsConstructor
/**
 * Data Transfer Object (DTO) class representing a quiz.
 */
public class QuizDto {

    /**
     * The unique identifier of the quiz.
     */
    private int quizId;

    /**
     * The title of the quiz.
     */
    private String title;

    /**
     * A brief description of the quiz.
     */
    private String description;

    /**
     * The timer duration in seconds for the quiz.
     */
    private int quizTimer;

    /**
     * The DTO representing the associated category.
     */
    private CategoryDto category;
    
    public final CategoryDto getCategory() {
        return new CategoryDto(category.getCategoryId(),
                category.getTitle(),
                category.getDescription());
    }
    
    public final void setCategory(final CategoryDto cat) {
        this.category = new CategoryDto(cat.getCategoryId(),
                cat.getTitle(),
                cat.getDescription());
    }
    
    public QuizDto(final int id, final String qTitle,
            final String qDescription, final int qTimer,
            final CategoryDto cat) {
        this.quizId = id;
        this.title = qTitle;
        this.description = qDescription;
        this.quizTimer = qTimer;
        this.category = new CategoryDto(cat.getCategoryId(),
                cat.getTitle(),
                cat.getDescription());
    }
}