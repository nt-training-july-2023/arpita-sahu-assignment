import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import "./question.css";

export default function TakeTest() {
  const [questions, setQuestions] = useState([]);
  const [selectedAnswers, setSelectedAnswers] = useState({});
  const { quizId } = useParams();
  const navigate = useNavigate();
  const userName = localStorage.getItem('name');
  const quizTitle=localStorage.getItem('selectedQuizTitle');
  const categoryTitle = localStorage.getItem('selectedCategoryTitle');
  const userEmail = localStorage.getItem('selectedEmail');
  const [score, setScore]=useState(0);
  const [timeInSeconds, setTimeInSeconds] = useState(0);

  useEffect(() => {
    loadQuestions();
    loadQuizTimer();
  }, [quizId]);

  useEffect(() => {
    if (timeInSeconds > 0) {
      const timerInterval = setInterval(() => {
        setTimeInSeconds((prevTime) => prevTime - 1);
      }, 1000);

      return () => clearInterval(timerInterval);
    }
    else{
      handleSubmit();
    }
    
  }, [timeInSeconds]);

  const loadQuizTimer = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/quiz/${quizId}`);
      // const timerInMinutes = result.data.quizTimer;
      const timerInMinutes=1;
      const timerInSeconds = timerInMinutes * 60;
      setTimeInSeconds(timerInSeconds);
    } catch (error) {
      console.error("Error loading quiz timer:", error);
    }
  };
  
  const loadQuestions = async () => {
    try {
      const result = await axios.get(
        `http://localhost:8080/ques/quiz/${quizId}`
      );
      setQuestions(result.data);
    } catch (error) {
      console.error("Error loading questions:", error);
    }
  };


  const handleAnswerSelection = (questionId, selectedOption) => {
    setSelectedAnswers((prevSelectedAnswers) => ({
      ...prevSelectedAnswers,
      [questionId]: selectedOption,
    }));
  };
 const formatDateTime=(dateTime)=> {
    const year = dateTime.getFullYear();
    const month = (dateTime.getMonth() + 1).toString().padStart(2, '0'); 
    const day = dateTime.getDate().toString().padStart(2, '0');
    const hours = dateTime.getHours();
    const minutes = dateTime.getMinutes();
    const seconds = dateTime.getSeconds();
    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
  }

  const handleSubmit = async(e) => {
    if(e){
    e.preventDefault();
    }
    const currentTime = new Date();
    const formattedTime = formatDateTime(currentTime);
    let obtainedScore = 0;
    questions.forEach((question) => {
      const correctAnswer = question.answer;
      const selectedAnswer = selectedAnswers[question.quesId];
      if (selectedAnswer === correctAnswer) {
        obtainedScore += 1;
      }
    });
    setScore(obtainedScore);  
    try {
      const response = await axios.post("http://localhost:8080/result/save", {
        obtainedMarks: obtainedScore,
        totalMarks:questions.length,
        userName:userName,
        email:userEmail,
        quizTitle:quizTitle,
        categoryTitle:categoryTitle,
        totalNumOfQues: questions.length,
        dateAndTime:formattedTime,
        numofAttemptedQues: Object.keys(selectedAnswers).length,
      });
     console.log(response);
    } catch (error) {
      console.error("Error submitting quiz results:", error);
    }
    navigate('/result');
  };

  return (
    <div>
      <h1>Take Test</h1>
      <div className="quiz-timer">
        Time Left: {Math.floor(timeInSeconds / 60)}:{(timeInSeconds % 60).toString().padStart(2, "0")}
      </div>
      {questions.length > 0 ? (
        <form onSubmit={handleSubmit}>       
          {questions.map((question, index) => (
            <div className="question-card" key={index}>
              <p>
                {index + 1} {question.question}
              </p>
              <ul>
                {["option1", "option2", "option3", "option4"].map(
                  (optionKey) => (
                    <p key={optionKey}>
                      <label>
                        <input
                          type="radio"
                          name={`question-${index}`}
                          value={question[optionKey]}
                          onChange={() =>
                            handleAnswerSelection(
                              question.quesId,
                              question[optionKey]
                            )
                          }
                          checked={
                            selectedAnswers[question.quesId] ===
                            question[optionKey]
                          }
                        />
                        {question[optionKey]}
                      </label>
                    </p>
                  )
                )}
              </ul>
            </div>
          ))}
          <div className="submitTest">
          <button type="submit">Submit Answers</button>
          </div>
        </form>
      ) : (
        <p>Loading questions...</p>
      )}
    </div>
  );
}
