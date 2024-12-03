// Importing necessary modules
import { useFormik } from "formik"; // Formik hook to handle form state and validation
import { loginSchema } from "../../../Validation"; // Importing the login validation schema

// Custom hook for managing the login form
export const useLoginForm = () => {
  // onSubmit function to handle form submission
  const onSubmit = async (values, actions) => {
    // Log the form values and actions for debugging purposes
    console.log(values);
    console.log(actions);

    // Simulate a delay (e.g., network request)
    await new Promise((resolve) => setTimeout(resolve, 1000));

    // Reset the form after successful submission
    actions.resetForm();
  };

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
