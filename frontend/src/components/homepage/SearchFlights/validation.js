import { useFormik } from "formik";
import { searchFlightsSchema } from "../../../Validation";
export const useSearchFlightsForm = (onSubmit) => {
  const formik = useFormik({
    initialValues: {
      departureAirportId: "",
      arrivalAirportId: "",
      arrivalDate: "",
      departureDate: "",
      numberOfTickets: "",
      seatClass: "ECONOMY",
      tripType: "round-trip",
    },

    validationSchema: searchFlightsSchema,

    onSubmit,
  });

  return formik;
};
