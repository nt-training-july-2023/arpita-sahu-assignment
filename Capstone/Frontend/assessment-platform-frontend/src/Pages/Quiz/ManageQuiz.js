import React, { useEffect, useState } from "react";
import { useNavigate, useParams, Link } from "react-router-dom";
import "./quiz.css";
import Navbar from "../../Component/Navbar/Navbar";
import NotFound from "../../Component/NotFound/NotFound";
import ServiceURL from "../../Component/Service/ServiceURL";
import SweetAlertService from "../../Component/SweetAlert/SweetAlertService";
import ButtonComponent from "../../Component/ButtonComponent/ButtonComponent";

export default function ManageQuiz({setTrue}) {
  const [quizzes, setQuizzes] = useState([]);
  const [categories, setCategories] = useState([]);
  const [questions, setQuestions] = useState([]); 
  const { categoryId } = useParams();
  const navigate = useNavigate();
  const role = localStorage.getItem("userRole");
  useEffect(() => {
    loadQuizzes();
    loadCategory();
    
  }, []);
 
  const loadQuizzes = async () => {
   if(role === 'user'){
    try {
      const result = await ServiceURL.getQuizByCategoryId(categoryId);
      const quizzesWithQuestions = await Promise.all(result.data.map(async (quiz) => {
        const questionData = await ServiceURL.getQuestionByQuizId(quiz.quizId);
        return questionData.data.length > 0 ? quiz : null;
      }));
  
      setQuizzes(quizzesWithQuestions.filter(Boolean));
    } catch (error) {
      console.error("Error loading quizzes:", error);
    }
  }else{
    try {
      const result = await ServiceURL.getQuizByCategoryId(categoryId);
      setQuizzes(result.data);
    } catch (error) {
      console.error("Error loading quizzes:", error);
    }
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
                    <ButtonComponent
                    className="start-quiz-button"                   
                    onClick={() =>{ 
                      localStorage.setItem('selectedQuizTitle', quiz.title);
                      SweetAlertService.showQuizInstructionAlert().then((result) => {
                        if (result.isConfirmed) {
                          setTrue();
                          navigate(`/takeTest/${quiz.quizId}`);
                        }
                      });
                    }}
                    label="Start Quiz"
                    />
                  ) : (
                    <>
                    <ButtonComponent
                     className="button-edit"
                     onClick={() => navigate(`/updateQuiz/${quiz.quizId}`)}
                     label="Update"
                    />
                    <ButtonComponent 
                     className="button-delete"
                     onClick={() => deleteQuiz(quiz.quizId)}
                     label="Delete Quiz"
                    />
                     <ButtonComponent 
                     className="start-quiz-button"
                     onClick={() =>
                       navigate(`/manage-questions/${quiz.quizId}`)
                     }
                     label="Questions"
                     />
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
