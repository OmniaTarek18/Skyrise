// Import necessary hooks and validation schema
import { useFormik } from "formik";
import { resetPasswordSchema } from "../../../Validation";
import useUserAuthenticationStore from "../../../store/useUserAuthenticationStore";

export const useForgetPasswordForm = (userEmail, onSubmit) => {
  const formik = useFormik({
    initialValues: {
      email: userEmail,
      newPassword: "",
      confirmNewPassword: "",
    },
    validationSchema: resetPasswordSchema,
    onSubmit,
  });

  return formik;
};


export const useChangePasswordForm = (onSubmit) => {
  const { id } = useUserAuthenticationStore();
  const formik = useFormik({
    initialValues: {
      id: id,
      newPassword: "",
      confirmNewPassword: "",
    },
    validationSchema: resetPasswordSchema,
    onSubmit,
  });

  return formik;
};

