import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import "./LoginReg.css";
import ServiceURL from "../Service/ServiceURL";
import SweetAlertService from "../SweetAlert/SweetAlertService";
export default function Signup() {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [firstNameError, setFirstNameError] = useState("");
  const [lastNameError, setLastNameError] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [confirmPasswordError, setConfirmPasswordError] = useState("");
  const [phoneNumberError, setPhoneNumberError] = useState("");

  const navigate = useNavigate();
  const redirect = () => {
    navigate("/Login");
  };

  const handleFirstnameChange = (e) => {
    setFirstName(e.target.value);
    if (firstNameError) {
      setFirstNameError("");
    }
  };

  const handleLastnameChange = (e) => {
    setLastName(e.target.value);
    if (lastNameError) {
      setLastNameError("");
    }
  };
  const handleEmailChange = (e) => {
    const newEmail = e.target.value;
    setEmail(newEmail);
    if (!newEmail) {
      setEmailError("Email is required");
    } else if (!newEmail.endsWith("@nucleusteq.com")) {
      setEmailError("Email must be in the form of @nucleusteq.com domain");
    } else {
      setEmailError("");
    }
  };
  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
    if (!password) {
      setPasswordError("Password is required ");
    } else if (password.length < 6) {
      setPasswordError("Password must be at least 6 characters");
    } else {
      setPasswordError("");
    }
  };
  const handleConfirmPasswordChange = (e) => {
    const newConfirmPassword = e.target.value;
    setConfirmPassword(newConfirmPassword);

    if (!newConfirmPassword) {
      setConfirmPasswordError("Confirm Password is required");
    } else if (password !== newConfirmPassword) {
      setConfirmPasswordError("Passwords do not match");
    } else {
      setConfirmPasswordError("");
    }
  };
  const handlephoneChange = (e) => {
    const numericInput = e.target.value.replace(/\D/g, "");
    setPhoneNumber(numericInput);
    if (numericInput.length < 10) {
      setPhoneNumberError("Phone number must be at least 10 digits");
    } else {
      setPhoneNumberError("");
    }
  };

  const validateForm = () => {
    let isValid = true;

    if (!firstName) {
      setFirstNameError("First Name is required");
      isValid = false;
    } else {
      setFirstNameError("");
    }

    if (!lastName) {
      setLastNameError("Last Name is required");
      isValid = false;
    } else {
      setLastNameError("");
    }

    if (!email) {
      setEmailError("Email is required");
      isValid = false;
    } else if (!email.endsWith("@nucleusteq.com")) {
      setEmailError("Email must be in the form of @nucleusteq.com domain");
      isValid = false;
    } else {
      setEmailError("");
    }

    if (!password) {
      setPasswordError("Password is required");
      isValid = false;
    } else if (password.length < 6) {
      setPasswordError("Password must be at least 6 characters");
      isValid = false;
    } else {
      setPasswordError("");
    }

    if (!confirmPassword) {
      setConfirmPasswordError("Confirm Password is required");
      isValid = false;
    } else if (password !== confirmPassword) {
      setConfirmPasswordError("Passwords do not match");
      isValid = false;
    } else {
      setConfirmPasswordError("");
    }

    if (!phoneNumber) {
      setPhoneNumberError("Phone Number is required");
      isValid = false;
    } else if (phoneNumber.length < 10) {
      setPhoneNumberError("Phone number must be at least 10 digits");
      isValid = false;
    } else {
      setPhoneNumberError("");
    }

    return isValid;
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();

    if (!validateForm()) {
      return;
    }

    try {
      const response = await ServiceURL.userRegistration({
        firstName,
        lastName,
        email,
        password,
        phoneNumber,
      });
      SweetAlertService.showNotificationAlert("Success","Registration Successful","success");
      navigate("/Login");
    } catch (error) {
      SweetAlertService.showNotificationAlert("Error!","Enter Valid Email in the format of [a-z][a-zA-Z0-9]","error")
    }
  };

  return (
    <div className="loginReg">
      <div className="wrapper">
        <h2>Registration</h2>
        <form onSubmit={handleFormSubmit}>
          <div className="input-box">
            <input
              type="text"
              placeholder=" First name"
              onChange={handleFirstnameChange}
              value={firstName}
            />
            {firstNameError && (
              <p className="error-message">{firstNameError}</p>
            )}
          </div>
          <div className="input-box">
            <input
              type="text"
              placeholder=" Last name"
              onChange={handleLastnameChange}
              value={lastName}
            />
            {lastNameError && <p className="error-message">{lastNameError}</p>}
          </div>
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
          <div className="input-box">
            <input
              type="password"
              placeholder="Confirm password"
              onChange={handleConfirmPasswordChange}
              value={confirmPassword}
            />
            {confirmPasswordError && (
              <p className="error-message">{confirmPasswordError}</p>
            )}
          </div>
          <div className="input-box">
            <input
              type="phone"
              placeholder="Phone Number"
              pattern="[0-9]*"
              onChange={handlephoneChange}
              value={phoneNumber}
            />
            {phoneNumberError && (
              <p className="error-message">{phoneNumberError}</p>
            )}
          </div>
          <div className="input-box button">
            <input type="Submit" value="Register Now" />
          </div>
          <div className="text">
            <h3 onClick={redirect}>
              Already have an account? <Link to="/Login">Login now</Link>
            </h3>
          </div>
        </form>
      </div>
    </div>
  );
}
