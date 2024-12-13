import React from "react";
import Button from "../../../components/shared/Button";
import Input from "../../../components/shared/Input";
import { useAddFlightForm } from "./validation";
import { addFlightAPI } from "./api";
import "./style.css";
const AddFlight = () => {
  const {
    values,
    errors,
    touched,
    isSubmitting,
    status,
    handleChange,
    handleBlur,
    handleSubmit,
  } = useAddFlightForm(addFlightAPI);
  return (
    <div className="add-flight-container">
      <h1>Add Flight</h1>
      <form className="add-flight-form-container" onSubmit={handleSubmit}>
        <div className="row">
          <Input
            type={"date"}
            id={"departureDate"}
            placeholder={"Enter the departure date"}
            label={"Departure Date"}
            onChange={handleChange}
            value={values.departureDate}
            onBlur={handleBlur}
            showError={errors.departureDate && touched.departureDate}
            errorMessage={errors.departureDate}
          />
          <Input
            type={"date"}
            id={"arrivalDate"}
            placeholder={"Enter the arrival date"}
            label={"Arrival Date"}
            onChange={handleChange}
            value={values.arrivalDate}
            onBlur={handleBlur}
            showError={errors.arrivalDate && touched.arrivalDate}
            errorMessage={errors.arrivalDate}
          />
        </div>
        <div className="row">
          <Input
            type={"number"}
            id={"availableEconomySeats"}
            placeholder={"Enter available seats number"}
            label={"Available Economy Seats"}
            onChange={handleChange}
            value={values.availableEconomySeats}
            onBlur={handleBlur}
            showError={
              errors.availableEconomySeats && touched.availableEconomySeats
            }
            errorMessage={errors.availableEconomySeats}
          />
          <Input
            type={"number"}
            id={"availableBusinessSeats"}
            placeholder={"Enter available seats number"}
            label={"Available Business Seats"}
            onChange={handleChange}
            value={values.availableBusinessSeats}
            onBlur={handleBlur}
            showError={
              errors.availableBusinessSeats && touched.availableBusinessSeats
            }
            errorMessage={errors.availableBusinessSeats}
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
            value={values.economyPrice}
            onChange={handleChange}
            onBlur={handleBlur}
            showError={errors.economyPrice && touched.economyPrice}
            errorMessage={errors.economyPrice}
          />
          <Input
            type={"number"}
            id={"businessPrice"}
            name={"businessPrice"}
            placeholder={"Enter the price"}
            label={"Business class price"}
            min={"0"}
            step={"0.01"}
            value={values.businessPrice}
            onChange={handleChange}
            onBlur={handleBlur}
            showError={errors.businessPrice && touched.businessPrice}
            errorMessage={errors.businessPrice}
          />
        </div>
        <Button
          btnText={"Add Flight"}
          btnColor="pink"
          disabled={isSubmitting}
          type={"submit"}
        />
      </form>
    </div>
  );
};

export default AddFlight;
