import { useFormik } from "formik"; // Importing the useFormik hook to manage form state and validation
import { forgetPasswordSchema } from "../../../Validation"; // Importing the validation schema for the form (typically defined using Yup)

export const useForgotPasswordForm = (onSubmit) => {
  // Using Formik hook to manage form state and validation
  const formik = useFormik({
    // Initial form values for email input
    initialValues: {
      email: "", // The email field is initially empty
    },

    // Validation schema for the form, imported from Validation.js
    validationSchema: forgetPasswordSchema,

    // Form submission handler, triggered when the form is submitted
    onSubmit,
  });

  // Return the formik object so that it can be used in the ForgetPassword component
  return formik;
};
