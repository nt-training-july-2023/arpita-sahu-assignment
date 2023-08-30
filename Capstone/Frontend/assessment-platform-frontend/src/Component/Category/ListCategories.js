import React, { useState, useEffect } from "react";
import axios from "axios";
import {useNavigate, Link } from "react-router-dom";
import "./CategoryList.css";

function CategoryList() {
  const [categories, setCategories] = useState([]);
  const navigate=useNavigate();

  useEffect(() => {
    loadCategories();
  }, []);

  const loadCategories = async () => {
    const result = await axios.get('http://localhost:8080/category/');
    setCategories(result.data);
  };

  const deleteCategory = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/category/${id}`);
      console.log("category deleted successfully");
      loadCategories();
    } catch (error) {
      console.log("An error occured:", error);
    }
   
  };

  return (
    <div>
      <h2>Category List</h2>
      <Link to='/addCategory'>addCategory</Link>
      <table className="category-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {categories.map(category => (
            <tr key={category.categoryId}>
              <td>{category.categoryId}</td>
              <td>{category.title}</td>
              <td>{category.description}</td>
              <td><button  onClick={() => navigate(`/addcategory/${category.categoryId}`)}>Update</button></td>
              <td><button onClick={() => deleteCategory(category.categoryId)}>Delete</button></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default CategoryList;
