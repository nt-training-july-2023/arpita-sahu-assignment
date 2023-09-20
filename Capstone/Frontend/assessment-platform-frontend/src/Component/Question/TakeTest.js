import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import "./question.css";

export default function TakeTest() {
  const [questions, setQuestions] = useState([]);
  const [selectedAnswers, setSelectedAnswers] = useState({});
  const [submitted, setSubmitted] = useState(false);
  const [quizTimer, setQuizTimer] = useState(null);
  const [score, setScore] = useState(0);
  const { quizId } = useParams();

  useEffect(() => {
    loadQuestions();
    loadQuizTimer();
  }, []);

  const loadQuestions = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/ques/quiz/${quizId}`);
      setQuestions(result.data);
    } catch (error) {
      console.error("Error loading questions:", error);
    }
  };

  const loadQuizTimer = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/quiz/${quizId}`);
      const quizEndTime = result.data.quizTimer;
      const currentTime = new Date().getTime();
      const timeRemaining = quizEndTime - currentTime;
      if (timeRemaining > 0) {
        setQuizTimer(
          setTimeout(() => {
            handleAutoSubmit();
          }, timeRemaining)
        );
      }
    } catch (error) {
      console.error("Error loading quiz timer:", error);
    }
  };

  const handleAnswerSelection = (questionId, selectedOption) => {
    setSelectedAnswers((prevSelectedAnswers) => ({
      ...prevSelectedAnswers,
      [questionId]: selectedOption,
    }));
  };

  const handleAutoSubmit = () => {
    if (!submitted) {
      handleSubmit(new Event("autoSubmit"));
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    clearTimeout(quizTimer);
    let obtainedScore = 0;
    questions.forEach((question) => {
      const correctAnswer = question.answer;
      const selectedAnswer = selectedAnswers[question.quesId];
      if (selectedAnswer === correctAnswer) {
        obtainedScore += 1;
      }
    });
    setScore(obtainedScore);
    setSubmitted(true);
  };

  return (
    <div>
      <h1>Take Test</h1>
      {questions.length > 0 ? (
        <form onSubmit={handleSubmit}>
          <div className="quiz-timer">Time Left: {formatTimeRemaining()}</div>
          {questions.map((question, index) => (
            <div className='question-card' key={index}>
              <p>{index + 1} {question.question}</p>
              <ul>
                {['option1', 'option2', 'option3', 'option4'].map((optionKey) => (
                  <p key={optionKey}>
                    <label>
                      <input
                        type="radio"
                        name={`question-${index}`}
                        value={question[optionKey]}
                        onChange={() => handleAnswerSelection(question.quesId, question[optionKey])}
                        checked={selectedAnswers[question.quesId] === question[optionKey]}
                        disabled={submitted}
                      />
                      {question[optionKey]}
                    </label>
                  </p>
                ))}
              </ul>
            </div>
          ))}
          <button type="submit">Submit Answers</button>
        </form>
      ) : (
        <p>Loading questions...</p>
      )}
      {submitted && (
        <div>
          <h2>Your Score:</h2>
          <p>{score} out of {questions.length}</p>
        </div>
      )}
    </div>
  );
}
