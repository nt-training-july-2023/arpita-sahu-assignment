import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import NotFound from "../../Component/NotFound/NotFound";
import Navbar from "../../Component/Navbar/Navbar";
import ServiceURL from "../../Component/Service/ServiceURL";
import SweetAlertService from "../../Component/SweetAlert/SweetAlertService";
import InputComponent from "../../Component/InputComponent/InputComponent";
import ButtonComponent from "../../Component/ButtonComponent/ButtonComponent";

function AddUpdateQuestion() {
  const [formData, setFormData] = useState({
    question: "",
    option1: "",
    option2: "",
    option3: "",
    option4: "",
    answer: "",
  });

  const [error, setError] = useState("");
  const { quesId, quizId } = useParams();
  const role = localStorage.getItem("userRole");
  const navigate = useNavigate();

  useEffect(() => {
    if (quesId) {
      ServiceURL.getQuestionByQuestionId(quesId)
        .then((response) => {
          setFormData({
            ...formData,
            question: response.data.question,
            option1: response.data.option1,
            option2: response.data.option2,
            option3: response.data.option3,
            option4: response.data.option4,
            answer: response.data.answer,
          });
        })
        .catch((error) => {
          console.error("An error occurred:", error);
        });
    }
  }, [quesId]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const validateQuestion = () => {
    let isValid = true;
    const { question, option1, option2, option3, option4, answer } = formData;
  
    if (!question || !option1 || !option2 || !option3 || !option4 || !answer) {
      setError("* All fields are required");
      isValid = false;
    } else if (
      option1 === option2 ||
      option1 === option3 ||
      option1 === option4 ||
      option2 === option3 ||
      option2 === option4 ||
      option3 === option4
    ) {
      setError("* Options must be unique");
      isValid = false;
    } else {
      setError("");
    }
  
    return isValid;
  };
  

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateQuestion()) {
      return;
    }
    try {
      const requestData = {
        ...formData,
        quizId: quizId,
      };

      if (quizId) {
       await ServiceURL.addQuestion(requestData).then((response) =>{
        if (response.status === 200) {
          SweetAlertService.showNotificationAlert("Success","Question Added Successfully","success");
          navigate(`/manage-questions/${quizId}`);
        } 
       })
      } else {
        await ServiceURL.updateQuestion(quesId, requestData).then((response)=>{
        if (response.status === 200) {
          SweetAlertService.showNotificationAlert("Success","Question Updated Successfully","success");
          window.history.back();
        } 
      })
      }
    } catch (error) {
      console.error("An error  occurred:", error);
    }
  };

  const answerOptions = [
    { label: "Option 1", value: formData.option1 },
    { label: "Option 2", value: formData.option2 },
    { label: "Option 3", value: formData.option3 },
    { label: "Option 4", value: formData.option4 },
  ];

  return (
    <>
      {role === "admin" ? (
        <>
          <Navbar />
          <div className="category-form-container">
            <div className="category-form-card">
              <h2>{quizId ? "Add Question" : "Update Question"}</h2>
              <form onSubmit={handleSubmit}>
              <InputComponent
                label="Enter Question:"
                type="text"
                name="question"
                value={formData.question}
                onChange={handleInputChange}
                />
                <InputComponent
                label="Option 1:"
                type="text"
                name="option1"
                value={formData.option1}
                onChange={handleInputChange}
                />
                <InputComponent
                label="Option 2:"
                type="text"
                name="option2"
                value={formData.option2}
                onChange={handleInputChange}
                />
                <InputComponent
                label="Option 3:"
                type="text"
                name="option3"
                value={formData.option3}
                onChange={handleInputChange}
                />
                <InputComponent
                label="Option 4:"
                type="text"
                name="option4"
                value={formData.option4}
                onChange={handleInputChange}
                />
                <div>
                  <label>Answer:</label>
                  <select
                    name="answer"
                    value={formData.answer}
                    onChange={handleInputChange}
                  >
                    <option value="">Select Answer</option>
                    {answerOptions.map((option, index) => (
                      <option key={index} value={option.value}>
                        {option.value}
                      </option>
                    ))}
                  </select>
                </div>
                {error && <p className="error-message">{error}</p>}
                <div>
                  <ButtonComponent
                  type="submit"
                  label= {quizId ? "Add Question" : "Update Question"}
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

export default AddUpdateQuestion;
