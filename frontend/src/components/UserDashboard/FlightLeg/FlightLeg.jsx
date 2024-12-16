import React from 'react';
import AirportInfo from './AirportInfo';
import './FlightLeg.css';

const FlightLeg = ({
  departureTime,
  departureAirportName,
  departureAirportCountry,
  departureAirportCity,
  departureAirportCode,
  arrivalTime,
  arrivalAirportName,
  arrivalAirportCountry,
  arrivalAirportCity,
  arrivalAirportCode,
}) => {
  const isDayFlight =
    parseInt(departureTime.split(":")[0]) > 6 &&
    parseInt(departureTime.split(":")[0]) < 18;

  return (
    <div className="flight-leg-card">
      <div className="card-content">
        <div className="airports-container">
          <AirportInfo
            time={departureTime}
            airportName={departureAirportName}
            airportCountry={departureAirportCountry}
            airportCity={departureAirportCity}
            airportCode={departureAirportCode}
            type="departure"
          />

          <div className="arrow">→</div>

          <AirportInfo
            time={arrivalTime}
            airportName={arrivalAirportName}
            airportCountry={arrivalAirportCountry}
            airportCity={arrivalAirportCity}
            airportCode={arrivalAirportCode}
            type="arrival"
          />
        </div>
      </div>
    </div>
  );
};

export default FlightLeg;