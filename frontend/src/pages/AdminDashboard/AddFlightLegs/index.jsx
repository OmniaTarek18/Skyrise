import React from "react";
import Button from "../../../components/shared/Button";
import Input from "../../../components/shared/Input";
import { countries } from "../../../components/signup/SignUpForm/validation";
import { useAddFlightLegsForm } from "./validation";
import { addFlightLegsAPI } from "./api";
import "./style.css";

const AddFlightLegs = () => {
  const {
    values,
    errors,
    touched,
    isSubmitting,
    status,
    handleChange,
    handleBlur,
    handleSubmit,
  } = useAddFlightLegsForm(addFlightLegsAPI);

  return (
    <div className="add-flight-leg-container">
      <h1>Add Flight Leg</h1>
      <form className="add-flight-leg-form-container" onSubmit={handleSubmit}>
        <div className="row">
          <Input
            id={"arrivalAirport"}
            selectionInput={true}
            defaultSelectionText={"Enter arrival airport"}
            options={countries} // List of countries for selection
            label={"Arrival Airport"}
            onChange={handleChange}
            value={values.arrivalAirport}
            onBlur={handleBlur}
            showError={errors.arrivalAirport && touched.arrivalAirport}
            errorMessage={errors.arrivalAirport}
          />

          <Input
            id={"departureAirport"}
            selectionInput={true}
            defaultSelectionText={"Enter departure airport"}
            options={countries} // List of countries for selection
            label={"Departure Airport"}
            onChange={handleChange}
            value={values.departureAirport}
            onBlur={handleBlur}
            showError={errors.departureAirport && touched.departureAirport}
            errorMessage={errors.departureAirport}
          />
        </div>
        <div className="row">
          {" "}
          <Input
            type={"time"}
            id={"arrivalTime"}
            label={"Arrival Time"}
            onChange={handleChange}
            value={values.arrivalTime}
            onBlur={handleBlur}
            showError={errors.arrivalTime && touched.arrivalTime}
            errorMessage={errors.arrivalTime}
          />
          <Input
            type={"time"}
            id={"departureTime"}
            label={"Departure Time"}
            onChange={handleChange}
            value={values.departureTime}
            onBlur={handleBlur}
            showError={errors.departureTime && touched.departureTime}
            errorMessage={errors.departureTime}
          />
        </div>
        <Button
          btnText={"Add Flight Leg"}
          btnColor="pink"
          type={"submit"}
          disabled={isSubmitting}
        />
      </form>
    </div>
  );
};

export default AddFlightLegs;
