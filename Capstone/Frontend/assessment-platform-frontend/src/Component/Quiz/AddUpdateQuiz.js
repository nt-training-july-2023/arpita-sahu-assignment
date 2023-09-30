import React, { useState, useEffect } from "react";
import {useParams } from "react-router-dom";
import NotFound from "../NotFound";
import Navbar from "../Navbar/Navbar";
import ServiceURL from "../Service/ServiceURL";
import SweetAlertService from "../SweetAlert/SweetAlertService";

function AddUpdateQuiz() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [quizTimer, setQuizTimer] = useState("");
  const [titleError, setTitleError] = useState("");
  const [descriptionError, setDescriptionError] = useState("");
  const [timerError, setTimerError] = useState("");
  const { quizId, categoryId } = useParams();
  const role = localStorage.getItem("userRole");

  useEffect(() => {
    if (quizId) {
      ServiceURL.getQuizByQuizId(quizId)
        .then((response) => {
          setTitle(response.data.title);
          setDescription(response.data.description);
          setQuizTimer(response.data.quizTimer);
        })
        .catch((error) => {
          console.error("An error occurred:", error);
        });
    }
  }, [quizId]);

  const handleTitleChange = (e) => {
    setTitle(e.target.value);
    if (titleError) {
      setTitleError("");
    }
  };

  const handleDescriptionChange = (e) => {
    setDescription(e.target.value);
    if (descriptionError) {
      setDescriptionError("");
    }
  };

  const handleTimerChange = (e) => {
    const numericInput = e.target.value.replace(/\D/g, "");
    setQuizTimer(numericInput);
    if (timerError) setTimerError("");
  };

  const validateCategory = () => {
    let isValid = true;
    if (!title) {
      setTitleError("Title is required");
      isValid = false;
    }

    if (!description) {
      setDescriptionError("Description is required");
      isValid = false;
    }
    if (!quizTimer) {
      setTimerError("Timer is required");
      isValid = false;
    }
    return isValid;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateCategory()) {
      return;
    }

    const categoryObject = {
      categoryId: categoryId,
    };

    try {
      const quizItem ={title, description, quizTimer, category: categoryObject}
      if (!quizId) {
        await ServiceURL.addQuiz(quizItem).then((response)=>{
        if (response.status === 201) {
          SweetAlertService.showNotificationAlert("Success", "Quiz Added Successfully","success");
          window.history.back();
        }
      }) 
     }  else {
      await ServiceURL.updateQuiz(quizId,quizItem).then((response)=>{
        if (response.status === 200) {
          SweetAlertService("Success","Quiz Updated Successfully","success");
          window.history.back();
        } 
       })
       }
     } catch (error) {
      if (error.response && error.response.status === 302) {
        SweetAlertService.showNotificationAlert("Error!","Title already exists","error");
      } 
    }
  };

  return (
    <>
      {role === "admin" ? (
        <>
          <Navbar />
          <div className="category-form-container">
            <div className="category-form-card">
              <h2>{quizId ? "Update Quiz" : "Add Quiz"}</h2>
              <form onSubmit={handleSubmit}>
                <div>
                  <label>Title:</label>
                  <input
                    type="text"
                    value={title}
                    onChange={handleTitleChange}
                  />
                  {titleError && <p className="error-message">{titleError}</p>}
                </div>
                <div>
                  <label>Description:</label>
                  <textarea
                    value={description}
                    onChange={handleDescriptionChange}
                  />
                  {descriptionError && (
                    <p className="error-message">{descriptionError}</p>
                  )}
                </div>
                <div>
                  <label>Timer:</label>
                  <input
                    type="text"
                    onChange={handleTimerChange}
                    value={quizTimer}
                  />
                  {timerError && <p className="error-message">{timerError}</p>}
                </div>
                <div>
                  <button type="submit">
                    {quizId ? "Update Quiz" : "Add Quiz"}
                  </button>
                  <button type="button" className="button-cancel" onClick={()=> window.history.back()}>
                    Cancel
                  </button>
                </div>
              </form>
            </div>
          </div>
        </>
      ) : (
        <NotFound />
      )}
    </>
  );
}

export default AddUpdateQuiz;
