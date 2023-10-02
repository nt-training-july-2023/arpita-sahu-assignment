import React, { useEffect, useState } from "react";
import { useNavigate, useParams, Link } from "react-router-dom";
import "./quiz.css";
import Navbar from "../Navbar/Navbar";
import NotFound from "../NotFound";
import ServiceURL from "../Service/ServiceURL";
import SweetAlertService from "../SweetAlert/SweetAlertService";

export default function ManageQuiz() {
  const [quizzes, setQuizzes] = useState([]);
  const [categories, setCategories] = useState([]);
  const { categoryId } = useParams();
  const navigate = useNavigate();
  const role = localStorage.getItem("userRole");
  useEffect(() => {
    loadQuizzes();
    loadCategory();
  }, []);
  const loadQuizzes = async () => {
    try {
      const result = await ServiceURL.getQuizByCategoryId(categoryId);
      setQuizzes(result.data);
    } catch (error) {
      console.error("Error loading quizzes:", error);
    }
  };

  const loadCategory = async () => {
    try {
      const category = await ServiceURL.getCategoryById(categoryId);
      setCategories(category.data);
    } catch (error) {
      console.error("Error loading category:", error);
    }
  };

  const deleteQuiz = async (id) => {
    try {
      const result = await SweetAlertService.showDeleteNotificationAlert(
        'Delete Quiz',
        'Are you sure you want to delete this Quiz?'
      );
      if (result.isConfirmed) {
        await ServiceURL.deleteQuiz(id);
        loadQuizzes();
      }
    } catch (error) {
      console.error("Failed to Delete Quiz" + error);
    }
  };

  return (
    <>
      {role === "admin" || role === "user" ? (
        <>
          <Navbar />
          <div className="page-container">
            <h2>Available Quizzes for the Category: {categories.title} </h2>
            {role === "admin" && (
              <Link className="" to={`/addQuiz/${categoryId}`}>
                Add Quiz
              </Link>
            )}
            <div className="quiz-cards">
              {quizzes.map((quiz) => (
                <div key={quiz.quizId} className="quiz-card">
                  <h3>Title : {quiz.title}</h3>
                  <p>Description : {quiz.description}</p>
                  <p>Quiz Timer : {quiz.quizTimer}</p>
                  {role === "user" ? (
                    <button
                      className="start-quiz-button"                   
                      onClick={() =>{ 
                        localStorage.setItem('selectedQuizTitle', quiz.title);
                        SweetAlertService.showQuizInstructionAlert().then((result) => {
                          if (result.isConfirmed) {
                            navigate(`/takeTest/${quiz.quizId}`);
                          }
                        });
                      }}
                    >
                      Start Quiz
                    </button>
                  ) : (
                    <>
                      <button
                        className="button-edit"
                        onClick={() => navigate(`/updateQuiz/${quiz.quizId}`)}
                      >
                        Update
                      </button>
                      <button
                        className="button-delete"
                        onClick={() => deleteQuiz(quiz.quizId)}
                      >
                        Delete Quiz
                      </button>
                      <button
                        className="start-quiz-button"
                        onClick={() =>
                          navigate(`/manage-questions/${quiz.quizId}`)
                        }
                      >
                        Questions
                      </button>
                    </>
                  )}
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
