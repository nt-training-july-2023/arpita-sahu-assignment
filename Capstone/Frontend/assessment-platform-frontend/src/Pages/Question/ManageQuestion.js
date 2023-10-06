import React, { useState, useEffect } from "react";
import { useParams, Link, useNavigate } from "react-router-dom";
import NotFound from "../../Component/NotFound/NotFound";
import Navbar from "../../Component/Navbar/Navbar";
import ServiceURL from "../../Component/Service/ServiceURL";
import "./question.css";
import SweetAlertService from "../../Component/SweetAlert/SweetAlertService";
import ButtonComponent from "../../Component/ButtonComponent/ButtonComponent";

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
                  <ButtonComponent
                   className="button-edit"
                   onClick={() =>
                     navigate(`/updatequestion/${question.quesId}`)
                   }
                   label="Update Question"
                  />
                  <ButtonComponent
                   className="button-delete"
                   onClick={() => deleteQuestion(question.quesId)}
                   label="Delete Question"
                  />
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
