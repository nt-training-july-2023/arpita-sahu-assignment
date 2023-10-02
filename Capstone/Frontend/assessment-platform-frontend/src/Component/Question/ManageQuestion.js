import React, { useState, useEffect } from "react";
import { useParams, Link, useNavigate } from "react-router-dom";
import NotFound from "../NotFound";
import Navbar from "../Navbar/Navbar";
import ServiceURL from "../Service/ServiceURL";
import "./question.css";
import SweetAlertService from "../SweetAlert/SweetAlertService";

export default function ManageQuestion() {
  const [questions, setQuestions] = useState([]);
  const { quizId } = useParams();
  const role = localStorage.getItem("userRole");
  const navigate = useNavigate();
  useEffect(() => {
    loadQuestions();
  },[]);

  const loadQuestions = async () => {
    try {
      const result = await ServiceURL.getQuestionByQuizId(quizId);
      setQuestions(result.data);
    } catch (error) {
      console.error("Error loading questions:", error);
    }
  };

  const deleteQuestion = async (id) => {
    try {
      const result = await SweetAlertService.showDeleteNotificationAlert
      ('Delete Question','Are you sure you want to delete this Question?');
      if(result.isConfirmed){
      await ServiceURL.deleteQuestion(id);
      loadQuestions();
    } 
  }catch (error) {
      console.error("Failed to Delete Question" + error);
    }
  };

  return (
    <>
      {role === "admin" || role === "user" ? (
        <>
          <Navbar />
          <div className="page-container">
            <h2>Available Questions for the Quiz:</h2>
            {role === "admin" && (
              <Link className="" to={`/addQuestion/${quizId}`}>
                Add Question
              </Link>
            )}
            <div>
              {questions.map((question) => (
                <div key={question.quesId} className="question-card">
                  <h3>{question.question}</h3>
                  <p>1. {question.option1}</p>
                  <p>2. {question.option2}</p>
                  <p>3. {question.option3}</p>
                  <p>4. {question.option4}</p>
                  <p>Answer: {question.answer}</p>
                  <button
                    className="button-edit"
                    onClick={() =>
                      navigate(`/updatequestion/${question.quesId}`)
                    }
                  >
                    Update Question
                  </button>
                  <button
                    className="button-delete"
                    onClick={() => deleteQuestion(question.quesId)}
                  >
                    Delete Question
                  </button>
                </div>
              ))}
            </div>
          </div>
        </>
      ) : (
        <NotFound />
      )}
    </>
  );
}
