package com.nucleusteq.asessmentPlatform.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a quiz.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    /**
     * The unique identifier of the quiz.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private int quizId;

    /**
     * The title of the quiz.
     */
    @Column(name = "quiz_title")
    private String title;

    /**
     * The description of the quiz.
     */
    @Column(name = "quiz_description")
    private String description;

    /**
     * The number of questions in the quiz.
     */
    @Column(name = "no._of_ques")
    private int numOfQues;

    /**
     * The category to which this quiz belongs.
     */
//    @ManyToOne
//    @JoinColumn(name = "category_id", nullable = false)
//    private Category category;
}

