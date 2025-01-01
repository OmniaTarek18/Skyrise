import React, { useState, useEffect } from "react";
import SearchFlight from "../../components/UserDashboard/UserFlights/SearchFlight";
import { fetchUserFlights } from "../../api/userFlightsAPI";
import "./userflights.css";
import FlightTicket from "../../components/UserDashboard/Ticket/FlightTicket";
import useUserAuthenticationStore from "../../store/useUserAuthenticationStore";
const UserFlights = () => {
  const { id, role } = useUserAuthenticationStore();
  const [flights, setFlights] = useState([]);
  const [filters, setFilters] = useState({
    userId: id,
    source: null,
    destination: null,
    departureDate: null,
    arrivalDate: null,
    sortBy: null,
    direction: "asc",
    pastFlights: false,
    recentFlights: false,
  });
  const [page, setPage] = useState(0);
  const [hasMorePages, setHasMorePages] = useState(true);
  const [error, setError] = useState("");
  const [locations, setLocations] = useState({ from: [], to: [] });

  // Fetch flights with filters
  const loadFlights = async (filters, page = 0) => {
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

  useEffect(() => {
    loadFlights(filters, 0);
  }, []);

  useEffect(() => {
    const fetchLocations = async () => {
      try {
        const data = await fetchUserFlights(filters, 0);

        if (data.content) {
          const uniqueSources = [
            ...new Set(data.content.map((f) => f.source.split(", ")[0])),
          ];

          const uniqueDestinations = [
            ...new Set(data.content.map((f) => f.destination.split(", ")[0])),
          ];

          setLocations({
            from: uniqueSources.map((src) => ({
              value: src,
              label: `${src}, ${data.content[0].source.split(", ")[1]}`,
            })),
            to: uniqueDestinations.map((dest) => ({
              value: dest,
              label: `${dest}, ${data.content[0].destination.split(", ")[1]}`,
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

  const nextPage = () => {
    if (hasMorePages) setPage((prev) => prev + 1);
  };

  const prevPage = () => setPage((prev) => Math.max(prev - 1, 0));

  return (
    <div className="user-flights-container">
      <div className="content-container">
        <div className="search-container">
          <SearchFlight
            filters={filters}
            locations={locations}
            onInputChange={handleInputChange}
            onSearch={() => loadFlights(filters, 0)}
          />
        </div>

        {error && (
          <div className="error-message">
            <p className="error-text">{error}</p>
          </div>
        )}
        <div className="flight-tickets">
          {flights.map((flight) => (
            <FlightTicket key={flight.flightId} {...flight} />
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
