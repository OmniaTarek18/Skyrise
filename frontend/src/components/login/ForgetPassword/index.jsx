<<<<<<< HEAD
// validate user's email, then call change password
import React from "react";

const index = () => {
  return <div>index</div>;
};

export default index;
=======
import React, { useState, useEffect } from "react";
import Input from "../../shared/Input";
import Button from "../../shared/Button";
import Section from "../../shared/Section";
import ResetPassword from "../../shared/ResetPassword";
import { useForgotPasswordForm } from "./validation";
import "./style.css";

const ForgetPassword = () => {
  const [page, setPage] = useState("forgetPassword");
  const [alert, setAlert] = useState(false);
  const {
    values,
    errors,
    touched,
    isSubmitting,
    handleChange,
    handleBlur,
    handleSubmit, // Formik's handleSubmit function
    status,
  } = useForgotPasswordForm();

  // Form submission handling
  const handleFormSubmit = (e) => {
    e.preventDefault(); // Prevents the default form submission behavior
    if (!touched.email || errors.email) {
      return; // If validation fails, don't proceed with the form submission
    }
    handleSubmit();
  };

  useEffect(() => {
    if (status == "success") {
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
        <form className="forget-password-form" onSubmit={handleFormSubmit}>
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
      {page === "continue" && (
        <ResetPassword userEmail={values.email} />
      )}
    </section>
  );
};

export default ForgetPassword;
>>>>>>> SCRUM-65-Reset-Password
