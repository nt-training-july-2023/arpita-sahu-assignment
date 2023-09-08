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

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Quiz> quizzes = new ArrayList<>();

    public List<Quiz> getQuizzes() {
        return new ArrayList<>(quizzes);
    }

    public void setQuizzes(final List<Quiz> quizzes) {
        this.quizzes = new ArrayList<>(quizzes);
    }

    public Category(final int categoryId, final String title,
            final String description) {
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
    }
}
