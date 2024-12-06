import React, { useEffect, useState } from "react";
import { filterFlights } from "../../api/flightsAPI";
import { deleteFlight } from "../../api/archiveAPI"; // Import deleteFlight API
import FlightTable from "../../components/adminDashboard/Archive/FlightTable";
import "./archive.css";

const ArchivePage = () => {
  const [currentDate, setCurrentDate] = useState(new Date());
  const [flights, setFlights] = useState([]);
  const [loading, setLoading] = useState(false);
  const [filters, setFilters] = useState({
    departureDate: currentDate.toISOString().split("T")[0],
    source: null,
    destination: null,
    isCancel: null,
    sortby: null,
    direction: null,
  });
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [hasMorePages, setHasMorePages] = useState(true);

  const loadFlights = async (filters = {}, page = 0) => {
    setLoading(true);
    try {
      const data = await filterFlights(filters, page);
      setFlights(data.content || []);
      setTotalPages(data.totalPages || 0);
      setHasMorePages(data.content && data.content.length === 10);
    } catch (error) {
      console.error("Error fetching flights:", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (currentDate && currentDate instanceof Date && !isNaN(currentDate)) {
      loadFlights(
        { ...filters, departureDate: currentDate.toISOString().split("T")[0] },
        page
      );
    } else {
      setFlights([]);
    }
  }, [filters, page, currentDate]);

  const handleDateChange = (e) => {
    const newDate = new Date(e.target.value);
    setCurrentDate(newDate);
  };

  const nextPage = () => {
    if (page < totalPages - 1) {
      setPage(page + 1);
    }
  };

  const prevPage = () => {
    if (page > 0) {
      setPage(page - 1);
    }
  };

  const handleDelete = async (flightId) => {
    try {
      await deleteFlight(flightId);
      setFlights((prevFlights) =>
        prevFlights.filter((flight) => flight.id !== flightId)
      );
    } catch (error) {
      console.error("Failed to delete flight:", error);
    }
  };

  return (
    <div className="archive-page">
      <div className="archive-header">
        <h1>Archived Flights</h1>
      </div>

      {/* date */}
      <div className="date-picker-container">
        <label htmlFor="flight-date">Departure Date: </label>
        <div className="date-picker-wrapper">
          <input
            type="date"
            id="flight-date"
            value={currentDate ? currentDate.toISOString().split("T")[0] : ""}
            onChange={handleDateChange}
            className="date-picker"
          />
        </div>
      </div>

      {/* table of flights */}
      <div className="table-container">
        {loading ? (
          <p>Loading flights...</p>
        ) : flights.length === 0 ? (
          <p>No flights available for this date.</p>
        ) : (
          <FlightTable
            flights={flights}
            onDelete={handleDelete}
            loading={loading}
          />
        )}
      </div>

      <div className="pagination-controls">
        <button
          onClick={prevPage}
          disabled={page === 0}
          className="pagination-button"
        >
          Prev
        </button>
        <span className="pagination-info">
          Page {page + 1} of {totalPages}
        </span>
        <button
          onClick={nextPage}
          disabled={page === totalPages - 1 || !hasMorePages}
          className="pagination-button"
        >
          Next
        </button>
      </div>
    </div>
  );
};

export default ArchivePage;
