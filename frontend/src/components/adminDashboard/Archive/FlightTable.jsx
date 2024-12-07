import React from "react";
import { Trash2 } from "lucide-react";
import { Button, Spinner } from "react-bootstrap";
import "./flighttable.css";

export function FlightTable({ flights, onDelete, loading }) {
  if (loading) {
    return (
      <div className="loading-container">
        <Spinner animation="border" variant="primary" />
        <p>Loading flights...</p>
      </div>
    );
  }

  return (
    <div className="table-container">
      <table className="flights-table">
        <thead>
          <tr>
            <th>Source</th>
            <th>Destination</th>
            <th>ArrivalDate</th>
            <th>Arrival Time</th>
            <th>Departure Time</th>
            <th>Economy Price</th>
            <th>Business Price</th>
            <th>Status</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          {flights.map((flight) => (
            <tr key={flight.id}>
              <td>{flight.source}</td>
              <td>{flight.destination}</td>
              <td>{flight.arrivalDate}</td>
              <td>{flight.arrivalTime}</td>
              <td>{flight.departureTime}</td>
              <td>{flight.economyPrice.toFixed(2)}</td>
              <td>{flight.businessPrice.toFixed(2)}</td>
              <td>
                <span
                  className={`status-badge ${
                    flight.isCancel ? "status-cancelled" : "status-completed"
                  }`}
                >
                  {flight.isCancel ? "Cancelled" : "Completed"}
                </span>
              </td>
              <td>
                <Button
                  variant="danger"
                  onClick={() => onDelete(flight.id)}
                  className="delete-button"
                >
                  <Trash2 size={16} />
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default FlightTable;
