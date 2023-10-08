package com.nucleusteq.asessmentPlatform.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a question in a quiz.
 */
@Entity
@Table(name = "Questions")
@NoArgsConstructor
@Getter
@Setter
public class Question {

    /**
     * The unique identifier of the question.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private int quesId;

    /**
     * The content of the question.
     */
    @Column(name = "question_Name", nullable = false)
    private String question;

    /**
     * The first answer option for the question.
     */
    @Column(nullable = false)
    private String option1;

    /**
     * The second answer option for the question.
     */
    @Column(nullable = false)
    private String option2;

    /**
     * The third answer option for the question.
     */
    @Column(nullable = false)
    @NotBlank(message = "Option3 is required")
    private String option3;

    /**
     * The fourth answer option for the question.
     */
    @Column(nullable = false)
    private String option4;

    /**
     * The correct answer to the question.
     */
    @Column(nullable = false)
    private String answer;

    /**
     * The quiz to which this question belongs.
     */
    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    /**
     * Retrieves a copy of the Quiz associated with this question.
     *
     * @return A new Quiz object with the same properties as the associated
     *         quiz.
     */
    public Quiz getQuiz() {
        return new Quiz(quiz.getQuizId(), quiz.getTitle(),
                quiz.getDescription(), quiz.getQuizTimer());
    }

    /**
     * Sets the associated quiz for this question.
     *
     * @param quizz The Quiz object to associate with this question.
     */
    public void setQuiz(final Quiz quizz) {
        this.quiz = new Quiz(quizz.getQuizId(), quizz.getTitle(),
                quizz.getDescription(), quizz.getQuizTimer());
    }

    /**
     * Constructs a Question object with the provided attributes.
     *
     * @param questionId   The ID of the question.
     * @param questionName The content of the question.
     * @param opt1  The first option for the question.
     * @param opt2  The second option for the question.
     * @param opt3  The third option for the question.
     * @param opt4  The fourth option for the question.
     * @param correctAnswer   The correct answer to the question.
     */
    public Question(final int questionId, final String questionName,
            final String opt1, final String opt2, final String opt3,
            final String opt4, final String correctAnswer) {
        this.quesId = questionId;
        this.question = questionName;
        this.option1 = opt1;
        this.option2 = opt2;
        this.option3 = opt3;
        this.option4 = opt4;
        this.answer = correctAnswer;
    }
}
