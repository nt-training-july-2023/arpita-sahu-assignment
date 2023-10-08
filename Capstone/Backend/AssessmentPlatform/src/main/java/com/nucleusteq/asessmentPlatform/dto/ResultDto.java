package com.nucleusteq.asessmentPlatform.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a result.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {

    /**
     * The unique identifier of the result.
     */
    private int resultId;

    /**
     * The number of marks obtained in the quiz.
     */
    @Min(value = 0, message = "Obtained Marks required")
    private int obtainedMarks;

    /**
     * The total number of marks available in the quiz.
     */
    @Min(value = 0, message = "Total Marks required")
    private int totalMarks;

    /**
     * The username of the user who attempted the quiz.
     */
    @NotBlank(message = "userName is required")
    private String userName;

    /**
     * The email address of the user who attempted the quiz.
     */
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@nucleusteq\\.com$",
    message = "Email domain must be @nucleusteq.com")
    private String email;

    /**
     * The title of the quiz.
     */
    @NotBlank(message = "Quiz title is required")
    private String quizTitle;

    /**
     * The title of the quiz category.
     */
    @NotBlank(message = "Category title is required")
    private String categoryTitle;

    /**
     * The number of questions attempted in the quiz.
     */
    @Min(value = 0, message = "Number of attempted question required")
    private int numofAttemptedQues;

    /**
     * The total number of questions in the quiz.
     */
    @Min(value = 0, message = "Total Number of question required")
    private int totalNumOfQues;

    /**
     * The date and time when the quiz was attempted.
     */
    @NotBlank(message = "Date and Time is required")
    private String dateAndTime;
}
