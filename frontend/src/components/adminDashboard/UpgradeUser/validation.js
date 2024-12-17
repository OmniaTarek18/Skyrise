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

  return formik;
};
