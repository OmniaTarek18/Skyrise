import { useFormik } from "formik";
import { addFlightLegsSchema } from "../../../Validation";
export const useAddFlightLegsForm = (onSubmit) => {
  const formik = useFormik({
    initialValues: {
      arrivalAirport: "",
      departureAirport: "",
      arrivalTime: "",
      departureTime: "",
    },
    validationSchema: addFlightLegsSchema,
    onSubmit,
  });

  return formik;
};
