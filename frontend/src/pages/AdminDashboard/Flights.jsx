<<<<<<< HEAD
import React, { useEffect, useState } from "react";
import { Timeline } from "../../components/adminDashboard/Flights/Timeline";
import { FlightCard } from "../../components/adminDashboard/Flights/FlightCard";
import FilterModal from "../../components/shared/FilterModal/FilterModal";
import { fetchFlightsByDate, filterFlights } from "../../api/flightsAPI";
import { Filter } from "lucide-react";
import "./flights.css";

const Flights = () => {
  const [currentDate, setCurrentDate] = useState(new Date());
  const [flights, setFlights] = useState([]);
  const [isFilterOpen, setIsFilterOpen] = useState(false);
  const [page, setPage] = useState(0);
  const [filters, setFilters] = useState({
    source: "",
    destination: "",
    status: "",
  });
  const [sortBy, setSortBy] = useState(null);
  const [hasMorePages, setHasMorePages] = useState(true); 

  const loadFlights = async (date, filters = {}, page = 0, sortBy = null) => {
    try {
      let data;
      if (filters.source || filters.destination || filters.status) {
        data = await filterFlights({ ...filters, date, sortBy }, page);
      } else {
        const formattedDate = date.toISOString().split("T")[0];
        data = await fetchFlightsByDate(formattedDate, page, sortBy);
      }
      setFlights(data.content || []);
      setHasMorePages(data.content && data.content.length === 10);
    } catch (error) {
      console.error("Error fetching flights:", error);
      setFlights([]);
      setHasMorePages(false);
    }
  };

  useEffect(() => {
    loadFlights(currentDate, filters, page, sortBy);
  }, [currentDate, filters, page, sortBy]);

  const toggleFilterModal = () => setIsFilterOpen(!isFilterOpen);

  const handleFilterChange = (filterType, value) => {
    setFilters((prev) => ({
      ...prev,
      [filterType]: value,
    }));
    setPage(0);
  };

  const handleSort = (sortType) => {
    setSortBy(sortType);
    setPage(0);
  };

  const nextPage = () => {
    if (hasMorePages) {
      setPage((prev) => prev + 1);
    }
  };

  const prevPage = () => setPage((prev) => Math.max(prev - 1, 0));

  return (
    <div className="flights-page">
      <div className="flights-header">
        <h1>Flight Schedule</h1>
        <div className="sort-buttons">
          <button
            className="sort-button"
            onClick={() => handleSort("flightNumber")}
          >
            Sort by Flight Number
          </button>
          <button
            className="sort-button"
            onClick={() => handleSort("departureDate")}
          >
            Sort by Departure Date
          </button>
          <button
            className="sort-button"
            onClick={() => handleSort("arrivalDate")}
          >
            Sort by Arrival Date
          </button>
        </div>
        <button onClick={toggleFilterModal} className="filter-button">
          <Filter className="filter-icon" />
        </button>
      </div>

      <Timeline currentDate={currentDate} onDateChange={setCurrentDate} />

      <FilterModal
        isOpen={isFilterOpen}
        onClose={toggleFilterModal}
        onFilterChange={handleFilterChange}
      />

      <div className="flights-list">
        {flights.length > 0 ? (
          flights.map((flight) => {
            console.log("Flight data:", flight); 
            return (
              <FlightCard
                key={flight.id}
                flight={{
                  flightNumber: flight.id,
                  status: flight.isCancel ? "Cancelled" : "On Time",
                  departureTime: flight.departureTime,
                  arrivalTime: flight.arrivalTime,
                  source: flight.source,
                  destination: flight.destination,
                  economyPrice: flight.economyPrice,
                  economySeatsAvailable: flight.availableEconomySeats,
                  businessPrice: flight.businessPrice,
                  businessSeatsAvailable: flight.availableBusinessSeats,
                }}
              />
            );
          })
        ) : (
          <p className="no-flights-message">
            No flights available for this date.
          </p>
        )}
      </div>

      <div className="pagination-controls">
        <button onClick={prevPage} disabled={page === 0}>
          Prev
        </button>
        <button onClick={nextPage} disabled={!hasMorePages}>
          Next
        </button>
      </div>
=======
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
>>>>>>> feat-SCRUM-45-Admin-Dashboard-Frontend
    </div>
  );
};

export default Flights;
