import React from "react";
import { Link, useNavigate } from "react-router-dom";
import "../Navbar/Navbar.css";
import Swal from "sweetalert2";
export default function Navbar() {
  const navigate = useNavigate();
  const role = localStorage.getItem('userRole');

  const handleLogout =()=>{
    Swal.fire({
      title: "Success",
      text: "Logout Successfully!",
      icon: "success"
    });
   localStorage.removeItem('isLoggedIn');
   localStorage.removeItem('userRole');
   navigate('/login');
  }
  return (
    <div className="topnav" id="myTopnav">
      <Link to ="">Assessment Platform</Link> 
      {role === 'admin'?  <Link to ="/adminDashboard" className="active">
        Home
      </Link>:  <Link to ="/userDashboard" className="active">
        Home
      </Link>}
     
      <Link to ="#news">About</Link>
      <Link to ="/listcategory">Categories</Link>
      <Link to ="/login"><button onClick={handleLogout}>Logout</button></Link>
    </div>
  );
}
