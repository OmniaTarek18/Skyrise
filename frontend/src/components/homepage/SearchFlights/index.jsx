import React from "react";
import Input from "../../shared/Input";
import "./style.css";
import Button from "../../shared/Button";
import { countries } from "../../signup/SignUpForm/validation";

const SearchFlights = () => {
  return (
    <form className="search-flights-container">
      <div className="row row1">
        <fieldset className="col-md-6 trip-type-container">
          <div class="form-check form-check-inline">
            <input
              class="form-check-input"
              type="radio"
              name="trip-type"
              id="round-trip"
              value="round-trip"
              checked
            />
            <label class="form-check-label" for="round-trip">
              Round Trip
            </label>
          </div>
          <div class="form-check form-check-inline">
            <input
              class="form-check-input"
              type="radio"
              name="trip-type"
              id="one-way"
              value="one-way"
            />
            <label class="form-check-label" for="one-way">
              One-Way
            </label>
          </div>
        </fieldset>

        <fieldset className="col-md-6 class-container">
          <div class="form-check form-check-inline">
            <input
              class="form-check-input"
              type="radio"
              name="class"
              id="economy"
              value="economy"
              checked
            />
            <label class="form-check-label" for="economy">
              Economy Class
            </label>
          </div>
          <div class="form-check form-check-inline">
            <input
              class="form-check-input"
              type="radio"
              name="class"
              id="business"
              value="business"
            />
            <label class="form-check-label" for="business">
              Business Class
            </label>
          </div>
        </fieldset>
      </div>

      <div className="row row2">
        <Input
          id={"sourceCountry"}
          selectionInput={true}
          defaultSelectionText={"Source"}
          options={countries} // List of countries for selection
        />

        <Input
          id={"destinationCountry"}
          selectionInput={true}
          defaultSelectionText={"Destination"}
          options={countries} // List of countries for selection
        />
        <Input
          type={"date"}
          id={"departureDate"}
          placeholder={"Departure Date"}
        />
        <Input type={"date"} id={"arrivalDate"} placeholder={"Arrival Date"} />
        <Input
          type={"number"}
          id={"passengersNumber"}
          placeholder={"Passengers"}
        />
        <Button btnText={"Search Flights"} type="submit" />
      </div>
    </form>
  );
};

export default SearchFlights;
