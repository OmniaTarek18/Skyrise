import { useFormik } from "formik";
import { addFlightSchema } from "../../../Validation";
export const useAddFlightForm = (onSubmit) => {
  const formik = useFormik({
    initialValues: {
      arrivalDate: "",
      departureDate: "",
      economyPrice: "",
      businessPrice: "",
      availableBusinessSeats: "",
      availableEconomySeats: "",
    },
    validationSchema: addFlightSchema,
    onSubmit,
  });

  return formik;
};
