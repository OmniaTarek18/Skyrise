// Import necessary hooks and validation schema
import { useFormik } from "formik";
import { resetPasswordSchema } from "../../../Validation";
import axios from "axios";
// Custom hook for handling the reset password form logic
export const useResetPasswordForm = (userEmail, onSubmit) => {
  // Initialize Formik with form values, validation schema, and submission handler
  const formik = useFormik({
    initialValues: {
      // Pre-fill the email field with the userEmail prop
      email: userEmail, // The user's email passed from the parent component
      // Initialize password and confirm password fields as empty
      newPassword: "",
      confirmNewPassword: "",
    },
    // Define validation schema for the form (to be imported from Validation)
    validationSchema: resetPasswordSchema,
    // Bind the onSubmit function for handling form submissions
    onSubmit,
  });

  // Return Formik's form state, handlers, and values to be used in the component
  return formik;
};
