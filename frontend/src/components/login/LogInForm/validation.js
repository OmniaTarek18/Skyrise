// Importing necessary modules
import { useFormik } from "formik"; // Formik hook to handle form state and validation
import { loginSchema } from "../../../Validation"; // Importing the login validation schema

// Custom hook for managing the login form
export const useLoginForm = (onSubmit) => {
  // Using Formik to manage form state, validation, and submission
  const formik = useFormik({
    initialValues: {
      email: "", // Initial email value, empty at the start
      password: "", // Initial password value, empty at the start
    },
    validationSchema: loginSchema, // Apply the validation schema for email and password fields
    onSubmit, // Handle form submission
  });

  // Return the formik object to be used in the component
  return formik;
};
