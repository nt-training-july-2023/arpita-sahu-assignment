import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import NotFound from "../NotFound";
import Swal from "sweetalert2";
import Navbar from "../Navbar/Navbar";

function AddQuiz() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [titleError, setTitleError] = useState("");
  const [descriptionError, setDescriptionError] = useState("");
  const [categories, setCategories] = useState({});
  const { quizId, categoryId } = useParams();
  const role = localStorage.getItem("userRole");
  const navigate = useNavigate();
  useEffect(() => {
    if (quizId) {
      axios
        .get(`http://localhost:8080/quiz/${quizId}`)
        .then((response) => {
          setTitle(response.data.title);
          setDescription(response.data.description);
          setCategories(response.data.category);
          console.log(response);
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
      if (!quizId) {
        const response = await axios.post("http://localhost:8080/quiz/add", {
          title,
          description,
          category: categoryObject,
        });
        console.log(response);
        if (response.status === 201) {
          Swal.fire({
            title: "Success",
            text: "Quiz Added Successfully",
            icon: "success",
          });
          console.log("Quiz added Successfully");
        } else {
          console.error("Failed to add Quiz");
        }
      } else {
        const response = await axios.put(
          `http://localhost:8080/quiz/${quizId}`,
          {
            title,
            description,
            category: categoryObject,
          }
        );
        console.log(response);
        if (response.status === 200) {
          Swal.fire({
            title: "Success",
            text: "Quiz Updated Successfully",
            icon: "success",
          });
          console.log("Quiz Updated Successfully");
        } else {
          console.log("Failed to Update Category");
        }
      }
    } catch (error) {
      if (error.response && error.response.status === 409) {
        Swal.fire({
          icon: "error",
          title: "Error!",
          text: "Title already exists",
        });
        console.error("Quiz with the same title already exists");
      } else {
        console.error("An error occurred:", error.message);
      }
      console.error("An error occurred:", error);
    }
  };

  const handleNavigation = () => {
    navigate("/listcategory");
  };

  const handleCancelClick = () => {
    navigate("/listcategory");
  };

  return (
    <>
      <Navbar />
      <div className="category-form-container">
        {role === "admin" ? (
          <>
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
                  <button type="submit">
                    {quizId ? "Update Quiz" : "Add Quiz"}
                  </button>
                  <button type="button" className="button-cancel">
                    Cancel
                  </button>
                </div>
              </form>
            </div>
          </>
        ) : (
          <NotFound />
        )}
      </div>
    </>
  );
}

export default AddQuiz;
