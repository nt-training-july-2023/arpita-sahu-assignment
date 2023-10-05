import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Signup from "./Pages/LoginRegistration/Signup";
import Login from "./Pages/LoginRegistration/Login";
import AddCategory from "./Pages/Category/AddCategory";
import ListCategories from "./Pages/Category/ListCategories";
import Navbar from "./Component/Navbar/Navbar";
import NotFound from './Component/NotFound/NotFound';
import AddUpdateQuiz from "./Pages/Quiz/AddUpdateQuiz";
import ManageQuiz from "./Pages/Quiz/ManageQuiz";
import ManageQuestion from "./Pages/Question/ManageQuestion";
import AddUpdateQuestion from "./Pages/Question/AddUpdateQuestion";
import TakeTest from "./Pages/Question/TakeTest";
import Result from "./Pages/Result/Result";
import HomePage from "./Pages/HomePage/HomePage";
import { useState } from "react";

function App() {
 const [isRefresh, setIsRefresh] = useState(false)
 function setTrue(){
  setIsRefresh(true);
 }
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path ="/" element={<HomePage/>}/>
          <Route path="/navbar" element={<Navbar />} />
          <Route path="/Signup" element={<Signup />} />
          <Route path="/Login" element={<Login />} />
          <Route path="/addcategory" element={<AddCategory />} />
          <Route path="/listcategory" element={<ListCategories />} />
          <Route path="/updatecategory/:id" element={<AddCategory />} />
          <Route path="/notfound" element={<NotFound />} />
          <Route path="/addQuiz/:categoryId" element = {<AddUpdateQuiz/>}/>
          <Route path="/quiz/:categoryId" element={<ManageQuiz setTrue={setTrue}/>}/>
          <Route path="/updateQuiz/:quizId" element ={<AddUpdateQuiz/>}/>
          <Route path="/manage-questions/:quizId" element={<ManageQuestion/>}/>
          <Route path="/addquestion/:quizId" element={<AddUpdateQuestion/>}/>
          <Route path="/updatequestion/:quesId" element={<AddUpdateQuestion/>}/>
          <Route path="/takeTest/:quizId" element ={<TakeTest isRefresh={isRefresh}/>}/>
          <Route path="/result" element ={<Result/>}/>
          {/* <Route path="*" element={<Navigate to="/notfound" />} /> */}
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
