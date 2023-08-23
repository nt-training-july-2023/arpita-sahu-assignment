package com.nucleusteq.asessmentPlatform.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Quiz {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private int quizId; 

    @Column(name = "quiz_title")
    private String title;

    @Column(name = "quiz_description")
    private String description;
    
    @Column(name="no._of_ques")
    private int numOfQues;
    
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Quiz(int quizId, String title, String description, int numOfQues, Category category) {
		super();
		this.quizId = quizId;
		this.title = title;
		this.description = description;
		this.numOfQues = numOfQues;
		this.category = category;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumOfQues() {
		return numOfQues;
	}

	public void setNumOfQues(int numOfQues) {
		this.numOfQues = numOfQues;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
    
    

}
