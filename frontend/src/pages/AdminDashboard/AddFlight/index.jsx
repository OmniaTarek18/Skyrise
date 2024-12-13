import React from "react";
import Button from "../../../components/shared/Button";
import Input from "../../../components/shared/Input";
import "./style.css"
const AddFlight = () => {
  return (
    <div className="add-flight-container">
      <h1>Add Flight</h1>
      <form className="add-flight-form-container">
        <div className="row">
            <Input
              type={"date"}
              id={"departureDate"}
              placeholder={"Enter the departure date"}
              label={"Departure Date"}
              //   onChange={handleChange}
              //   value={values.departureDate}
              //   onBlur={handleBlur}
              //   showError={errors.departureDate && touched.departureDate}
              //   errorMessage={errors.departureDate}
            />
            <Input
              type={"date"}
              id={"arrivalDate"}
              placeholder={"Enter the arrival date"}
              label={"Arrival Date"}
              //   onChange={handleChange}
              //   value={values.arrivalDate}
              //   onBlur={handleBlur}
              //   showError={errors.arrivalDate && touched.arrivalDate}
              //   errorMessage={errors.arrivalDate}
              //   disabled={values.tripType === "one-way"}
            />
        </div>
        <div className="row">
            <Input
              type={"number"}
              id={"availableEconomySeats"}
              placeholder={"Enter available seats number"}
              label={"Available Economy Seats"}
              //   onChange={handleChange}
              //   value={values.passengers}
              //   onBlur={handleBlur}
              //   showError={errors.passengers && touched.passengers}
              //   errorMessage={errors.passengers}
            />
            <Input
              type={"number"}
              id={"availableBusinessSeats"}
              placeholder={"Enter available seats number"}
              label={"Available Business Seats"}
              //   onChange={handleChange}
              //   value={values.passengers}
              //   onBlur={handleBlur}
              //   showError={errors.passengers && touched.passengers}
              //   errorMessage={errors.passengers}
            />
        </div>
        <div className="row">
            <Input
              type={"number"}
              id={"economyPrice"}
              name={"economyPrice"}
              placeholder={"Enter the price"}
              label={"Economy class price"}
              min={"0"}
              step={"0.01"}
            />
            <Input
              type={"number"}
              id={"businessPrice"}
              name={"businessPrice"}
              placeholder={"Enter the price"}
              label={"Business class price"}
              min={"0"}
              step={"0.01"}
            />
        </div>
        <Button btnText={"Add Flight"} btnColor="pink" />
      </form>
    </div>
  );
};

export default AddFlight;
