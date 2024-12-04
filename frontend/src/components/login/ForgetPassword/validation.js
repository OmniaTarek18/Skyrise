import { useFormik } from "formik"; // Importing the useFormik hook to manage form state and validation
import { forgetPasswordSchema } from "../../../Validation"; // Importing the validation schema for the form (typically defined using Yup)
import axios from "axios"; // Importing axios for making HTTP requests

export const useForgotPasswordForm = () => {
  // Using Formik hook to manage form state and validation
  const formik = useFormik({
    // Initial form values for email input
    initialValues: {
      email: "", // The email field is initially empty
    },

    // Validation schema for the form, imported from Validation.js
    validationSchema: forgetPasswordSchema,

    // Form submission handler, triggered when the form is submitted
    onSubmit: async (values, actions) => {
      console.log(values); // Log form values to the console
      console.log(actions); // Log actions (such as resetForm) for debugging purposes

      try {
        // Make the POST request to reset the password using axios
        const response = await axios.post(
          `http://localhost:8080/password/reset?email=${values.email}`
        );

        // console.log(response.status)
        values.errors = false
        console.log(values.errors)
        // Reset the form after submission
        actions.resetForm();

      } catch (error) {
        values.errors = true
        console.error(
          "Error:",
          error.response ? error.response.data : error.message
        ); // Handle errors
        // Optionally, handle error (e.g., show an error message)
        actions.setStatus({
          error: error.response ? error.response.data : error.message,
        });
        actions.resetForm();
      }
    },
  });

  // Return the formik object so that it can be used in the ForgetPassword component
  return formik;
};
