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
 * Entity class representing a result of a quiz attempt.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    /**
     * The unique identifier of the result.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private int resultId;

    /**
     * The total marks obtained in the quiz attempt.
     */
    private int totalObtainedMarks;

    /**
     * The user who attempted the quiz.
     */
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    /**
     * The quiz for which the result was obtained. //
     */
//    @ManyToOne
//    @JoinColumn(name = "quiz_id", nullable = false)
//    private Quiz quiz;

    /**
     * The date and time of the quiz attempt.
     */

    // private LocalDateTime attemptDateTime;
}
