import React, { useState, useEffect, useRef } from "react";
import Input from "../../../components/shared/Input";
import Button from "../../../components/shared/Button";
import { useUpgradeUserForm } from "./validation";
import { UpgradeUserAPI } from "./api";
import "./style.css";

const UpgradeUser = () => {
  const [alert, setAlert] = useState(false);
  const isFirstRender = useRef(true);
  const {
    values,
    errors,
    touched,
    isSubmitting,
    status,
    handleChange,
    handleBlur,
    handleSubmit, // Formik's handleSubmit function
  } = useUpgradeUserForm(UpgradeUserAPI);

  useEffect(() => {
    if (isFirstRender.current) {
      isFirstRender.current = false; // Skip effect on the first render
      return;
    }
    if (status.status == "success");
    else {
      setAlert(true);
      setTimeout(() => {
        setAlert(false);
      }, 2000); // 2000 milliseconds = 2 seconds
    }
  }, [status]);

  return (
    <section className="upgrade-user-form">
      {alert && (
        <div className="alert alert-warning" role="alert">
          Email doesn't exist!
        </div>
      )}
      <h1>Upgrade User to Admin</h1>
      <form onSubmit={handleSubmit}>
        <Input
          label={"Email"}
          type={"email"}
          id={"email"}
          placeholder={"Enter email of user to upgrade"}
          onChange={handleChange}
          value={values.email}
          onBlur={handleBlur}
          showError={errors.email && touched.email}
          errorMessage={errors.email}
        />
        <Button
          btnText={"Continue"}
          btnColor="pink"
          type="submit" // Ensures the button triggers the form submission
          disabled={isSubmitting}
        />
      </form>
    </section>
  );
};

export default UpgradeUser;
