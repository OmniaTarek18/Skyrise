import React, { useState } from "react";
import { useTicketReservationForm } from "./validation";
import { bookFlightAPI, ticketReservationAPI } from "./api";
import { gender, specialNeeds, mealSpecification } from "./validation";
import { countries, countryCodes } from "../signup/SignUpForm/validation";
import Button from "../shared/Button";
import Input from "../shared/Input";
import useUserAuthenticationStore from "../../store/useUserAuthenticationStore";
import "./style.css";
import useSearchFlightDetails from "../../store/useSearchFlightDetails";
import { useNavigate } from "react-router-dom";
const TicketForm = ({ numberOfTickets, flightId }) => {
  const [currentTicket, setCurrentTicket] = useState(0);
  const [tickets, setTickets] = useState([]);
  const { id } = useUserAuthenticationStore();
  const { searchFlightDetails } = useSearchFlightDetails();
  const nav = useNavigate();
  numberOfTickets = searchFlightDetails.numberOfTickets;
  const {
    values,
    errors,
    touched,
    isSubmitting,
    handleChange,
    handleBlur,
    resetForm,
    handleSubmit,
    setFieldValue,
  } = useTicketReservationForm(async (submittedValues) => {
    setTickets((prevTickets) => {
      const updatedTickets = [...prevTickets];

      if (updatedTickets[currentTicket]) {
        updatedTickets[currentTicket] = submittedValues;
      } else {
        updatedTickets.push(submittedValues);
      }
      return updatedTickets;
    });
    if (currentTicket + 1 < numberOfTickets) {
      editCurrentTicket();
      resetForm();
    } else {
      await bookFlightAPI(
        flightId,
        id,
        searchFlightDetails.seatClass,
        numberOfTickets
      );
      await ticketReservationAPI(tickets.concat(submittedValues), flightId, id);
      nav("/");
    }
  });

  const editPreviousTicket = () => {
    setCurrentTicket((prevTicket) => {
      const newTicket = prevTicket - 1;
      Object.entries(tickets[newTicket]).forEach(([key, value]) => {
        setFieldValue(key, value === null || value === undefined ? "" : value);
      });
      return newTicket;
    });
  };

  const editCurrentTicket = () => {
    setCurrentTicket((prevTicket) => {
      const newTicket = prevTicket + 1;
      if (newTicket < tickets.length) {
        Object.entries(tickets[newTicket]).forEach(([key, value]) => {
          setFieldValue(
            key,
            value === null || value === undefined ? "" : value
          );
        });
      }
      return newTicket;
    });
  };

  return (
    <div className="ticket-form-container">
      <h1 className="ticket-reservation-heading">Ticket Reservation</h1>
      <h3 className="ticket-reservation-heading">
        Filling details for ticket {currentTicket + 1} of {numberOfTickets}
      </h3>
      <form className="ticket-form-container" onSubmit={handleSubmit}>
        <div className="inputs-container">
          <fieldset className="passport-details-container">
            <legend>Passport Details</legend>
            <div className="row">
              <Input
                label="Passport Number"
                type="text"
                id="passportNumber"
                placeholder="Enter your passport number"
                onChange={handleChange}
                value={values.passportNumber}
                onBlur={handleBlur}
                showError={errors.passportNumber && touched.passportNumber}
                errorMessage={errors.passportNumber}
              />
              <Input
                label="Issuing Country"
                type="text"
                id="passportIssuingCountry"
                onChange={handleChange}
                value={values.passportIssuingCountry}
                onBlur={handleBlur}
                showError={
                  errors.passportIssuingCountry &&
                  touched.passportIssuingCountry
                }
                errorMessage={errors.passportIssuingCountry}
                selectionInput
                defaultSelectionText="Select Country"
                options={countries}
              />
            </div>
          </fieldset>
          <fieldset className="personal-details-container">
            <legend>Personal Details</legend>
            <div className="row">
              <Input
                label="Date of Birth"
                type="date"
                id="dateOfBirth"
                onChange={handleChange}
                value={values.dateOfBirth}
                onBlur={handleBlur}
                showError={errors.dateOfBirth && touched.dateOfBirth}
                errorMessage={errors.dateOfBirth}
              />
              <Input
                label="National ID"
                type="text"
                id="nationalId"
                placeholder="Enter your national ID"
                onChange={handleChange}
                value={values.nationalId}
                onBlur={handleBlur}
                showError={errors.nationalId && touched.nationalId}
                errorMessage={errors.nationalId}
              />
            </div>
            <div className="three-input-row">
              <Input
                label="Gender"
                id="gender"
                onChange={handleChange}
                value={values.gender}
                onBlur={handleBlur}
                showError={errors.gender && touched.gender}
                errorMessage={errors.gender}
                selectionInput
                defaultSelectionText="Select Gender"
                options={gender}
              />
              <Input
                label="First Name"
                type="text"
                id="firstName"
                placeholder="Enter your first name"
                onChange={handleChange}
                value={values.firstName}
                onBlur={handleBlur}
                showError={errors.firstName && touched.firstName}
                errorMessage={errors.firstName}
              />
              <Input
                label="Last Name"
                type="text"
                id="lastName"
                placeholder="Enter your last name"
                onChange={handleChange}
                value={values.lastName}
                onBlur={handleBlur}
                showError={errors.lastName && touched.lastName}
                errorMessage={errors.lastName}
              />
            </div>
            <div className="row">
              <Input
                label="Special Needs"
                id="specialNeeds"
                onChange={handleChange}
                value={values.specialNeeds}
                onBlur={handleBlur}
                showError={errors.specialNeeds && touched.specialNeeds}
                errorMessage={errors.specialNeeds}
                selectionInput
                defaultSelectionText="Select Special Needs"
                options={specialNeeds}
              />
              <Input
                label="Meal Specification"
                id="mealSpecification"
                onChange={handleChange}
                value={values.mealSpecification}
                onBlur={handleBlur}
                showError={
                  errors.mealSpecification && touched.mealSpecification
                }
                errorMessage={errors.mealSpecification}
                selectionInput
                defaultSelectionText="Select Meal"
                options={mealSpecification}
              />
            </div>
          </fieldset>
          <fieldset className="contact-details-container">
            <legend>Contact Details</legend>
            <div className="row">
              <Input
                label="Country Code"
                type="text"
                id="countryCode"
                onChange={handleChange}
                value={values.countryCode}
                onBlur={handleBlur}
                showError={errors.countryCode && touched.countryCode}
                errorMessage={errors.countryCode}
                selectionInput
                defaultSelectionText="Select Code"
                options={countryCodes}
              />
              <Input
                label="Phone Number"
                type="text"
                id="phoneNumber"
                placeholder="Enter your phone number"
                onChange={handleChange}
                value={values.phoneNumber}
                onBlur={handleBlur}
                showError={errors.phoneNumber && touched.phoneNumber}
                errorMessage={errors.phoneNumber}
              />
            </div>
          </fieldset>
        </div>
        <div className="button-row">
          {currentTicket > 0 && (
            <Button
              btnText={"Edit Previous Ticket"}
              btnColor="dark"
              disabled={isSubmitting}
              type="button"
              handleClick={editPreviousTicket}
            />
          )}

          <Button
            btnText={
              currentTicket + 1 < numberOfTickets ? "Next Ticket" : "Submit All"
            }
            btnColor="light"
            disabled={isSubmitting}
            type="submit"
          />
        </div>
      </form>
    </div>
  );
};

export default TicketForm;
