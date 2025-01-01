import { useFormik } from "formik";
import { ticketReservationSchema } from "../../Validation";

export const gender = ["MALE", "FEMALE"];

export const specialNeeds = [
  "VISUAL_IMPAIRMENT",
  "HEARING_IMPAIRMENT",
  "MOBILITY_IMPAIRMENT",
];
export const mealSpecification = [
  "VEGEN",
  "VEGETARIAN",
  "GLUTIN_FREE",
  "DIABETIC",
  "DAIRY_FREE",
];

export const useTicketReservationForm = (onSubmit) => {
  const formik = useFormik({
      initialValues: {
      firstName: "",
      lastName: "",
      nationalId: "",
      countryCode: "",
      phoneNumber: "",
      passportIssuingCountry: "",
      passportNumber: "",
      dateOfBirth: "",
      gender: "",
      specialNeeds: "",
      mealSpecification: "",
    },
    validationSchema: ticketReservationSchema,
    onSubmit,
  });

  return formik;
};
