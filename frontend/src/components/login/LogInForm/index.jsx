// Import necessary components and hooks
import React from "react"; // React library to define the component
import Button from "../../shared/Button"; // Button component for rendering buttons
import Input from "../../shared/Input"; // Input component for rendering input fields
import CallToAction from "../../shared/CallToAction"; // CallToAction component for rendering a call-to-action link
import { useLoginForm } from "./validation"; // Custom hook to handle form validation
import "./style.css"; // Importing the associated CSS for styling

// LogInForm component definition
const LogInForm = ({ navigateTo }) => {
  // onSubmit handler for the form submission
  const onSubmit = async (values, actions) => {
    // Log form values and actions to the console for debugging
    console.log(values);
    console.log(actions);

    // Simulate an asynchronous operation (e.g., network request) with a delay
    await new Promise((resolve) => setTimeout(resolve, 1000));

    // Reset the form after submission
    actions.resetForm();
  };

  // Using the useLoginForm hook to get form values, handlers, and validation states
  const {
    values,
    errors,
    touched,
    isSubmitting,
    handleChange,
    handleBlur,
    handleSubmit,
  } = useLoginForm(); // Use the custom hook for form management

  return (
    // Form element for user login
    <form onSubmit={handleSubmit}>
      {/* Email input field */}
      <Input
        label={"Email"} // Label for the email field
        type={"email"} // Type of input (email)
        id={"email"} // ID for the input field
        placeholder={"Enter your email"} // Placeholder text for the field
        onChange={handleChange} // Handle change event to update form values
        value={values.email} // Bind the email value to the form state
        onBlur={handleBlur} // Handle blur event to mark the field as touched
        showError={errors.email && touched.email} // Show error if field is touched and there is an error
        errorMessage={errors.email} // Display the error message for the email field
      ></Input>

      {/* Password input field */}
      <Input
        label={"Password"} // Label for the password field
        type={"password"} // Type of input (password)
        id={"password"} // ID for the password input field
        placeholder={"Enter your password"} // Placeholder text for the password field
        onChange={handleChange} // Handle change event to update form values
        value={values.password} // Bind the password value to the form state
        onBlur={handleBlur} // Handle blur event to mark the field as touched
        showError={errors.password && touched.password} // Show error if field is touched and there is an error
        errorMessage={errors.password} // Display the error message for the password field
      ></Input>

      {/* CallToAction for navigating to "Forget Password" page */}
      <CallToAction
        action={"Forget Password"} // Action text for the CallToAction component
        handleClick={() => navigateTo("forgetPasswordForm")} // Function to navigate to the "forgetPasswordForm" page
      />

      {/* Submit Button for signing in */}
      <Button
        btnText={"Sign in"} // Text displayed on the button
        btnColor="dark" // Button color
        handleClick={() => console.log("clicked")} // Handle button click (optional, can be omitted if no specific action is needed)
        type="submit" // Submit the form when clicked
        disabled={isSubmitting} // Disable the button if the form is submitting
      ></Button>
    </form>
  );
};

export default LogInForm;
