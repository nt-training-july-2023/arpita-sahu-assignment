import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import ServiceURL from "../../Component/Service/ServiceURL";
import SweetAlertService from "../../Component/SweetAlert/SweetAlertService";
import InputComponent from "../../Component/InputComponent/InputComponent";

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
      navigate('/listcategory')
      localStorage.setItem("isLoggedIn", response.status);
      localStorage.setItem("userRole", response.data.Role);
      localStorage.setItem("name",response.data.Name);
      localStorage.setItem('selectedEmail', email);
    } catch (error) {
      if(error.response.status === 404){
        SweetAlertService.showNotificationAlert("Error!", error.response.data.message,"error");
      }
      if (error.response.status === 401) {
      setPasswordError("Invalid Password");
      SweetAlertService.showNotificationAlert("Error!","Wrong Password","error");
      }else if(error.response.status === 400){
      SweetAlertService.showNotificationAlert("Error!","Wrong Credentials","error");
    }
  }
  };

  return (
    <div className="loginReg">
      <div className="wrapper">
        <h2>Login</h2>
        <form onSubmit={handleLogin}>
          <InputComponent
          className="input-box"
          type="email"
          placeholder="Email"
          onChange={handleEmailChange}
          value={email}
          error={emailError}
          />
          <InputComponent
           className="input-box"
           type="password"
           placeholder="Password"
           onChange={handlePasswordChange}
           value={password}
           error={passwordError}
          />
          <InputComponent
          className="input-box button"
          type="Submit"
          value="Login Now"
          />
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
