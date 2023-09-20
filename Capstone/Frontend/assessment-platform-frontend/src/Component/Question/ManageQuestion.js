import React, { useState, useEffect } from 'react'
import axios from 'axios';
import { useParams, Link, useNavigate } from 'react-router-dom';
import NotFound from '../NotFound';
import Navbar from '../Navbar/Navbar';
import "./question.css";
export default function ManageQuestion() {
    const [questions, setQuestions] = useState([]);
    const { quizId } = useParams();
    const role = localStorage.getItem('userRole');
    const navigate = useNavigate();
  useEffect(()=>{
    loadQuestions();
  })

      const loadQuestions = async () => {
        try {
          const result = await axios.get(`http://localhost:8080/ques/quiz/${quizId}`);
          setQuestions(result.data);
        } catch (error) {
          console.error("Error loading questions:", error);
        }
      };


      const deleteQuestion = async (id) => {
        try {
          await axios.delete(`http://localhost:8080/ques/${id}`);
          console.log("Question deleted successfully");
          loadQuestions();
        } catch (error) {
          console.log("Failed to Delete Question" + error);
        }
      };
  
    return (
        <>
        {(role === "admin" ||
          role === "user") ?
            <>
              <Navbar />
              <div className="page-container">
                <h2>Available Questions for the Quiz:</h2>
               {role === 'admin' && <Link className="" to={`/addQuestion/${quizId}`}>
                  Add Question
                </Link>}
                <div className="quiz-cards" >
                  {questions.map((question) => (
                    <div key={question.quesId} className="quiz-card">
                      <h3>{question.question}</h3>
                      <p>1. {question.option1}</p>
                      <p>2. {question.option2}</p>
                      <p>3. {question.option3}</p>
                      <p>4. {question.option4}</p>
                      <p>Answer: {question.answer}</p>
                      <button
                        className="button-edit"
                        onClick={() => navigate(`/updatequestion/${question.quesId}`)}
                      >
                        Update
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
            </> :<NotFound/>
          }
      </>
    );
}
