import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import "./quiz.css";
import Navbar from "../Navbar/Navbar";
export default function Quiz() {
  const [quizzes, setQuizzes] = useState([]);
  const [categories, setCategories]=useState([]);
  const { categoryId } = useParams();
  
  useEffect(() => {
    loadQuizzes();
    loadCategory();
  }, []);
  const loadQuizzes = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/quiz/category/${categoryId}`);
      setQuizzes(result.data);
    } catch (error) {
      console.error("Error loading quizzes:", error);
    }
  };

  const loadCategory = async () => {
    try {
      const category = await axios.get(`http://localhost:8080/category/${categoryId}`);
      setCategories(category.data);
    } catch (error) {
      console.error("Error loading category:", error);
    }
  };

  return (
    <>
    <Navbar/>
    <div className="page-container">  
    <div>
      <h2>Available Quizzes for the Category: {categories.title}</h2>
      <button  className="add-quiz-button">add quiz</button>
      </div>
      <div className="quiz-cards">
        {quizzes.map((quiz) => (
          <div key={quiz.id} className="quiz-card">
            <h3>{quiz.title}</h3>
            <p>{quiz.description}</p>
            <button className="start-quiz-button">Start Quiz</button>
          </div>
        ))}
      </div>
    </div>
    </>
  );
}
