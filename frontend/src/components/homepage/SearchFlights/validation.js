import { useFormik } from "formik";
import { searchFlightsSchema } from "../../../Validation";
export const useSearchFlightsForm = (onSubmit) => {
  const formik = useFormik({
    initialValues: {
      source: "",
      destination: "",
      arrivalDate: "",
      departureDate: "",
      passengers: "",
      class: "economy",
      tripType: "round-trip",
    },
    validationSchema: searchFlightsSchema,

    onSubmit,
  });

  return formik;
};
