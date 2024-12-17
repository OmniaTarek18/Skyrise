import { useFormik } from "formik";
import { changePasswordSchema } from "../../../Validation";
import useUserAuthenticationStore from "../../../store/useUserAuthenticationStore";

export const useChangePasswordForm = (onSubmit) => {
  const { id } = useUserAuthenticationStore();
  const formik = useFormik({
    initialValues: {
      id:id,
      password: "",
    },
    validationSchema: changePasswordSchema,
    onSubmit,
  });

  return formik;
};
