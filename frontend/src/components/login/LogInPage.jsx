import React, { useState } from "react";
import loginImg from "../../assets/loginImg.jpeg";
import LogInForm from "./LogInForm";
import GoogleLogIn from "./GoogleLogIn";
import Section from "../shared/Section";
import CallToAction from "../shared/CallToAction";
import ResetPassword from "../shared/ResetPassword";
import ForgetPassword from "./ForgetPassword";
import ChangePassword from "../userdashboard/ChangePassword";
import { useNavigate } from "react-router-dom";
import Button from "../shared/Button";
import "./style.css";

const LogInPage = () => {
  const navigate = useNavigate();
  const [ page, setPage ] = useState("loginForm");
  return (
    <main className="login-page">
      <section className="login-container">
        {page === "loginForm" && (
          <div className="login-form-container">
            <header>
              {/* <Logo /> */}
              <Section
                heading={"Welcome back"}
                headingText={"Welcome back! Please enter your details."}
              />
            </header>
            <LogInForm navigateTo={setPage}/>
            <GoogleLogIn />
            <CallToAction
              action={"Sign up"}
              actionText={"Don't have an account?"}
              handleClick={() => navigate("/signup")}
            />
          </div>
        )}
        {page === "forgetPasswordForm" && (
          <div className="login-form-container">
            <ForgetPassword />
            {/* <ChangePassword/> */}
            <Button
              btnText={"Cancel"}
              btnColor="light"
              handleClick={() => setPage("loginForm")}
            />
          </div>
        )}

        <div className="login-image-container">
          <img
            className="loginImg"
            src={loginImg}
            alt="Aerial view of an airplane flying above a shipping port with rows of colorful containers along a waterway, highlighting global logistics and transportation."
          />
        </div>
      </section>
    </main>
  );
};

export default LogInPage;
