import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import axios from "axios";
import Swal from "sweetalert2";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const redirect = () => {
    navigate("/Signup");
  };

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
    if (emailError) {
      setEmailError("");
    }
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
    if (passwordError) {
      setPasswordError("");
    }
  };

  const validateForm = () => {
    let isValid = true;

    if (!email) {
      setEmailError("Email is required");
      isValid = false;
    } else setEmailError("");

    if (!password) {
      setPasswordError("Password is required");
      isValid = false;
    } else setPasswordError("");

    return isValid;
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    if (!validateForm()) {
      return;
    }

    try {
      const response = await axios.post("http://localhost:8080/api/login", {
        email,
        password,
      });
      Swal.fire({
        title: "Success",
        text: "Login Successful",
        icon: "success"
      });
      if (response.data.Role === "admin") navigate("/adminDashboard");
      if (response.data.Role === "user") navigate("/userDashboard");
      console.log("Login successfully!", response.data);
      localStorage.setItem("isLoggedIn", response.status);
      localStorage.setItem("userRole", response.data.Role);
    } catch (error) {
      if (error.response.data.status === 409) {
        setPasswordError("Wrong Credentials");
        Swal.fire({
          icon: 'error',
          title: 'Error!',
          text: 'Wrong Credentials',
        });
      }
      console.error("Login failed:", error);
    }
  };

  return (
    <div className="loginReg">
      <div className="wrapper">
        <h2>Login</h2>
        <form onSubmit={handleLogin}>
          <div className="input-box">
            <input
              type="email"
              placeholder="Email"
              onChange={handleEmailChange}
              value={email}
            />
            {emailError && <p className="error-message">{emailError}</p>}
          </div>
          <div className="input-box">
            <input
              type="password"
              placeholder="Password"
              onChange={handlePasswordChange}
              value={password}
            />
            {passwordError && <p className="error-message">{passwordError}</p>}
          </div>
          <div className="input-box button">
            <input type="Submit" value="Login Now" />
          </div>
          <div className="text">
            <h3 onClick={redirect}>
              Don't have an account? <Link>Register now</Link>
            </h3>
          </div>
        </form>
      </div>
    </div>
  );
}
