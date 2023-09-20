
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

export default function TakeTest() {
  const [questions, setQuestions] = useState([]);
  const [selectedAnswers, setSelectedAnswers] = useState({});
  const [submitted, setSubmitted] = useState(false);
  const [score, setScore] = useState(0);
  const { quizId } = useParams();

  useEffect(() => {
    loadQuestions();
  }, []);

  const loadQuestions = async () => {
 
      try {
        const result = await axios.get(`http://localhost:8080/ques/quiz/${quizId}`);
       
        setQuestions(result.data);
      } catch (error) {
        console.error("Error loading questions:", error);
      }
  };
  const loadQuizzes = async () => {
    try{
      const result = await axios.get(`http://localhost:8080/quiz/${quizId}`)
      setQuizzes(result.data);
    }catch(error){
      console.log("Error loading Quizzes", error);
    }
  }
  
  const handleAnswerSelection = (questionId, selectedOption) => {
    setSelectedAnswers((prevSelectedAnswers) => ({
      ...prevSelectedAnswers,
      [questionId]: selectedOption,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
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
  }, [quizId]);
  };

  return (
    <div>
      <h1>Take Test</h1>
      {questions.length > 0 ? (
        <form onSubmit={handleSubmit}>
          {questions.map((question, index) => (
            <div key={index}>
              <h2>Question {index + 1}</h2>
              <p>{question.question}</p>
              <ul>
                {['option1', 'option2', 'option3', 'option4'].map((optionKey) => (
                  <li key={optionKey}>
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
                  </li>
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
