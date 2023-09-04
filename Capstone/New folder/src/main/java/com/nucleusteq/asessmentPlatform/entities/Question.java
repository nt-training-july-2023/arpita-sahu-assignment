package com.nucleusteq.asessmentPlatform.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a question in a quiz.
 */
@Entity
@Table(name = "Questions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(name = "question_content", nullable = false)
    private String question;

    /**
     * The first answer option for the question.
     */
    private String option1;

    /**
     * The second answer option for the question.
     */
    private String option2;

    /**
     * The third answer option for the question.
     */
    private String option3;

    /**
     * The fourth answer option for the question.
     */
    private String option4;

    /**
     * The correct answer to the question.
     */
    private String answer;

    /**
     * The quiz to which this question belongs.
     */
//    @ManyToOne
//    @JoinColumn(name = "quiz_id", nullable = false)
//    private Quiz quiz;
}

