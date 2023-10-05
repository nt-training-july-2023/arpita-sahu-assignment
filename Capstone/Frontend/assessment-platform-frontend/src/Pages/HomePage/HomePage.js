import React from 'react';
import { FaArrowRight } from 'react-icons/fa';
import "./HomePage.css";
import { useNavigate } from 'react-router-dom';

export default function HomePage() {
  const navigate=useNavigate();
  return (
    <div
    className='Homepage'
    >
      <h1>Assessment Portal</h1>
      <button className='get-Started'onClick={()=>navigate('/Login')}>
      Get Started
      <span className='arrow-icon'>
        <FaArrowRight />
      </span>
    </button>
    </div>
  )
}
