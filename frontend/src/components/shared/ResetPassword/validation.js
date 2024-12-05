// Import necessary hooks and validation schema
import { useFormik } from "formik";
import { resetPasswordSchema } from "../../../Validation";

// Custom hook for handling the reset password form logic
export const useResetPasswordForm = (userEmail) => {

  // onSubmit function that handles form submission logic
  const onSubmit = async (values, actions) => {
    // Log the form values and actions for debugging purposes
    console.log(values);
    console.log(actions);

    // Simulate an asynchronous operation, e.g., API call to reset the password
    await new Promise((resolve) => setTimeout(resolve, 1000));

    // Reset the form after the operation is complete (e.g., after a successful password reset)
    actions.resetForm();
  };

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
