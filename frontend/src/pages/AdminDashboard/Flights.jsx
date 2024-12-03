import React from "react";
import "./flights.css";


const Flights = () => {
  const flightData = [
    {
      flight: "12354",
      route: "egypt - france",
      arrival: "12/11/2024, 2:34:16 AM",
      status: "SCHEDULED",
      price: "1235",
    },
    {
      flight: "12354",
      route: "egypt - france",
      arrival: "12/11/2024, 2:34:16 AM",
      status: "SCHEDULED",
      price: "1235",
    },
    {
      flight: "12354",
      route: "egypt - france",
      arrival: "12/11/2024, 2:34:16 AM",
      status: "SCHEDULED",
      price: "1235",
    },
    {
      flight: "12354",
      route: "egypt - france",
      arrival: "12/11/2024, 2:34:16 AM",
      status: "SCHEDULED",
      price: "1235",
    },
    {
      flight: "12354",
      route: "egypt - france",
      arrival: "12/11/2024, 2:34:16 AM",
      status: "SCHEDULED",
      price: "1235",
    },
    {
      flight: "12354",
      route: "egypt - france",
      arrival: "12/11/2024, 2:34:16 AM",
      status: "SCHEDULED",
      price: "1235",
    },
  ];

  return (
    <div className="flights">
      <h1>Flight Management</h1>
      {flightData.length === 0 ? (
        <div className="no-flights-container">
          <div className="no-flights-message">No flights to be shown</div>
        </div>
      ) : (
        <table>
          <thead>
            <tr>
              <th>Flight</th>
              <th>Route</th>
              <th>Arrival</th>
              <th>Status</th>
              <th>Price</th>
            </tr>
          </thead>
          <tbody>
            {flightData.map((flight, index) => (
              <tr key={index}>
                <td data-label="Flight">{flight.flight}</td>
                <td data-label="Route">{flight.route}</td>
                <td data-label="Arrival">{flight.arrival}</td>
                <td data-label="Status">{flight.status}</td>
                <td data-label="Price">{flight.price}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default Flights;
