package com.nucleusteq.asessmentPlatform.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    public Quiz getQuiz() {
        return new Quiz(quiz.getQuizId(), quiz.getTitle(),
                quiz.getDescription(), quiz.getQuizTimer());
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = new Quiz(quiz.getQuizId(), quiz.getTitle(),
                quiz.getDescription(), quiz.getQuizTimer());
    }

    public Question(int quesId, String question, String option1, String option2,
            String option3, String option4, String answer) {
        this.quesId=quesId;
        this.question=question;
        this.option1=option1;
        this.option2=option2;
        this.option3=option3;
        this.option4=option4;
        this.answer=answer;
    }
}
