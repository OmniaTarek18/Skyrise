// Import necessary modules and components
import React, { useEffect, useState, useRef } from "react"; // React for creating components
import { useNavigate } from "react-router-dom";
import GoogleSignUp from "../GoogleSignUp"; // Google SignUp component
import Button from "../../shared/Button"; // Button component for form submission
import Input from "../../shared/Input"; // Input component for form fields
import { useSignUpForm, countries, countryCodes, gender } from "./validation"; // Form validation logic and options
import { onSubmit } from "./api";
import useUserAuthenticationStore from "../../../store/useUserAuthenticationStore";
import "./style.css"; // CSS file for styling the form

// SignUpForm component handles the user registration form
const SignUpForm = () => {
  // const nav = useNavigate()
  const [alert, setAlert] = useState(false);
  const nav = useNavigate();
  const isFirstRender = useRef(true);
  const handleEmail = (email) => setFieldValue("email", email);
  const handleFirstName = (firstName) => setFieldValue("firstName", firstName);
  const handleLastName = (lastName) => setFieldValue("lastName", lastName);
  const {setUserAuthentication} = useUserAuthenticationStore()
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
      isFirstRender.current = false; 
      return;
    }
    if (status.status === "success") {
      setUserAuthentication(status.data, "USER")
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
      <section className="essential-details-container">
        <fieldset className="account-details-container">
          <legend>Account Details</legend>
          <div className="row">
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

        <fieldset className="contact-details-container">
          <legend>Contact Details</legend>
          <div className="row">
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

      <section className="additional-details-container">
        <h2>Additional Details</h2>
        <fieldset className="personal-info-container">
          <legend>Personal Information</legend>
          <div className="row three-input-row">
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

      <div className="button-row">
        <Button
          btnText={"Sign up"}
          btnColor="dark"
          disabled={isSubmitting}
          type="submit"
        />
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
