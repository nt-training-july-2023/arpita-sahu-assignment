import React from 'react'
import { Link } from 'react-router-dom'
import Navbar from '../Navbar/Navbar'

export default function AdminDashboard() {
  return (
    <div>
      <Navbar/>
        <p>Welcome to AdminDashboard</p>
        
        <Link to='/listCategory'> ListCategories</Link>
   </div>
  )
}

