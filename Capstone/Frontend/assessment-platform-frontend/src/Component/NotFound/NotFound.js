import React from "react";
import './NotFound.css'
import { FaArrowLeft } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
export default function NotFound() {
  const navigate=useNavigate();
  return <div className="notfound">
    <p>404, Page Not Found</p>
    <button className='backbutton'onClick={()=>navigate('/')}>
      <span className='arrow-icon'>
        <FaArrowLeft />
      </span>
      Go Back
      </button>
    </div>;
}
