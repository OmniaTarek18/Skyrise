import React, { useEffect, useState } from "react";
import { Timeline } from "../../components/adminDashboard/Flights/Timeline";
import { FlightCard } from "../../components/adminDashboard/Flights/FlightCard";
import FilterModal from "../../components/shared/FilterModal/FilterModal";
import { filterFlights } from "../../api/flightsAPI";
import { Filter } from "lucide-react";
import "./flights.css";

const Flights = () => {
  const [currentDate, setCurrentDate] = useState(new Date());
  const [flights, setFlights] = useState([]);
  const [isFilterOpen, setIsFilterOpen] = useState(false);
  const [page, setPage] = useState(0);
  const [filters, setFilters] = useState({
    departureDate: currentDate.toISOString().split("T")[0],
    source: null,
    destination: null,
    isCancel: null,
    sortby: null,
    direction: null,
  });

  const [locations, setLocations] = useState({ from: [], to: [] });
  const [hasMorePages, setHasMorePages] = useState(true);
  const [modalKey, setModalKey] = useState(Date.now());

  const loadFlights = async (filters = {}, page = 0) => {
    try {
      const data = await filterFlights(filters, page);
      setFlights(data.content || []);
      setHasMorePages(data.content && data.content.length === 10);
    } catch (error) {
      console.error("Error fetching flights:", error);
      setFlights([]);
      setHasMorePages(false);
    }
  };

  useEffect(() => {
    const formattedDate = currentDate.toISOString().split("T")[0];
    setFilters({
      departureDate: formattedDate,
      source: null,
      destination: null,
      isCancel: null,
      sortby: null,
      direction: null,
    });
    setModalKey(Date.now()); 
    setPage(0); 
  }, [currentDate]);

 
  useEffect(() => {
    const fetchLocations = async () => {
      try {
        const formattedDate = currentDate.toISOString().split("T")[0];
        const data = await filterFlights({ departureDate: formattedDate }, 0);

        if (data.content) {
          const uniqueSources = [
            ...new Set(data.content.map((flight) => flight.source)),
          ];
          const uniqueDestinations = [
            ...new Set(data.content.map((flight) => flight.destination)),
          ];
          setLocations({ from: uniqueSources, to: uniqueDestinations });
        }
      } catch (error) {
        console.error("Error fetching locations:", error);
      }
    };

    fetchLocations();
  }, [currentDate]);

  useEffect(() => {
    loadFlights(filters, page);
  }, [filters, page]);

  const toggleFilterModal = () => setIsFilterOpen(!isFilterOpen);

  const handleFilterChange = (updatedFilters) => {
    setFilters((prevFilters) => ({
      ...prevFilters,
      ...updatedFilters,
    }));
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
        <button onClick={toggleFilterModal} className="filter-button">
          <Filter className="filter-icon" />
        </button>
      </div>

      <Timeline currentDate={currentDate} onDateChange={setCurrentDate} />

      <FilterModal
        key={modalKey} 
        isOpen={isFilterOpen}
        onClose={toggleFilterModal}
        onApply={handleFilterChange}
        flightStatuses={["Complete", "Incomplete", "Cancelled"]}
        locations={locations}
        filters={filters}
      />

      <div className="flights-list">
        {flights.length > 0 ? (
          flights.map((flight) => (
            <FlightCard
              key={flight.id}
              flight={{
                flightNumber: flight.id,
                status: flight.isCancel ? "Cancelled" : "Scheduled",
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
          ))
        ) : (
          <p className="no-flight-message">
            No flights available for this date.
          </p>
        )}
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
          disabled={!hasMorePages}
        >
          Next
        </button>
      </div>
    </div>
  );
};

export default Flights;
