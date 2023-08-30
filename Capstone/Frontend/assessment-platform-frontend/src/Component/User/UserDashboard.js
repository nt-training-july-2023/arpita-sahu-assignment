import React from 'react'

export default function UserDashboard() {
        const handleLogout = () => {
          
          localStorage.removeItem('IsLoggedIn');
          localStorage.removeItem('userRole');
          
        };
      
        return (
          <div>
            <h1>User Dashboard</h1>
            <button onClick={handleLogout}>Logout</button>
          </div>
        );
      };
      

