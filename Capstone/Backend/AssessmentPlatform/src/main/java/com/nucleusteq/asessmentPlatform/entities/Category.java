package com.nucleusteq.asessmentPlatform.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a category.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    /**
     * The unique identifier of the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private int categoryId;

    /**
     * The title of the category.
     */
    @Column(name = "category_title", nullable = false)
    private String title;

    /**
     * The description of the category.
     */
    @Column(name = "category_description", nullable = false)
    private String description;
    /**
     * ArrayList for the quiz.
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Quiz> quizzes = new ArrayList<>();

    /**
     * Gets a list of quizzes associated with this category.
     *
     * @return A new ArrayList containing the quizzes associated with this
     *         category.
     */
    public final List<Quiz> getQuizzes() {
        return new ArrayList<>(quizzes);
    }

    /**
     * Sets the list of quizzes associated with this category.
     *
     * @param quiz The list of Quiz objects to associate with this category.
     */

    public final void setQuizzes(final List<Quiz> quiz) {
        this.quizzes = new ArrayList<>(quiz);
    }

    /**
     * Constructs a new Category object with the specified parameters.
     *
     * @param catId    The unique identifier of the category.
     * @param catTitle The title of the category.
     * @param desc     A brief description of the category.
     */

    public Category(final int catId, final String catTitle, final String desc) {
        this.categoryId = catId;
        this.title = catTitle;
        this.description = desc;
    }
}
