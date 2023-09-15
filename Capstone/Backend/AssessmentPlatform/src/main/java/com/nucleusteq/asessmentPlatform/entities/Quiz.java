package com.nucleusteq.asessmentPlatform.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a quiz.
 */
@Entity
@Setter
@Getter
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
    @Column(name = "quiz_title", nullable = false, unique = true)
    private String title;

    /**
     * The description of the quiz.
     */
    @Column(name = "quiz_description")
    private String description;
    /**
     * The timer of the quiz.
     */
    private int quizTimer;

    /**
     * The category to which this quiz belongs.
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * Gets the category associated with this quiz.
     *
     * @return A new Category object representing the associated category.
     */

    public final Category getCategory() {
        return new Category(category.getCategoryId(), category.getTitle(),
                category.getDescription());
    }

    /**
     * Sets the category for this quiz.
     *
     * @param cat The Category object to associate with this quiz.
     */
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> question = new ArrayList<>();

    /**
     * Sets the category for this quiz.
     *
     * @param cat The Category object to set for this quiz.
     */
    public final void setCategory(final Category cat) {
        this.category = new Category(cat.getCategoryId(), cat.getTitle(),
                cat.getDescription());
    }

    /**
     * Retrieves a list of questions associated with this quiz.
     *
     * @return A new ArrayList containing the questions associated with this
     *         quiz.
     */
    public final List<Question> getQuestion() {
        return new ArrayList<>(question);
    }

    /**
     * Sets the list of questions for this quiz.
     *
     * @param ques The List of Question objects to set for this quiz.
     */
    public final void setQuestion(final List<Question> ques) {
        this.question = new ArrayList<>(ques);
    }

    /**
     * Constructs a new Quiz object with the specified parameters.
     *
     * @param id        The unique identifier of the quiz.
     * @param quizTitle The title of the quiz.
     * @param desc      A brief description of the quiz.
     * @param time      The timer duration in seconds for the quiz.
     */

    public Quiz(final int id, final String quizTitle, final String desc,
            final int time) {
        this.quizId = id;
        this.title = quizTitle;
        this.description = desc;
        this.quizTimer = time;
    }

}
