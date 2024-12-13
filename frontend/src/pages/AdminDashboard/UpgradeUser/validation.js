import { useFormik } from "formik";
import { upgradeUserSchema } from "../../../Validation";

export const useUpgradeUserForm = (onSubmit) => {
  const formik = useFormik({
    initialValues: {
      email: "",
    },
    validationSchema: upgradeUserSchema,
    onSubmit,
  });

  // Return the formik object so that it can be used in the ForgetPassword component
  return formik;
};
