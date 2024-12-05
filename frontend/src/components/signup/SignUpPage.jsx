import React from "react";
import CallToAction from "../shared/CallToAction";
import Logo from "../shared/Logo";
import SignUpForm from "./SignUpForm";
import { useNavigate } from "react-router-dom";
import "./style.css";

const SignUpPage = () => {
  const navigate = useNavigate();
  return (
    <main className="signup-container">
      <header className="Logo">{/* <Logo /> */}</header>
      <section className="signup-form-container">
        <h1>Create an Account</h1>
        <div className="cta-container">
          <CallToAction
            action={"Sign in"}
            actionText={"Already have an account?"}
            handleClick={() => navigate("/login")}
          />
        </div>
        <SignUpForm />
      </section>
    </main>
  );
};

export default SignUpPage;
