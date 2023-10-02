import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import NotFound from "../NotFound";
import Navbar from "../Navbar/Navbar";
import ServiceURL from "../Service/ServiceURL";
import SweetAlertService from "../SweetAlert/SweetAlertService";
function AddCategory() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [titleError, setTitleError] = useState("");
  const [descriptionError, setDescriptionError] = useState("");
  const navigate = useNavigate();
  const { id } = useParams();
  const role = localStorage.getItem("userRole");
  useEffect(() => {
    if (id) {
      ServiceURL.getCategoryById(id)
        .then((response) => {
          setTitle(response.data.title);
          setDescription(response.data.description);
        })
        .catch((error) => {
          console.error("An error occurred:", error);
        });
    }
  }, [id]);

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
    try {
      const category = { title, description };
      if (!id) {
         ServiceURL.addCategory(category)
          .then((response) => {
            if (response.status === 200) {
              SweetAlertService.showNotificationAlert("Success","Category Added Successfully", "success");
              window.history.back();
            } 
            })
        .catch((error) => {
            if (error.response && error.response.status === 302) {
              SweetAlertService.showNotificationAlert("Error!","Title already exists","error");
            } 
          });
      } else {
        await ServiceURL.updateCategory(id, category).then((response) => {
          if (response.status === 200) {
            SweetAlertService.showNotificationAlert("Success","Category Updated Successfully","success");
            window.history.back();
          } 
        });
      }
    } catch (error) {
      console.error("An error occurred:", error);
    }
  };
  return (
    <>
      <Navbar />
      {role === "admin" ? (
        <>
          <div className="category-form-container">
            <div className="category-form-card">
              <h2>{id ? "Update Category" : "Add Category"}</h2>
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
                    {id ? "Update Category" : "Add Category"}
                  </button>
                  <button
                    type="button"
                    className="button-cancel"
                    onClick={() => navigate("/listcategory")}
                  >
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

export default AddCategory;
