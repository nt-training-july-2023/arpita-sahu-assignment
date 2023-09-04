import React from "react";
import { Link } from "react-router-dom";
import '../Navbar/Navbar.css';
export default function Navbar() {
  return (
    <div class="navbar">
        <ul>
            <li><Link to ="#">Home</Link></li>
            <li><Link to ="#">About</Link></li>
            <li><Link to ="#">Services</Link></li>
            <li><Link to ="#">Portfolio</Link></li>
            <li><Link to ="#">Contact</Link></li>
        </ul>
    </div>
  );
}
