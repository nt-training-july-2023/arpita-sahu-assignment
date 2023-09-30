import React from "react";
import { Link, useNavigate } from "react-router-dom";
import "../Navbar/Navbar.css";
import SweetAlertService from "../SweetAlert/SweetAlertService";
export default function Navbar() {
  const navigate = useNavigate();
  const role = localStorage.getItem('userRole');

  const handleLogout =()=>{
   SweetAlertService.showNotificationAlert("Success","Logout Successfully!","success");
   localStorage.removeItem('isLoggedIn');
   localStorage.removeItem('userRole');
   localStorage.removeItem('name');
   localStorage.removeItem('selectedEmail');
   localStorage.removeItem('selectedQuizTitle');
   localStorage.removeItem('selectedCategoryTitle');
   navigate('/login');
  }
  return (
    <div className="topnav" id="myTopnav">
      <Link>Assessment Platform</Link> 
      {role === 'admin'?  <Link to ="/adminDashboard">
        Home
      </Link>:  <Link to ="/userDashboard">
        Home
      </Link>}
     
      <Link to ="">About</Link>
      <Link to ="/listcategory">Categories</Link>
      <Link to="/result">Results</Link>
      <button className="logout" onClick={handleLogout}>Logout</button>
    </div>
    
  );
}
