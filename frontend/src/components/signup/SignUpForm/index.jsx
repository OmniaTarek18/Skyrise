// Import necessary modules and components
import React, { useEffect, useState, useRef } from "react"; // React for creating components
import { useNavigate } from "react-router-dom";
import GoogleSignUp from "../GoogleSignUp"; // Google SignUp component
import Button from "../../shared/Button"; // Button component for form submission
import Input from "../../shared/Input"; // Input component for form fields
import { useSignUpForm, countries, countryCodes, gender } from "./validation"; // Form validation logic and options
import { onSubmit } from "./api";
import "./style.css"; // CSS file for styling the form

// SignUpForm component handles the user registration form
const SignUpForm = () => {
  // const nav = useNavigate()
  const [alert, setAlert] = useState(false);
  const nav = useNavigate();
  const isFirstRender = useRef(true);
  // Handlers for updating Formik fields based on user input
  const handleEmail = (email) => setFieldValue("email", email);
  const handleFirstName = (firstName) => setFieldValue("firstName", firstName);
  const handleLastName = (lastName) => setFieldValue("lastName", lastName);

  // Formik hook for form state management and validation
  const {
    values,
    errors,
    touched,
    isSubmitting,
    status,
    handleChange,
    handleBlur,
    handleSubmit,
    setFieldValue,
  } = useSignUpForm(onSubmit);

  useEffect(() => {
    if (isFirstRender.current) {
      isFirstRender.current = false; // Skip effect on the first render
      return;
    }
    if (status === "success") {
      nav("/");
    } else {
      setAlert(true);
      setTimeout(() => {
        setAlert(false);
      }, 2000); // 2000 milliseconds = 2 seconds
    }
  }, [status, nav]);

  return (
    // Main form element
    <form className="" onSubmit={handleSubmit}>
      {alert && (
        <div className="alert alert-warning" role="alert">
          Email Or National_ID is used before!
        </div>
      )}
      {/* Essential details section: User's nationality and ID */}
      <section className="essential-details-container">
        <fieldset className="account-details-container">
          <legend>Account Details</legend>
          <div className="row">
            {/* Input field for nationality with selection options */}
            <Input
              label={"Nationality"}
              type={"text"}
              id={"nationality"}
              onChange={handleChange}
              value={values.nationality}
              onBlur={handleBlur}
              showError={errors.nationality && touched.nationality}
              errorMessage={errors.nationality}
              selectionInput={true}
              defaultSelectionText={"Country"}
              options={countries} // List of countries for selection
            />
            {/* Input field for national ID */}
            <Input
              label={"National ID"}
              type={"text"}
              id={"nationalId"}
              placeholder={"Enter your national ID"}
              onChange={handleChange}
              value={values.nationalId}
              onBlur={handleBlur}
              showError={errors.nationalId && touched.nationalId}
              errorMessage={errors.nationalId}
            />
          </div>
          <div className="row">
            {/* Input field for email address */}
            <Input
              label={"Email"}
              type={"email"}
              id={"email"}
              placeholder={"Enter your email"}
              onChange={handleChange}
              value={values.email}
              onBlur={handleBlur}
              showError={errors.email && touched.email}
              errorMessage={errors.email}
            />
            {/* Input field for date of birth */}
            <Input
              label={"Date of Birth"}
              type={"date"}
              id={"dateOfBirth"}
              placeholder={"mm/dd/yyyy"}
              onChange={handleChange}
              value={values.dateOfBirth}
              onBlur={handleBlur}
              showError={errors.dateOfBirth && touched.dateOfBirth}
              errorMessage={errors.dateOfBirth}
            />
          </div>
          <div className="row">
            {/* Input field for password */}
            <Input
              label={"Password"}
              type={"password"}
              id={"password"}
              placeholder={"Enter your password"}
              onChange={handleChange}
              value={values.password}
              onBlur={handleBlur}
              showError={errors.password && touched.password}
              errorMessage={errors.password}
            />
            {/* Input field for confirming password */}
            <Input
              label={"Confirm Password"}
              type={"password"}
              id={"confirmPassword"}
              placeholder={"Re-enter your password"}
              onChange={handleChange}
              value={values.confirmPassword}
              onBlur={handleBlur}
              showError={errors.confirmPassword && touched.confirmPassword}
              errorMessage={errors.confirmPassword}
            />
          </div>
        </fieldset>

        {/* Contact details section: Country code and phone number */}
        <fieldset className="contact-details-container">
          <legend>Contact Details</legend>
          <div className="row">
            {/* Input field for selecting country code */}
            <Input
              label={"Country Code"}
              type={"text"}
              id={"countryCode"}
              onChange={handleChange}
              value={values.countryCode}
              onBlur={handleBlur}
              showError={errors.countryCode && touched.countryCode}
              errorMessage={errors.countryCode}
              selectionInput={true}
              defaultSelectionText={"Code"}
              options={countryCodes} // List of country codes for selection
            />
            {/* Input field for phone number */}
            <Input
              label={"Phone Number"}
              type={"text"}
              id={"phoneNumber"}
              placeholder={"Enter your phone number"}
              onChange={handleChange}
              value={values.phoneNumber}
              onBlur={handleBlur}
              showError={errors.phoneNumber && touched.phoneNumber}
              errorMessage={errors.phoneNumber}
            />
          </div>
        </fieldset>
      </section>

      {/* Additional details section: Personal info and passport */}
      <section className="additional-details-container">
        <h2>Additional Details</h2>
        <fieldset className="personal-info-container">
          <legend>Personal Information</legend>
          <div className="row threeInputRow">
            {/* Input field for gender selection */}
            <Input
              label={"Gender"}
              id={"gender"}
              onChange={handleChange}
              value={values.gender}
              onBlur={handleBlur}
              showError={errors.gender && touched.gender}
              errorMessage={errors.gender}
              selectionInput={true}
              defaultSelectionText={"your gender"}
              options={gender} // List of gender options for selection
            />
            {/* Input field for first name */}
            <Input
              label={"First Name"}
              type={"text"}
              id={"firstName"}
              placeholder={"Enter your first name"}
              onChange={handleChange}
              value={values.firstName}
              onBlur={handleBlur}
              showError={errors.firstName && touched.firstName}
              errorMessage={errors.firstName}
            />
            {/* Input field for last name */}
            <Input
              label={"Last Name"}
              type={"text"}
              id={"lastName"}
              placeholder={"Enter your last name"}
              onChange={handleChange}
              value={values.lastName}
              onBlur={handleBlur}
              showError={errors.lastName && touched.lastName}
              errorMessage={errors.lastName}
            />
          </div>
          <div className="row">
            {/* Input field for passport number */}
            <Input
              label={"Passport Number"}
              type={"text"}
              id={"passportNumber"}
              placeholder={"Enter your passport number"}
              onChange={handleChange}
              value={values.passportNumber}
              onBlur={handleBlur}
              showError={errors.passportNumber && touched.passportNumber}
              errorMessage={errors.passportNumber}
            />
            {/* Input field for issuing country selection */}
            <Input
              label={"Issuing Country"}
              type={"text"}
              id={"passportIssuingCountry"}
              onChange={handleChange}
              value={values.passportIssuingCountry}
              onBlur={handleBlur}
              showError={
                errors.passportIssuingCountry && touched.passportIssuingCountry
              }
              errorMessage={errors.passportIssuingCountry}
              selectionInput={true}
              defaultSelectionText={"Country"}
              options={countries} // List of countries for selection
            />
          </div>
        </fieldset>
      </section>

      {/* Button row for submission */}
      <div className="button-row">
        {/* Submit button for the form */}
        <Button
          btnText={"Sign up"}
          btnColor="dark"
          disabled={isSubmitting}
          type="submit"
        />
        {/* Google SignUp button for OAuth authentication */}
        <GoogleSignUp
          handleEmail={handleEmail}
          handleFirstName={handleFirstName}
          handleLastName={handleLastName}
          touched={touched}
        />
      </div>
    </form>
  );
};

// Export SignUpForm component for use in other parts of the application
export default SignUpForm;
