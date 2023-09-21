import React from "react";
import Navbar from "../Navbar/Navbar";
import NotFound from "../NotFound";

export default function AdminDashboard() {
  const role = localStorage.getItem("userRole");
  return (
    <div>
      {role === "admin" ? (
        <>
          <Navbar />
          <p>Welcome to AdminDashboard : Logged in as {role}</p>
        </>
      ) : (
        <NotFound />
      )}
    </div>
  );
}
