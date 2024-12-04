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
          flights.map((flight) => (
            <FlightCard
              key={flight.id}
              flight={{
                flightNumber: flight.id,
                status: flight.isCancel ? "Cancelled" : "On Time",
                departureTime: flight.departureTime,
                arrivalTime: flight.arrivalTime,
                source: flight.source,
                destination: flight.destination,
                price: flight.economyPrice,
                seatsAvailable: flight.availableEconomySeats,
              }}
            />
          ))
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
    </div>
  );
};

export default Flights;
