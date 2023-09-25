import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import NotFound from "../NotFound";
import Swal from "sweetalert2";
import Navbar from "../Navbar/Navbar";
import ServiceURL from "../Service/ServiceURL";
import axios from "axios";
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
            console.log(response);
  
            if (response.status === 201) {
              Swal.fire({
                title: "Success",
                text: "Category Added Successfully",
                icon: "success",
              });
              console.log("Category added successfully");
              window.history.back();
            } else {
              console.error("Failed to add category");
            }
          })
          .catch((error) => {
            if (error.response && error.response.status === 409) {
              Swal.fire({
                icon: "error",
                title: "Error!",
                text: "Title already exists",
              });
              console.error("Category with the same title already exists");
            } else {
              console.error("An error occurred:", error.message);
            }
          });
         
      } else {
        const response = await axios.put(
          `http://localhost:8080/category/${id}`,
          {
            title,
            description,
          }
        );

        if (response.status === 200) {
          Swal.fire({
            title: "Success",
            text: "Category Updated Successfully",
            icon: "success"
          });
          console.log("Category updated successfully");
          window.history.back();
        } else {
          console.error("Failed to update category");
        }
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
                  <button type="button" className="button-cancel" onClick={()=> navigate('/listcategory')}>
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
