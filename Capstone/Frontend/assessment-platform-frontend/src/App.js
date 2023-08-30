import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Signup from "./Component/LoginRegistration/Signup"
import Login from "./Component/LoginRegistration/Login";
import UserDashboard from "./Component/User/UserDashboard";
import AdminDashboard from "./Component/Admin/AdminDashboard";
import AddCategory from "./Component/Category/AddCategory";
import ListCategories from "./Component/Category/ListCategories"

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/Signup" element={<Signup />} />
          <Route path="/Login" element={<Login />} />
          <Route path="/userDashboard" element={<UserDashboard />} />
          <Route path="/adminDashboard" element={<AdminDashboard />} />
          <Route path="/addcategory" element={<AddCategory />} />
          <Route path="/listCategory" element={<ListCategories />} />
          <Route path="/addcategory/:id" element={<AddCategory />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
