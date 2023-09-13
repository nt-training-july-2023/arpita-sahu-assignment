import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Signup from "./Component/LoginRegistration/Signup";
import Login from "./Component/LoginRegistration/Login";
import UserDashboard from "./Component/User/UserDashboard";
import AdminDashboard from "./Component/Admin/AdminDashboard";
import AddCategory from "./Component/Category/AddCategory";
import ListCategories from "./Component/Category/ListCategories";
import Navbar from "./Component/Navbar/Navbar";
import Quiz from "./Component/Quiz/Quiz";
import NotFound from './Component/NotFound';
import AddQuiz from "./Component/Quiz/AddQuiz";


function App() {
 
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/navbar" element={<Navbar />} />
          <Route path="/Signup" element={<Signup />} />
          <Route path="/Login" element={<Login />} />
          <Route path="/userDashboard" element={<UserDashboard/>} />
          <Route path="/adminDashboard" element={<AdminDashboard/>} />
          <Route path="/addcategory" element={<AddCategory />} />
          <Route path="/listcategory" element={<ListCategories />} />
          <Route path="/updateCategory/:id" element={<AddCategory />} />
          <Route path="/notfound" element={<NotFound />} />
          <Route path="/addQuiz/:categoryId" element = {<AddQuiz/>}/>
          <Route path="/quiz/:categoryId" element={<Quiz/>}/>
          <Route path="/updateQuiz/:quizId" element ={<AddQuiz/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
