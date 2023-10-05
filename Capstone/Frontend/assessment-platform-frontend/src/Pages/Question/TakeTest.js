import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import DisableBackButton from "../../Component/DisableBackButton/DisableBackButton";
import ServiceURL from "../../Component/Service/ServiceURL";
import SweetAlertService from "../../Component/SweetAlert/SweetAlertService";
import NotFound from "../../Component/NotFound/NotFound";
import Swal from "sweetalert2";
import ButtonComponent from "../../Component/ButtonComponent/ButtonComponent";

export default function TakeTest({isRefresh}) {
  const [selectedAnswers, setSelectedAnswers] = useState({});
  const { quizId } = useParams();
  const navigate = useNavigate();
  const StoreQuestions = localStorage.getItem('StoreQuestion')?JSON.parse(localStorage.getItem('StoreQuestion')):[];
  const [questions, setQuestions] = useState(StoreQuestions);
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
  }, []);

  useEffect(() => {
    if (!isRefresh) {
      calculateMarks();
      CountSelectedOptions();
      Swal.fire({
        title: 'Page Reloaded', 
        text: 'Answer Submitted', 
        icon: 'warning',   
        allowOutsideClick: false,
        backdrop: `
        rgba(128, 128, 128, 0.98)
        `,
        confirmButtonText: 'Ok',
      }).then((result) => {
        if (result.isConfirmed) {
          handleSubmit();
        }
      });
    }
  }, [isRefresh]);

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
      const result = await ServiceURL.getQuizByQuizId(quizId);
      const timerInMinutes = result.data.quizTimer;
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
      localStorage.setItem('StoreQuestion',JSON.stringify(result.data));
    } catch (error) {
      console.error("Error loading questions:", error);
    }
  };
  const handleAnswerSelection = (questionId, selectedOption) => {
    setSelectedAnswers((prevSelectedAnswers) => ({
      ...prevSelectedAnswers,
      [questionId]: selectedOption,
    }));
    localStorage.setItem(`selectedOption_${questionId}`,selectedOption);
  };
 const formatDateTime=(dateTime)=> {
    const year = dateTime.getFullYear();
    const month = (dateTime.getMonth() + 1).toString().padStart(2, '0'); 
    const day = dateTime.getDate().toString().padStart(2, '0');
    const hours = dateTime.getHours();
    const minutes = dateTime.getMinutes();
    const seconds = dateTime.getSeconds();
    const storeDateAndTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    localStorage.setItem('DateAndTime',storeDateAndTime)
    return storeDateAndTime ;
  }
  const handleManualSubmit = async (e) => {
    if(e)
    e.preventDefault();
    const result = await SweetAlertService.showManualSubmitAlert();
    if (result.isConfirmed) {
      handleSubmit();
    }
  };
  const showAutoSubmitMessage = () => {
    SweetAlertService.showAutoSubmitAlert(handleSubmit);
  };

  const calculateMarks = () =>{
    let obtainedScore = 0;
    questions.forEach((question) => {
      const correctAnswer = question.answer;
      const selectedAnswer = localStorage.getItem(`selectedOption_${question.quesId}`);
      if (selectedAnswer === correctAnswer) {
          obtainedScore += 1;       
        }
    });
    setScore(obtainedScore);
    localStorage.setItem('StoreScore', obtainedScore); 
    return obtainedScore;
  }

  const handleSubmit = async(e) => {
    if(e){
    e.preventDefault();
    }
    const currentTime = new Date();
    const formattedTime = formatDateTime(currentTime);
     const marks=calculateMarks();  
    localStorage.getItem('StoreQuestion');
    try {
      const resultItem ={
        obtainedMarks: marks,
        totalMarks:questions.length,
        userName:userName,
        email:userEmail,
        quizTitle:quizTitle,
        categoryTitle:categoryTitle,
        totalNumOfQues: questions.length,
        dateAndTime:formattedTime,
        numofAttemptedQues: !isRefresh
        ? localStorage.getItem('storeCountSelectedOption')
        : Object.keys(selectedAnswers).length,
      }
     await ServiceURL.addResult(resultItem);
     removeLocalStorageItems();
    } catch (error) {
      console.error("Error submitting quiz results:", error);
    }
    navigate('/result');
  };
  const CountSelectedOptions = ()=>{
    let count=0;
    for(let i=0; i < localStorage.length; i++){
      const option = localStorage.key(i);
      if(option.startsWith("selectedOption_"))
      count++;
    }
    localStorage.setItem('storeCountSelectedOption',count);
    return count;
  }
  const removeLocalStorageItems = () =>{
    const optionsToRemove = [];
    for(let i=0; i<localStorage.length; i++){
      const option = localStorage.key(i);
      if(option.startsWith("selectedOption_")){
        optionsToRemove.push(option);
      }
    }
    optionsToRemove.forEach((key)=>{
      localStorage.removeItem(key);
    })
    localStorage.removeItem('StoreScore');
    localStorage.removeItem('StoreQuestion');
    localStorage.removeItem('DateAndTime');
    localStorage.removeItem('storeCountSelectedOption');
  }

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
            <ButtonComponent
            type="submit"
            onClick={handleManualSubmit}
            label = "Submit Answers"
            />
          </div>
        </form>
        </>
      ) : (
        <>
        <p>Question not available...</p>
        <ButtonComponent
        className="button start-quiz-button"
        onClick={()=>window.history.back()}
        label="Back"
        />
        </>
      )} </>: <NotFound/>
      }
    </div>
  );
}
