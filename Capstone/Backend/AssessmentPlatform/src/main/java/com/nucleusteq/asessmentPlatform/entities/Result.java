package com.nucleusteq.asessmentPlatform.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Result {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private int resultId; 

    private int totalObtainedMarks;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;
    
    //attempt date time

	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Result(int resultId, int totalObtainedMarks, User user, Quiz quiz) {
		super();
		this.resultId = resultId;
		this.totalObtainedMarks = totalObtainedMarks;
		this.user = user;
		this.quiz = quiz;
	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public int getTotalObtainedMarks() {
		return totalObtainedMarks;
	}

	public void setTotalObtainedMarks(int totalObtainedMarks) {
		this.totalObtainedMarks = totalObtainedMarks;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
}