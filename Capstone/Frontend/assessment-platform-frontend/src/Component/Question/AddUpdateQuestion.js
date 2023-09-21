import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import NotFound from "../NotFound";
import Swal from "sweetalert2";
import Navbar from "../Navbar/Navbar";

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
      axios
        .get(`http://localhost:8080/ques/${quesId}`)
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
          console.log(response);
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
        const response = await axios.post(
          "http://localhost:8080/ques/save",
          requestData
        );
        console.log(response);
        if (response.status === 201) {
          Swal.fire({
            title: "Success",
            text: "Question Added Successfully",
            icon: "success",
          });
          console.log("Question added Successfully");
          navigate(`/manage-questions/${quizId}`);
        } else {
          console.error("Failed to add Question");
        }
      } else {
        const response = await axios.put(
          `http://localhost:8080/ques/${quesId}`,
          requestData
        );
        console.log(response);
        if (response.status === 200) {
          Swal.fire({
            title: "Success",
            text: "Question Updated Successfully",
            icon: "success",
          });
          console.log("Question Updated Successfully");
          window.history.back();
        } else {
          console.log("Failed to Update Question");
        }
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
                <div>
                  <label>Enter Question:</label>
                  <input
                    type="text"
                    name="question"
                    value={formData.question}
                    onChange={handleInputChange}
                  />
                </div>
                <div>
                  <label>Option 1:</label>
                  <input
                    type="text"
                    name="option1"
                    value={formData.option1}
                    onChange={handleInputChange}
                  />
                </div>
                <div>
                  <label>Option 2:</label>
                  <input
                    type="text"
                    name="option2"
                    value={formData.option2}
                    onChange={handleInputChange}
                  />
                </div>
                <div>
                  <label>Option 3:</label>
                  <input
                    type="text"
                    name="option3"
                    value={formData.option3}
                    onChange={handleInputChange}
                  />
                </div>
                <div>
                  <label>Option 4:</label>
                  <input
                    type="text"
                    name="option4"
                    value={formData.option4}
                    onChange={handleInputChange}
                  />
                </div>
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
                  <button type="submit">
                    {quizId ? "Add Question" : "Update Question"}
                  </button>
                  <button type="button" className="button-cancel">
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

export default AddUpdateQuestion;
