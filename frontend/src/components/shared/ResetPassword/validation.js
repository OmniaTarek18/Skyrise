// Import necessary hooks and validation schema
import { useFormik } from "formik";
import { resetPasswordSchema } from "../../../Validation";
<<<<<<< HEAD

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

=======
import axios from "axios";
// Custom hook for handling the reset password form logic
export const useResetPasswordForm = (userEmail) => {
>>>>>>> SCRUM-65-Reset-Password
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
<<<<<<< HEAD
    onSubmit,
=======
    onSubmit: async (values, actions) => {
      console.log(values); // Log form values to the console
      // console.log(actions); // Log actions (such as resetForm) for debugging purposes

      try {
        console.log(userEmail)
        // Make the POST request to reset the password using axios
        const response = await axios.post(
          `http://localhost:8080/password/change?email=${userEmail}&newPassword=${values.newPassword}`
        );

        // actions.resetForm();
        actions.setStatus("success");
        // Reset the form after submission
        // actions.resetForm();
      } catch (error) {
        console.error(
          "Error:",
          error.response ? error.response.data : error.message
        ); // Handle errors
        // Optionally, handle error (e.g., show an error message)
        actions.setStatus({
          error: error.response ? error.response.data : error.message,
        });
      }
    },
>>>>>>> SCRUM-65-Reset-Password
  });

  // Return Formik's form state, handlers, and values to be used in the component
  return formik;
};
