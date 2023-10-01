import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import DisableBackButton from "../DisableBackButton";
import ServiceURL from "../Service/ServiceURL";
import SweetAlertService from "../SweetAlert/SweetAlertService";
import NotFound from "../NotFound";

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
  const [timerEnded, setTimerEnded] = useState(false);
  const role = localStorage.getItem('userRole');

  useEffect(() => {
    loadQuestions();
    loadQuizTimer();
  }, [quizId]);
  
    const handleCountdown = () => {
      if (timeInSeconds > 0) {
        setTimeInSeconds((prevTime) => prevTime - 1);
      } else if(!timerEnded){
        setTimerEnded(true);
        showAutoSubmitMessage();
      }
    };

    useEffect(() => {
      const countdownInterval = setInterval(handleCountdown, 1000);
      return () => clearInterval(countdownInterval);
    }, [timeInSeconds, timerEnded]);


  const loadQuizTimer = async () => {
    try {
      const result = ServiceURL.getQuizByQuizId(quizId);
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
      const result = await ServiceURL.getQuestionByQuizId(quizId);
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
  const handleManualSubmit = async (e) => {
    e.preventDefault();
    const result = await SweetAlertService.showManualSubmitAlert();
    if (result.isConfirmed) {
      handleSubmit();
    }
  };
  const showAutoSubmitMessage = () => {
    SweetAlertService.showAutoSubmitAlert(handleSubmit);
  };

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
      const resultItem ={
        obtainedMarks: obtainedScore,
        totalMarks:questions.length,
        userName:userName,
        email:userEmail,
        quizTitle:quizTitle,
        categoryTitle:categoryTitle,
        totalNumOfQues: questions.length,
        dateAndTime:formattedTime,
        numofAttemptedQues: Object.keys(selectedAnswers).length,
      }
     await ServiceURL.addResult(resultItem);
    } catch (error) {
      console.error("Error submitting quiz results:", error);
    }
    navigate('/result');
  };

  return (
    <div>
      {role === 'user' ? <>
       {questions.length > 0 ? (<>
      <DisableBackButton/>
      <h1>Take Test</h1>
      <div className="quiz-timer">
        Time Left: {Math.floor(timeInSeconds / 60)}:{(timeInSeconds % 60).toString().padStart(2, "0")}
      </div>
     
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
          <button type="submit" onClick={handleManualSubmit}>Submit Answers</button>
          </div>
        </form>
        </>
      ) : (
        <>
        <p>Question not available...</p>
        <button className="button start-quiz-button" onClick={()=>window.history.back()}>Back</button>
        </>
      )} </>: <NotFound/>
      }
    </div>
  );
}
