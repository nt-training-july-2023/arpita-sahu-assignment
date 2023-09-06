package com.nucleusteq.asessmentPlatform.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@Column(name = "quiz_title", nullable = false, unique = true)
	private String title;

	/**
	 * The description of the quiz.
	 */
	@Column(name = "quiz_description")
	private String description;

	private int quizTimer;

	/**
	 * The number of questions in the quiz.
	 */
//    @Column(name = "no._of_ques")
//    private int numOfQues;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date startTime;
//    
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date endTime;

	/**
	 * The category to which this quiz belongs.
	 */
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Category getCategory() {
		return new Category(category.getCategoryId(), category.getTitle(), category.getDescription());
	}

	public void setCategory(final Category category) {
		this.category = new Category(category.getCategoryId(), category.getTitle(), category.getDescription());
	}

	public Quiz(final int quizId, final String title, final String description, final int time) {
		this.quizId = quizId;
		this.title = title;
		this.description = description;
		this.quizTimer = time;
	}
}
