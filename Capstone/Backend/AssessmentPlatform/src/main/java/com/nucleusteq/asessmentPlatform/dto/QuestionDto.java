package com.nucleusteq.asessmentPlatform.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Question is required")
    private String question;

    /**
     * The first option for the question.
     */
    @NotBlank(message = "Option1 is required")
    private String option1;

    /**
     * The second option for the question.
     */
    @NotBlank(message = "Option2 is required")
    private String option2;

    /**
     * The third option for the question.
     */
    @NotBlank(message = "Option3 is required")
    private String option3;

    /**
     * The fourth option for the question.
     */
    @NotBlank(message = "Option4 is required")
    private String option4;

    /**
     * The correct answer to the question.
     */
    @NotBlank(message = "Answer is required")
    private String answer;

    /**
     * Checks if the provided answer is valid by comparing it to a set of
     * options.
     * @return true if the answer is valid (matches one of the options), false
     *         otherwise.
     */
    @AssertTrue(message = "Answer must be one of the provided options")
    final boolean isAnswerValid() {
        return answer != null
                && (answer.equals(option1) || answer.equals(option2)
                        || answer.equals(option3) || answer.equals(option4));
    }
    /**
     * The ID of the quiz to which this question belongs.
     */
    private int quizId;
}
