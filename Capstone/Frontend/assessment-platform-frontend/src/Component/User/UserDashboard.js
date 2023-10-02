import React from "react";
import Navbar from "../Navbar/Navbar";
import NotFound from "../NotFound";

export default function UserDashboard() {
  const role = localStorage.getItem("userRole");
  return (
    <div>
      {role === "user" ? (
        <>
          <Navbar />
          <p>Welcome to UserDashboard : Logged in as {role}</p>
        </>
      ) : (
        <NotFound />
      )}
    </div>
  );
}
