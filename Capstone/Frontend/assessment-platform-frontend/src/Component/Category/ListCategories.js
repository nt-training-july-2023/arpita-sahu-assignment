import React, { useState, useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";
import "./CategoryList.css";
import Navbar from "../Navbar/Navbar";
import NotFound from "../NotFound";
import ServiceURL from "../Service/ServiceURL";
import SweetAlertService from "../SweetAlert/SweetAlertService";

function CategoryList() {
  const [categories, setCategories] = useState([]);
  const navigate = useNavigate();
  const role = localStorage.getItem("userRole");

  useEffect(() => {
    loadCategories();
  }, []);

  const loadCategories = async () => {
    const result = await ServiceURL.getCategories();
    setCategories(result.data);
  };

  const deleteCategory = async (id) => {
    try{
    const result = await SweetAlertService.showDeleteNotificationAlert
    ('Delete Category','Are you sure you want to delete this category?');
    if (result.isConfirmed) {
      await ServiceURL.deleteCategory(id);
      loadCategories();
    }}
    catch (error) {
      console.log("An error occured:", error);
    }
  };

  return (
    <>
      {role === "admin" || role === "user" ? (
        <>
          <Navbar />
          <div className="category-wrapper-container">
            <div className="category-card">
              <div className="category_wrapper">
                <h2 className="cat_head">Category List</h2>
                {role === "admin" && (
                  <Link to="/addCategory">Add Category</Link>
                )}
                <table className="category-table">
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Title</th>
                      <th>Description</th>
                      {role === "admin" ? (
                        <>
                          <th style={{ textAlign: "end" }}>Actions</th>
                          <th></th>
                          <th></th>
                        </>
                      ) : (
                        <th>Actions</th>
                      )}
                    </tr>
                  </thead>
                  <tbody>
                    {categories.map((category, index) => (
                      <tr key={index}>
                        <td>{++index}</td>
                        <td>{category.title}</td>
                        <td>{category.description}</td>
                        {role === "admin" && (
                          <>
                            <td>
                              <button
                                className="button-edit"
                                onClick={() =>
                                  navigate(
                                    `/updatecategory/${category.categoryId}`
                                  )
                                }
                              >
                                Update
                              </button>
                            </td>
                            <td>
                              <button
                                className="button-delete"
                                onClick={() =>
                                  deleteCategory(category.categoryId)
                                }
                              >
                                Delete
                              </button>
                            </td>
                          </>
                        )}
                        <td>
                          <button
                            className="button-quiz"
                            onClick={() => {
                              localStorage.setItem(
                                "selectedCategoryTitle",
                                category.title
                              );
                              navigate(`/quiz/${category.categoryId}`);
                            }}
                          >
                            Quizzes
                          </button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </>
      ) : (
        <NotFound />
      )}
    </>
  );
}

export default CategoryList;
