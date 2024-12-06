// ResetPassword.js
import { React, useState, useEffect, useRef } from "react"; // Importing React library for JSX syntax
import Input from "../Input"; // Importing the custom Input component for rendering input fields
import Button from "../Button"; // Importing the custom Button component for rendering buttons
import Section from "../Section"; // Importing the custom Section component for rendering section headings
import { useResetPasswordForm } from "./validation"; // Importing the custom hook to handle form logic and validation
import "./style.css"; // Importing the stylesheet for the ResetPassword component
import { useNavigate } from "react-router";
import { changePasswordAPI } from "../../login/ForgetPassword/api";

const ResetPassword = ({ userEmail }) => {
  const navigate = useNavigate();
  const [alert, setAlert] = useState(false);
  const isFirstRender = useRef(true);
  // Destructuring the values and methods returned from the useResetPasswordForm custom hook

  const {
    values,
    errors,
    touched,
    isSubmitting,
    handleChange,
    handleBlur,
    handleSubmit,
    status,
  } = useResetPasswordForm(userEmail, changePasswordAPI); // Passing the user's email to the hook

  useEffect(() => {
    if (isFirstRender.current) {
      isFirstRender.current = false; // Skip effect on the first render
      return;
    }
    if (status == "success") {
      navigate("/home");
    } else {
      setAlert(true);
      setTimeout(() => {
        setAlert(false);
      }, 2000); // 2000 milliseconds = 2 seconds
    }
  }, [status]);

  return (
    <section className="reset-password-form">
      {/* Section for the heading */}
      {alert && (
        <div className="alert alert-warning" role="alert">
          Password changed Successfully
        </div>
      )}
      <Section heading={"Enter New Password"} />

      {/* Form for resetting the password */}
      <form onSubmit={handleSubmit}>
        {/* Input field for the new password */}
        <Input
          label={"New Password"} // Label for the input field
          type={"password"} // Input type is password, so the text is hidden
          id={"newPassword"} // Unique identifier for the input field
          placeholder={"Enter a new password"} // Placeholder text to guide the user
          onChange={handleChange} // Event handler for updating the input value
          value={values.newPassword} // The value of the input field is bound to the formik values
          onBlur={handleBlur} // Event handler for blur (losing focus)
          showError={errors.newPassword && touched.newPassword} // Show error if the field is touched and contains an error
          errorMessage={errors.newPassword} // Display the error message for the new password field
        />

        {/* Input field for confirming the new password */}
        <Input
          label={"Confirm Password"} // Label for the input field
          type={"password"} // Input type is password, so the text is hidden
          id={"confirmNewPassword"} // Unique identifier for the input field
          placeholder={"Re-enter your new password"} // Placeholder text to guide the user
          onChange={handleChange} // Event handler for updating the input value
          value={values.confirmNewPassword} // The value of the input field is bound to the formik values
          onBlur={handleBlur} // Event handler for blur (losing focus)
          showError={errors.confirmNewPassword && touched.confirmNewPassword} // Show error if the field is touched and contains an error
          errorMessage={errors.confirmNewPassword} // Display the error message for the confirm password field
        />

        {/* Submit button for resetting the password */}
        <Button
          btnText={"New Password"} // Text to display on the button
          btnColor="dark" // Button color style
          disabled={isSubmitting} // Disable the button when the form is submitting
          type="submit" // Set the button type to submit, triggering the form submission
        />
      </form>
    </section>
  );
};

export default ResetPassword;
