import React from 'react'
import { Link } from 'react-router-dom'

export default function AdminDashboard() {
  return (
    <div>
        <p>Welcome to AdminDashboard</p>
        
        <Link to='/listCategory'> ListCategories</Link>
   </div>
  )
}
