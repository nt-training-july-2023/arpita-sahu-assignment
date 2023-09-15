package com.nucleusteq.asessmentPlatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a question.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    /**
     * The ID of the question.
     */
    private int quesId;

    /**
     * The content of the question.
     */
    private String question;

    /**
     * The first option for the question.
     */
    private String option1;

    /**
     * The second option for the question.
     */
    private String option2;

    /**
     * The third option for the question.
     */
    private String option3;

    /**
     * The fourth option for the question.
     */
    private String option4;

    /**
     * The correct answer to the question.
     */
    private String answer;
    /**
     * The ID of the quiz to which this question belongs.
     */
    private int quizId;
}
