import React, { useState, useEffect } from "react";
import {useParams } from "react-router-dom";
import NotFound from "../../Component/NotFound/NotFound";
import Navbar from "../../Component/Navbar/Navbar";
import ServiceURL from "../../Component/Service/ServiceURL";
import SweetAlertService from "../../Component/SweetAlert/SweetAlertService";
import InputComponent from "../../Component/InputComponent/InputComponent";
import TextAreaComponent from "../../Component/TextAreaComponent/TextAreaComponent";
import ButtonComponent from "../../Component/ButtonComponent/ButtonComponent";

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
        if (response.status === 200) {
          SweetAlertService.showNotificationAlert("Success", "Quiz Added Successfully","success");
          window.history.back();
        }
      }) 
     }  else {
      await ServiceURL.updateQuiz(quizId,quizItem).then((response)=>{
        if (response.status === 200) {
          SweetAlertService.showNotificationAlert("Success","Quiz Updated Successfully","success");
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
                 <InputComponent
                label="Title:"
                type="text"
                value={title}
                onChange={handleTitleChange}
                error={titleError}
                />
                <TextAreaComponent
                label="Descirption:"
                onChange={handleDescriptionChange}
                value={description}
                error={descriptionError}
               />
                 <InputComponent
                label="Timer:"
                type="text"
                value={quizTimer}
                onChange={handleTimerChange}
                error={timerError}
                />
                <div>
                  <ButtonComponent
                  type="submit"
                  label= {quizId ? "Update Quiz" : "Add Quiz"}
                  />
                 <ButtonComponent
                 type="button"
                 className="button-cancel"
                 onClick={()=> window.history.back()}
                 label="Cancel"
                 />
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
