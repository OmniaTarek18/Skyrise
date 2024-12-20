import React, { useState, useEffect, useRef } from "react";
import Input from "../../shared/Input";
import Button from "../../shared/Button";
import Section from "../../shared/Section";
import ResetPassword from "../../shared/ResetPassword";
import { useForgotPasswordForm } from "./validation";
import { forgetPasswordAPI } from "./api";
import useUserAuthenticationStore from "../../../store/useUserAuthenticationStore";
import "./style.css";

const ForgetPassword = () => {
  const [page, setPage] = useState("forgetPassword");
  const [alert, setAlert] = useState(false);
  const isFirstRender = useRef(true);
  const { setUserAuthentication } = useUserAuthenticationStore();
  const {
    values,
    errors,
    touched,
    isSubmitting,
    status,
    handleChange,
    handleBlur,
    handleSubmit, // Formik's handleSubmit function
  } = useForgotPasswordForm(forgetPasswordAPI);

  useEffect(() => {
    if (isFirstRender.current) {
      isFirstRender.current = false; // Skip effect on the first render
      return;
    }
    if (status.status == "success") {
      setUserAuthentication(status.data.id, status.data.role);
      setPage("continue");
    } else {
      setAlert(true);
      setTimeout(() => {
        setAlert(false);
      }, 2000); // 2000 milliseconds = 2 seconds
    }
  }, [status]);

  return (
    <section className="forget-password-form">
      {alert && (
        <div className="alert alert-warning" role="alert">
          Email doesn't exist
        </div>
      )}
      {page === "forgetPassword" && <Section heading={"Find Your Account"} />}
      {page === "forgetPassword" && (
        <form className="forget-password-form" onSubmit={handleSubmit}>
          <Input
            label={"Email"}
            type={"email"}
            id={"email"}
            placeholder={"Enter your email"}
            onChange={handleChange}
            value={values.email}
            onBlur={handleBlur}
            showError={errors.email && touched.email}
            errorMessage={errors.email}
          />
          <Button
            btnText={"Continue"}
            btnColor="dark"
            type="submit" // Ensures the button triggers the form submission
            disabled={isSubmitting}
          />
        </form>
      )}
      {page === "continue" && <ResetPassword userEmail={values.email} />}
    </section>
  );
};

export default ForgetPassword;
