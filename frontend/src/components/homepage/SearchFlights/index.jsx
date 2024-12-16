import React, { useEffect } from "react";
import Input from "../../shared/Input";
import "./style.css";
import Button from "../../shared/Button";
import { countries } from "../../signup/SignUpForm/validation";
import { useSearchFlightsForm } from "./validation";
import { searchFlightsAPI } from "./api";

const SearchFlights = () => {
  const {
    values,
    errors,
    touched,
    isSubmitting,
    handleChange,
    handleBlur,
    handleSubmit,
    setFieldValue,
  } = useSearchFlightsForm(searchFlightsAPI); // Passing the user's email to the hook

  useEffect(() => {
    if (values.tripType === "one-way") {
      setFieldValue("arrivalDate", "No arrival date for one-way");
    }else{
      setFieldValue("arrivalDate", "");
    }
  }, [values.tripType, setFieldValue]);

  return (
    <form className="search-flights-container" onSubmit={handleSubmit}>
      <div className="row row1">
        <fieldset className="col-md-6 trip-type-container">
          <div className="form-check form-check-inline">
            <input
              className="form-check-input"
              type="radio"
              name="tripType"
              id="round-trip"
              value="round-trip"
              checked={values.tripType === "round-trip"}
              onChange={handleChange}
            />
            <label className="form-check-label" htmlFor="round-trip">
              Round Trip
            </label>
          </div>
          <div className="form-check form-check-inline">
            <input
              className="form-check-input"
              type="radio"
              name="tripType"
              id="one-way"
              value="one-way"
              checked={values.tripType === "one-way"}
              onChange={handleChange}
            />
            <label className="form-check-label" htmlFor="one-way">
              One-Way
            </label>
          </div>
        </fieldset>

        <fieldset className="col-md-6 class-container">
          <div className="form-check form-check-inline">
            <input
              className="form-check-input"
              type="radio"
              name="class"
              id="economy"
              value="economy"
              checked={values.class === "economy"}
              onChange={handleChange}
            />
            <label className="form-check-label" htmlFor="economy">
              Economy Class
            </label>
          </div>
          <div className="form-check form-check-inline">
            <input
              className="form-check-input"
              type="radio"
              name="class"
              id="business"
              value="business"
              checked={values.class === "business"}
              onChange={handleChange}
            />
            <label className="form-check-label" htmlFor="business">
              Business Class
            </label>
          </div>
        </fieldset>
      </div>

      <div className="row row2">
        <Input
          id={"source"}
          selectionInput={true}
          defaultSelectionText={"Source"}
          options={countries} // List of countries for selection
          onChange={handleChange}
          value={values.source}
          onBlur={handleBlur}
          showError={errors.source && touched.source}
          errorMessage={errors.source}
        />

        <Input
          id={"destination"}
          selectionInput={true}
          defaultSelectionText={"Destination"}
          options={countries} // List of countries for selection
          onChange={handleChange}
          value={values.destination}
          onBlur={handleBlur}
          showError={errors.destination && touched.destination}
          errorMessage={errors.destination}
        />
        <Input
          type={"date"}
          id={"departureDate"}
          placeholder={"Departure Date"}
          onChange={handleChange}
          value={values.departureDate}
          onBlur={handleBlur}
          showError={errors.departureDate && touched.departureDate}
          errorMessage={errors.departureDate}
        />

        <Input
          type={"date"}
          id={"arrivalDate"}
          placeholder={"Arrival Date"}
          onChange={handleChange}
          value={values.arrivalDate}
          onBlur={handleBlur}
          showError={errors.arrivalDate && touched.arrivalDate}
          errorMessage={errors.arrivalDate}
          disabled={values.tripType === "one-way"}
        />
        <Input
          type={"number"}
          id={"passengers"}
          placeholder={"Passengers"}
          onChange={handleChange}
          value={values.passengers}
          onBlur={handleBlur}
          showError={errors.passengers && touched.passengers}
          errorMessage={errors.passengers}
        />
        <Button
          btnText={"Search Flights"}
          type="submit"
          disabled={isSubmitting}
        />
      </div>
    </form>
  );
};

export default SearchFlights;
