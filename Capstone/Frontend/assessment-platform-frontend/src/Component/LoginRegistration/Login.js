import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import ServiceURL from "../Service/ServiceURL";
import SweetAlertService from "../SweetAlert/SweetAlertService";

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
      const response = await ServiceURL.userLogin({
        email,
        password,
      });
      SweetAlertService.showNotificationAlert("Success","Login Successful","success")
      // if (response.data.Role === "admin") navigate("/adminDashboard");
      // if (response.data.Role === "user") navigate("/userDashboard");
      navigate('/listcategory')
      localStorage.setItem("isLoggedIn", response.status);
      localStorage.setItem("userRole", response.data.Role);
      localStorage.setItem("name",response.data.Name);
      localStorage.setItem('selectedEmail', email);
    } catch (error) {
      if(error.response.status >=400 && error.response.status<=404){
        SweetAlertService.showNotificationAlert("Error!", "Wrong Credentials","error");
      }
      // if (error.response.status === 404 || error.response.status === 401) {
      //   setPasswordError("Invalid Password");
      //   SweetAlertService.showNotificationAlert("Error!","Wrong Password","error");
      // }else if(error.response.status === 400){
      //  SweetAlertService.showNotificationAlert("Error!","Wrong Email Format","error");
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
