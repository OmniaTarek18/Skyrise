import React from "react";
import Button from "../../../components/shared/Button";
import Input from "../../../components/shared/Input";
import { countries } from "../../../components/signup/SignUpForm/validation";
import "./style.css"

const AddFlightLegs = () => {
  return (
    <div className="add-flight-leg-container">
      <h1>Add Flight Leg</h1>
      <form className="add-flight-leg-form-container">
        <div className="row">
        <Input
          id={"arrivalAirport"}
          selectionInput={true}
          defaultSelectionText={"Enter arrival airport"}
          options={countries} // List of countries for selection
          label={"Arrival Airport"}
        //   onChange={handleChange}
        //   value={values.source}
        //   onBlur={handleBlur}
        //   showError={errors.source && touched.source}
        //   errorMessage={errors.source}
        />

        <Input
          id={"departureAirport"}
          selectionInput={true}
          defaultSelectionText={"Enter departure airport"}
          options={countries} // List of countries for selection
          label={"Departure Airport"}
        //   onChange={handleChange}
        //   value={values.destination}
        //   onBlur={handleBlur}
        //   showError={errors.destination && touched.destination}
        //   errorMessage={errors.destination}
        />
        </div>
        <div className="row">
          <Input
            type={"time"}
            id={"departureTime"}
            label={"Departure Time"}
            //   onChange={handleChange}
            //   value={values.passengers}
            //   onBlur={handleBlur}
            //   showError={errors.passengers && touched.passengers}
            //   errorMessage={errors.passengers}
          />
          <Input
            type={"time"}
            id={"arrivalTime"}
            label={"Arrival Time"}
            //   onChange={handleChange}
            //   value={values.passengers}
            //   onBlur={handleBlur}
            //   showError={errors.passengers && touched.passengers}
            //   errorMessage={errors.passengers}
          />
        </div>
        <Button btnText={"Add Flight Leg"} btnColor="pink" />
      </form>
    </div>
  );
};

export default AddFlightLegs;
