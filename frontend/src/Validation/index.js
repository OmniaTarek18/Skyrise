import * as yup from "yup";

var passwordRegex =
  /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;

var nameRegax = /^[a-z ,.'-]+$/i;

// Phone number validation regex (for example: US format: (123) 456-7890)
const phoneNumberRegex = /^\d{11}$/;

// National ID validation (assuming a generic length and format, e.g., 12 digits)
const nationalIdRegex = /^\d{14}$/;

// Passport Number validation (assuming alphanumeric with 9 characters, example format: ABC123456)
const passportNumberRegex = /^[A-Z0-9]{9}$/;

const phoneNumberMessage = "Invalid phone number format";
const nationalIdMessage = "Invalid National ID format";
const passportNumberMessage = "Invalid Passport Number format";
const nameMessage = "Please enter a valid name";
const emailErrorMessage = "Please enter a valid email";
const requiredMessage = "Required";
const passwordMessage = "Please create a stronger password";
const confirmPasswordMessage = "Passwords must match";

const emailValidation = yup
  .string()
  .email(emailErrorMessage)
  .required(requiredMessage);
const passwordValidation = yup
  .string()
  .min(8)
  .matches(passwordRegex, { message: passwordMessage })
  .required(requiredMessage);
const ageValidation = yup
  .number()
  .positive()
  .integer()
  .required(requiredMessage);
const confirmPasswordValidation = (reference) =>
  yup
    .string()
    .oneOf([yup.ref(reference), null], confirmPasswordMessage)
    .required(requiredMessage);

const nameValidation = yup
  .string()
  .matches(nameRegax, { message: nameMessage })
  .required(requiredMessage);

const nationalityValidation = yup.string().required(requiredMessage);
const genderValidation = yup.string().required(requiredMessage);
const nationalIdValidation = yup
  .string()
  .matches(nationalIdRegex, nationalIdMessage)
  .min(14)
  .required(requiredMessage);
const dobValidation = yup.string().required(requiredMessage);
const countryCodeValidation = yup.string().required(requiredMessage);
const phoneNumberValidation = yup
  .string()
  .matches(phoneNumberRegex, phoneNumberMessage)
  .min(11)
  .required(requiredMessage);
const passportNumberValidation = yup
  .string()
  .matches(passportNumberRegex, passportNumberMessage);
const issuingCountryValidation = yup.string().required(requiredMessage);

export const loginSchema = yup.object().shape({
  email: emailValidation,
  password: passwordValidation,
});

export const signUpSchema = yup.object().shape({
  email: emailValidation,
  password: passwordValidation,
  confirmPassword: confirmPasswordValidation("password"),
  nationality: nationalityValidation,
  nationalId: nationalIdValidation,
  dob: dobValidation,
  countryCode: countryCodeValidation,
  phoneNumber: phoneNumberValidation,
  passportNumber: passportNumberValidation,
  issuingCountry: issuingCountryValidation,
  firstName: nameValidation,
  lastName: nameValidation,
  gender: genderValidation,
});

export const accountDetailsSchema = yup.object().shape({
  email: emailValidation,
  password: passwordValidation,
  confirmPassword: confirmPasswordValidation("password"),
  // nationality, nationalid, dob
});

export const contactDetailsSchema = yup.object().shape({
  // country code, phone number
});

export const additionalDetailsSchema = yup.object().shape({
  // first name, last name, passport number, issuing country
  firstName: nameValidation,
  lastName: nameValidation,
});

export const changePasswordSchema = yup.object().shape({
  password: passwordValidation,
});

export const resetPasswordSchema = yup.object().shape({
  newPassword: passwordValidation,
  confirmNewPassword: confirmPasswordValidation("newPassword"),
});

export const forgetPasswordSchema = yup.object().shape({
  email: emailValidation,
});
