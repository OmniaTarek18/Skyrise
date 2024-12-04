import React, { useState, useEffect } from "react";
import "./archive.css";
import { fetchArchivedFlights } from "../../api/archiveAPI";

const Archive = () => {
  const [flights, setFlights] = useState([]);
  const [pageNumber, setPageNumber] = useState(0);
  const [hasMorePages, setHasMorePages] = useState(true);

  useEffect(() => {
    loadFlights(pageNumber);
  }, [pageNumber]);

  const loadFlights = async (page) => {
    try {
      const data = await fetchArchivedFlights(page);
      setFlights(data.content || []);
      setHasMorePages(data.totalPages > page + 1);
    } catch (error) {
      console.error("Error fetching archived flights:", error);
    }
  };

  const nextPage = () => {
    if (hasMorePages) setPageNumber((prev) => prev + 1);
  };

  const prevPage = () => {
    if (pageNumber > 0) setPageNumber((prev) => prev - 1);
  };

  return (
    <div className="archive-page">
      <h1>Archived Flights</h1>
      <div className="flights-list">
        {flights.length > 0 ? (
          flights.map((flight) => (
            <div key={flight.id} className="flight-card">
              <div className="flight-card-header">
                <h2>Flight ID: {flight.id}</h2>
              </div>
              <div className="flight-card-details">
                <p>
                  <strong>Source:</strong> {flight.source}
                </p>
                <p>
                  <strong>Destination:</strong> {flight.destination}
                </p>
                <p>
                  <strong>Departure:</strong> {flight.departureDate} at{" "}
                  {flight.departureTime}
                </p>
                <p>
                  <strong>Arrival:</strong> {flight.arrivalDate} at{" "}
                  {flight.arrivalTime}
                </p>
                <p>
                  <strong>Business Seats Available:</strong>{" "}
                  {flight.availableBusinessSeats}
                </p>
                <p>
                  <strong>Economy Seats Available:</strong>{" "}
                  {flight.availableEconomySeats}
                </p>
                <p>
                  <strong>Business Price:</strong> L.E.{flight.businessPrice}
                </p>
                <p>
                  <strong>Economy Price:</strong> L.E.{flight.economyPrice}
                </p>
                <p>
                  <strong>Cancellation Status:</strong>{" "}
                  {flight.isCancel ? "Cancelled" : "Active"}
                </p>
              </div>
            </div>
          ))
        ) : (
          <p>No archived flights available.</p>
        )}
      </div>
      <div className="pagination-controls">
        <button onClick={prevPage} disabled={pageNumber === 0}>
          Previous
        </button>
        <button onClick={nextPage} disabled={!hasMorePages}>
          Next
        </button>
      </div>
    </div>
  );
};

export default Archive;
