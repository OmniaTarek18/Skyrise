// ResetPassword.js
import { React, useState, useEffect, useRef } from "react";
import Input from "../Input";
import Button from "../Button";
import Section from "../Section";
import { useChangePasswordForm, useForgetPasswordForm } from "./validation";
import "./style.css";
import { useNavigate } from "react-router";
import { changePasswordAPI, forgetPasswordAPI } from "./api";

const ResetPassword = ({ userEmail, isChangePassword = false }) => {
  const navigate = useNavigate();
  const [alert, setAlert] = useState(false);
  const isFirstRender = useRef(true);

  const forgetPasswordForm = useForgetPasswordForm(
    userEmail,
    forgetPasswordAPI
  );
  const changePasswordForm = useChangePasswordForm(changePasswordAPI);

  const formValues = isChangePassword ? changePasswordForm : forgetPasswordForm;

  const {
    values,
    errors,
    touched,
    isSubmitting,
    handleChange,
    handleBlur,
    handleSubmit,
    status,
  } = formValues;

  useEffect(() => {
    if (isFirstRender.current) {
      isFirstRender.current = false;
      return;
    }
    if (status === "success") {
      navigate("/");
    } else {
      setAlert(true);
      setTimeout(() => {
        setAlert(false);
      }, 2000);
    }
  }, [status, navigate]);

  return (
    <section className="reset-password-form">
      <Section heading={"Enter New Password"} />
      {alert && (
        <div className="alert alert-warning" role="alert">
          Password changed Successfully
        </div>
      )}
      <form onSubmit={handleSubmit}>
        <Input
          label={"New Password"}
          type={"password"}
          id={"newPassword"}
          placeholder={"Enter a new password"}
          onChange={handleChange}
          value={values.newPassword}
          onBlur={handleBlur}
          showError={errors.newPassword && touched.newPassword}
          errorMessage={errors.newPassword}
        />

        <Input
          label={"Confirm Password"}
          type={"password"}
          id={"confirmNewPassword"}
          placeholder={"Re-enter your new password"}
          onChange={handleChange}
          value={values.confirmNewPassword}
          onBlur={handleBlur}
          showError={errors.confirmNewPassword && touched.confirmNewPassword}
          errorMessage={errors.confirmNewPassword}
        />

        <Button
          btnText={"New Password"}
          btnColor="dark"
          disabled={isSubmitting}
          type="submit"
        />
      </form>
    </section>
  );
};

export default ResetPassword;
