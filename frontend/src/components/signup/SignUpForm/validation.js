import { useFormik } from "formik"; // Importing useFormik hook from Formik for form handling
import { signUpSchema } from "../../../Validation"; // Importing the sign-up validation schema

// List of countries for the nationality dropdown
export const countries = [
  "United States", "Canada", "United Kingdom", "Australia", "India", "Germany",
  "France", "Italy", "Brazil", "Mexico", "South Korea", "Japan", "China", "Russia",
  "South Africa", "Argentina", "Spain", "Netherlands", "Sweden", "Norway", "Finland",
  "Denmark", "Switzerland", "Belgium", "Turkey", "Saudi Arabia", "United Arab Emirates",
  "Singapore", "Malaysia", "Indonesia", "Thailand", "Vietnam", "Philippines", "Egypt",
<<<<<<< HEAD
  "Nigeria", "Kenya", "Chile", "Colombia", "Peru", "Pakistan", "Bangladesh",
=======
  "Nigeria", "Kenya", "Chile", "Colombia", "Peru", "Pakistan", "Bangladesh", "Israel",
>>>>>>> 78722e824a6e9aefc3894f69d525f449583e134b
  "Greece", "Portugal", "New Zealand", "Ireland", "Czech Republic", "Romania", "Ukraine",
  "Poland", "Hungary", "Croatia", "Slovenia", "Serbia",
];

// List of country codes for the phone number dropdown
export const countryCodes = [
  "United States (+1)", "Canada (+1)", "United Kingdom (+44)", "Australia (+61)",
  "India (+91)", "Germany (+49)", "France (+33)", "Italy (+39)", "Brazil (+55)",
  "Mexico (+52)", "South Korea (+82)", "Japan (+81)", "China (+86)", "Russia (+7)",
  "South Africa (+27)", "Argentina (+54)", "Spain (+34)", "Netherlands (+31)", "Sweden (+46)",
  "Norway (+47)", "Finland (+358)", "Denmark (+45)", "Switzerland (+41)", "Belgium (+32)",
  "Poland (+48)", "Turkey (+90)", "Saudi Arabia (+966)", "United Arab Emirates (+971)",
  "Singapore (+65)", "Malaysia (+60)", "Indonesia (+62)", "Thailand (+66)", "Vietnam (+84)",
  "Philippines (+63)", "Egypt (+20)", "Nigeria (+234)", "Kenya (+254)", "Chile (+56)",
<<<<<<< HEAD
  "Colombia (+57)", "Peru (+51)", "Pakistan (+92)", "Bangladesh (+880)",
=======
  "Colombia (+57)", "Peru (+51)", "Pakistan (+92)", "Bangladesh (+880)", "Israel (+972)",
>>>>>>> 78722e824a6e9aefc3894f69d525f449583e134b
  "Greece (+30)", "Portugal (+351)", "New Zealand (+64)", "Ireland (+353)", "Czech Republic (+420)",
  "Romania (+40)", "Ukraine (+380)", "Hungary (+36)", "Croatia (+385)", "Slovenia (+386)", "Serbia (+381)",
];

// List of gender options for the gender dropdown
export const gender = ["male", "female"];

// Custom hook to handle the sign-up form logic using Formik
export const useSignUpForm = () => {
  // onSubmit function to handle form submission
  const onSubmit = async (values, actions) => {
    // Logging form values and actions to the console for debugging
    console.log(values);
    console.log(actions);

    // Simulating a delay (e.g., for API call) using a promise and setTimeout
    await new Promise((resolve) => setTimeout(resolve, 1000));

    // Resetting the form after submission
    actions.resetForm();
  };

  // Initializing Formik with necessary configurations
  const formik = useFormik({
    initialValues: {
      // Initial values for the form fields
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      confirmPassword: "",
      nationality: "",
      nationalId: "",
      countryCode: "",
      phoneNumber: "",
      issuingCountry: "",
      passportNumber: "",
      dob: "",
      gender: "",
    },
    validationSchema: signUpSchema, // Using the sign-up validation schema to validate form inputs
    onSubmit, // Binding the onSubmit function to handle form submission
  });

  // Returning the formik object to be used in the component
  return formik;
};
