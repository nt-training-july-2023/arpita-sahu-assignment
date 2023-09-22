package com.nucleusteq.asessmentPlatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a result of a quiz attempt.
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
    private int obtainedMarks;

    /**
     * The total number of marks available in the quiz.
     */
    private int totalMarks;

    /**
     * The username of the user who attempted the quiz.
     */
    private String userName;

    /**
     * The email address of the user who attempted the quiz.
     */
    private String email;

    /**
     * The title of the quiz.
     */
    private String quizTitle;

    /**
     * The title of the quiz category.
     */
    private String categoryTitle;

    /**
     * The number of questions attempted in the quiz.
     */
    private int numofAttemptedQues;

    /**
     * The total number of questions in the quiz.
     */
    private int totalNumOfQues;

    /**
     * The date and time when the quiz was attempted.
     */
    private String dateAndTime;
}

