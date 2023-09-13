import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, useParams, Link} from "react-router-dom";
import "./quiz.css";
import Navbar from "../Navbar/Navbar";
export default function Quiz() {
  const [quizzes, setQuizzes] = useState([]);
  const [categories, setCategories] = useState([]);
  const { categoryId } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    loadQuizzes();
    loadCategory();
  }, []);
  const loadQuizzes = async () => {
    try {
      const result = await axios.get(
        `http://localhost:8080/quiz/category/${categoryId}`
      );
      setQuizzes(result.data);
    } catch (error) {
      console.error("Error loading quizzes:", error);
    }
  };

  const loadCategory = async () => {
    try {
      const category = await axios.get(
        `http://localhost:8080/category/${categoryId}`
      );
      setCategories(category.data);
    } catch (error) {
      console.error("Error loading category:", error);
    }
  };

  const deleteQuiz = async (id) =>{
    try{
    await axios.delete(`http://localhost:8080/quiz/${id}`);
     console.log("quiz deleted successfully");
     loadQuizzes(); 
    }catch(error){
      console.log("Failed to Delete Quiz" + error);
    }
  }

  return (
    <>
      <Navbar />
      <div className="page-container">
      <h2>Available Quizzes for the Category: {categories.title} </h2>
      <Link className="" to ={`/addQuiz/${categoryId}`} >Add Quiz</Link>
      <div className="quiz-cards">
        {quizzes.map((quiz) => (
          <div key={quiz.quizId} className="quiz-card">
            <h3>{quiz.title}</h3>
            <p>{quiz.description}</p>
            <button className="start-quiz-button">Start Quiz</button>
            <button className="button-edit" onClick={() => navigate(`/updateQuiz/${quiz.quizId}`)}>Update</button>
            <button className="button-delete" onClick={()=> deleteQuiz(quiz.quizId)}>Delete Quiz</button>
          </div>
        ))}
      </div>
    </div>
    </>
  );
}
