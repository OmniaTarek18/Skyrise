import React, { useEffect, useState } from "react";
import Input from "../../shared/Input";
import "./style.css";
import Button from "../../shared/Button";
import { useSearchFlightsForm } from "./validation";
import { getCountriesAndAirportsToTravelAPI } from "./api";
import { useNavigate } from "react-router-dom";
import useSearchFlightDetails from "../../../store/useSearchFlightDetails";

const SearchFlights = () => {
  const [countries, setCountries] = useState({});
  const nav = useNavigate();
  const { setSearchFlightDetails } = useSearchFlightDetails();
  const {
    values,
    errors,
    touched,
    isSubmitting,
    handleChange,
    handleBlur,
    handleSubmit,
    setFieldValue,
  } = useSearchFlightsForm(() => {
    setSearchFlightDetails(values);
    nav("./search-flights");
  });

  useEffect(() => {
    if (values.tripType === "one-way") {
      setFieldValue("arrivalDate", "No arrival date for one-way");
    } else {
      setFieldValue("arrivalDate", "");
    }
  }, [values.tripType, setFieldValue]);

  const countryOptions = Object.entries(countries).map(([_, country]) => ({
    label: `${country.airportCity}, ${country.airportCountry}`,
    value: country.id,
  }));

  const handleFocus = async () => {
    const json = await getCountriesAndAirportsToTravelAPI();
    setCountries(json);
  };

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
              name="seatClass"
              id="economy"
              value="ECONOMY"
              checked={values.seatClass === "ECONOMY"}
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
              name="seatClass"
              id="business"
              value="BUSINESS"
              checked={values.seatClass === "BUSINESS"}
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
          id={"departureAirportId"}
          selectionInput={true}
          defaultSelectionText={"Source"}
          options={countryOptions} // List of countries for selection
          onChange={handleChange}
          value={values.departureAirportId}
          onFocus={handleFocus}
          onBlur={handleBlur}
          showError={errors.departureAirportId && touched.departureAirportId}
          errorMessage={errors.departureAirportId}
          isJson={true}
        />
        <Input
          id={"arrivalAirportId"}
          selectionInput={true}
          defaultSelectionText={"Destination"}
          options={countryOptions} // List of countries for selection
          onChange={handleChange}
          value={values.arrivalAirportId}
          onBlur={handleBlur}
          showError={errors.arrivalAirportId && touched.arrivalAirportId}
          errorMessage={errors.arrivalAirportId}
          onFocus={handleFocus}
          isJson={true}
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
          id={"numberOfTickets"}
          placeholder={"passengers"}
          onChange={handleChange}
          value={values.numberOfTickets}
          onBlur={handleBlur}
          showError={errors.numberOfTickets && touched.numberOfTickets}
          errorMessage={errors.numberOfTickets}
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
