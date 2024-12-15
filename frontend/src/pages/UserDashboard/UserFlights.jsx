import React, { useState, useEffect } from "react";
import { FlightCard } from "../../components/adminDashboard/Flights/FlightCard";
import SearchFlight from "../../components/userdashboard/UserFlights/SearchFlight";
import { fetchUserFlights } from "../../api/userFlightsAPI";
import { Plane } from "lucide-react";
import "./userflights.css";

const UserFlights = () => {
  const [flights, setFlights] = useState([]);
  const [filters, setFilters] = useState({
    flightNumber: "",
    source: null,
    destination: null,
    departureDate: null,
    arrivalDate: null,
    category: "all",
  });
  const [page, setPage] = useState(0);
  const [hasMorePages, setHasMorePages] = useState(true);
  const [error, setError] = useState("");
  const [locations, setLocations] = useState({ from: [], to: [] });

  // Fetch flights with filters
  const loadFlights = async (filters = {}, page = 0) => {
    try {
      const data = await fetchUserFlights(filters, page);
      setFlights(data.content || []);
      setHasMorePages(data.content?.length === 10);
      setError(data.content?.length === 0 ? "No flights found." : "");
    } catch (err) {
      console.error("Error fetching flights:", err);
      setError("Failed to load flights. Please try again.");
    }
  };

  // fetch locations (sources and destinations) for the select list
  useEffect(() => {
    const fetchLocations = async () => {
      try {
        const data = await fetchUserFlights({}, 0);
        if (data.content) {
          const uniqueSources = [...new Set(data.content.map((f) => f.source))];
          const uniqueDestinations = [
            ...new Set(data.content.map((f) => f.destination)),
          ];
          setLocations({
            from: uniqueSources.map((src) => ({ value: src, label: src })),
            to: uniqueDestinations.map((dest) => ({
              value: dest,
              label: dest,
            })),
          });
        }
      } catch (err) {
        console.error("Error fetching locations:", err);
      }
    };
    fetchLocations();
  }, []);

  const handleInputChange = (name, value) => {
    setFilters((prev) => ({ ...prev, [name]: value }));
  };

  const handleFilterCategory = (category) => {
    setFilters((prev) => ({ ...prev, category }));
  };

  const nextPage = () => {
    if (hasMorePages) setPage((prev) => prev + 1);
  };

  const prevPage = () => setPage((prev) => Math.max(prev - 1, 0));

  return (
    <div className="user-flights-container">
      <div className="content-container">
        <div className="header">
          <Plane className="plane-icon" />
          <h1 className="title">Flight Tracker</h1>
        </div>

        {/* pass the locations and filters to SearchFlight */}
        <div className="search-container">
          <SearchFlight
            filters={filters}
            locations={locations}
            onInputChange={handleInputChange}
            onSearch={() => loadFlights(filters, 0)}
            onFilterCategory={handleFilterCategory}
          />
        </div>

        {error && (
          <div className="error-message">
            <p className="error-text">{error}</p>
          </div>
        )}
        <div className="flight-cards">
          {flights.map((flight) => (
            <FlightCard key={flight.flightId} flight={flight} />
          ))}
        </div>
        <div className="pagination-controls">
          <button
            className="pagination-button"
            onClick={prevPage}
            disabled={page === 0}
          >
            Prev
          </button>
          <button
            className="pagination-button"
            onClick={nextPage}
            disabled={flights.length === 0 || !hasMorePages}
          >
            Next
          </button>
        </div>
      </div>
    </div>
  );
};

export default UserFlights;
