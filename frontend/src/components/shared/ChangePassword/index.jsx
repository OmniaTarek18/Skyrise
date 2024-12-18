import { React, useState, useEffect, useRef } from "react";
import Input from "../../shared/Input";
import Button from "../../shared/Button";
import Section from "../../shared/Section";
import ResetPassword from "../../shared/ResetPassword";
import { useChangePasswordForm } from "./validation";
import "./style.css";
import { changePasswordAPI } from "./api";

const ChangePassword = () => {
  const [page, setPage] = useState("changePassword");
  const [alert, setAlert] = useState(false);
  const isFirstRender = useRef(true);

  const {
    values,
    errors,
    touched,
    isSubmitting,
    status,
    setStatus,
    handleChange,
    handleBlur,
    handleSubmit,
  } = useChangePasswordForm(changePasswordAPI);

  useEffect(() => {
    console.log(status);
    if (isFirstRender.current) {
      isFirstRender.current = false;
      return;
    }
    if (status === "success") {
      setPage("continue");
    } else {
      setAlert(true);
      setTimeout(() => {
        setAlert(false);
      }, 2000);
    }
  }, [status]);

  return (
    <section className="change-password-form">
      {alert && (
        <div className="alert alert-warning" role="alert">
          Incorrect Password!
        </div>
      )}
      {page === "changePassword" && (
        <form className="change-password-form" onSubmit={handleSubmit}>
          <div>
            <Section heading={"Change Password"} />
            <Input
              label={"Old Password"}
              type={"password"}
              id={"password"}
              placeholder={"Enter password to change"}
              onChange={handleChange}
              value={values.password}
              onBlur={handleBlur}
              showError={errors.password && touched.password}
              errorMessage={errors.password}
            />

            <Button
              btnText={"Continue"}
              btnColor="dark"
              type="submit"
              disabled={isSubmitting}
            />
          </div>
        </form>
      )}

      {page === "continue" && <ResetPassword isChangePassword={true} />}
    </section>
  );
};

export default ChangePassword;
