import React from "react";
import { Link, useNavigate } from "react-router-dom";
import "../Navbar/Navbar.css";
export default function Navbar() {
  const navigate = useNavigate();

  const handleLogout =()=>{
   localStorage.removeItem('isLoggedIn');
   localStorage.removeItem('userRole');
   navigate('/login');
  }
  return (
    <div className="topnav" id="myTopnav">
      <Link to ="">Assessment Platform</Link> 
      <Link to ="#home" className="active">
        Home
      </Link>
      <Link to ="#news">About</Link>
      <Link to ="/listcategory">Categories</Link>
      <Link to ="/login"><button onClick={handleLogout}>Logout</button></Link>
    </div>
  );
}
