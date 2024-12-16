import React, { useState, useEffect } from "react";
import Select from "react-select";
import {
  PlaneTakeoff,
  PlaneLanding,
  Calendar,
  Users,
  SortAsc,
  RefreshCcw,
  Filter,
  X,
  Route,
  Ticket, 
} from "lucide-react";
import { fetchFlightSearchResults } from "../../api/flightsAfterSearch";
import UserFlight from "../../components/userdashboard/UserFlights/UserFlight";
import "./flightdisplay.css";

const FlightDisplay = () => {
  const initialFilters = {
    departureCity: "",
    arrivalCity: "",
    numberOfTickets: "",
    departureDate: "",
    arrivalDate: "",
    flightType: "",
    seatClass: "",
    sortby: "",
    pageNumber: 0,
  };

  const [filters, setFilters] = useState(initialFilters);
  const [showModal, setShowModal] = useState(false);
  const [flights, setFlights] = useState([]);
  const [loading, setLoading] = useState(false);
  const [hasMorePages, setHasMorePages] = useState(false);
  const [cityOptions, setCityOptions] = useState([]);
  const [totalFlights, setTotalFlights] = useState(0); 
  const [isFiltersApplied, setIsFiltersApplied] = useState(false);

  const seatClassOptions = [
    { value: "ECONOMY", label: "Economy" },
    { value: "BUSINESS", label: "Business" },
  ];

  const flightTypeOptions = [
    { value: "DIRECT", label: "Direct" },
    { value: "INDIRECT", label: "Indirect" },
  ];

  const sortByOptions = [{ value: "price", label: "Price" }];

  const fetchFlights = async () => {
    setLoading(true);
    try {
      const flightResults = await fetchFlightSearchResults(filters);
      setFlights(flightResults.content);
      setHasMorePages(flightResults.hasMorePages);
      setTotalFlights(flightResults.totalElements); 

      const cities = [
        ...new Set([
          ...flightResults.content.map((flight) => flight.source),
          ...flightResults.content.map((flight) => flight.destination),
        ]),
      ];
      setCityOptions(cities.map((city) => ({ value: city, label: city })));
      setIsFiltersApplied(true); 
    } catch (error) {
      console.error("Error fetching flights:", error);
    } finally {
      setLoading(false);
    }
  };

  const handleSelectChange = (option, field) => {
    setFilters((prev) => ({ ...prev, [field]: option ? option.value : "" }));
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFilters((prev) => ({ ...prev, [name]: value }));
  };

  // clear all filters
  const clearFilters = () => {
    setFilters((prev) => ({
      ...prev,
      departureCity: "",
      arrivalCity: "",
      numberOfTickets: "",
      departureDate: "",
      arrivalDate: "",
      flightType: "",
      seatClass: "",
      sortby: "",
    }));
    setIsFiltersApplied(false); 
  };

  const applyFilters = () => {
    fetchFlights();
  };

  // handle pagination when changing pages
  const handlePageChange = (direction) => {
    setFilters((prev) => ({
      ...prev,
      pageNumber: prev.pageNumber + direction,
    }));
  };

  // fetch flights when page number changes
  useEffect(() => {
    fetchFlights();
  }, [filters.pageNumber]);

  return (
    <div className="flight-display">
      <button className="filter-button" onClick={() => setShowModal(true)}>
        <Filter color="#007bff" size={18} /> Filter Flights
      </button>
      {showModal && (
        <div className="modal-overlay">
          <div className="modal-content">
            <button
              className="close-button"
              onClick={() => setShowModal(false)}
            >
              <X size={24} color="#555" />
            </button>
            <h2 className="modal-title">Filter Flights</h2>

            <form>
              <div className="row">
                <div className="form-group">
                  <label>
                    <PlaneTakeoff color="#007bff" /> Source
                  </label>
                  <Select
                    options={cityOptions}
                    placeholder="Select Source"
                    onChange={(option) =>
                      handleSelectChange(option, "departureCity")
                    }
                    isClearable
                    value={
                      filters.departureCity
                        ? {
                            value: filters.departureCity,
                            label: filters.departureCity,
                          }
                        : null
                    }
                  />
                </div>
                <div className="form-group">
                  <label>
                    <PlaneLanding color="#28a745" /> Destination
                  </label>
                  <Select
                    options={cityOptions}
                    placeholder="Select Destination"
                    onChange={(option) =>
                      handleSelectChange(option, "arrivalCity")
                    }
                    isClearable
                    value={
                      filters.arrivalCity
                        ? {
                            value: filters.arrivalCity,
                            label: filters.arrivalCity,
                          }
                        : null
                    }
                  />
                </div>
              </div>
              <div className="row">
                <div className="form-group">
                  <label>
                    <Calendar color="#ff8800" /> Departure Date
                  </label>
                  <input
                    type="date"
                    name="departureDate"
                    value={filters.departureDate}
                    onChange={handleInputChange}
                  />
                </div>
                <div className="form-group">
                  <label>
                    <Calendar color="#ff8800" /> Arrival Date
                  </label>
                  <input
                    type="date"
                    name="arrivalDate"
                    value={filters.arrivalDate}
                    onChange={handleInputChange}
                  />
                </div>
              </div>
              <div className="row">
                <div className="form-group">
                  <label>
                    <Users color="#6610f2" /> Number of Tickets
                  </label>
                  <input
                    type="number"
                    name="numberOfTickets"
                    placeholder="Enter quantity"
                    value={filters.numberOfTickets}
                    min="1"
                    onChange={handleInputChange}
                  />
                </div>
                <div className="form-group">
                  <label>
                    <Route color="#20c997" /> Flight Type
                  </label>
                  <Select
                    options={flightTypeOptions}
                    placeholder="Select Flight Type"
                    value={
                      filters.flightType
                        ? {
                            value: filters.flightType,
                            label: filters.flightType,
                          }
                        : null
                    }
                    onChange={(option) =>
                      handleSelectChange(option, "flightType")
                    }
                  />
                </div>
              </div>
              <div className="row">
                <div className="form-group">
                  <label>
                    <SortAsc color="#fd7e14" /> Sort By
                  </label>
                  <Select
                    options={sortByOptions}
                    placeholder="Sort By"
                    value={
                      filters.sortby
                        ? { value: filters.sortby, label: filters.sortby }
                        : null
                    }
                    onChange={(option) => handleSelectChange(option, "sortby")}
                  />
                </div>
                <div className="form-group">
                  <label>
                    <Ticket color="#fd7e14" /> Seat Class
                  </label>
                  <Select
                    options={seatClassOptions}
                    placeholder="Select Seat Class"
                    value={
                      filters.seatClass
                        ? {
                            value: filters.seatClass,
                            label: filters.seatClass,
                          }
                        : null
                    }
                    onChange={(option) =>
                      handleSelectChange(option, "seatClass")
                    }
                  />
                </div>
              </div>

              <div className="buttons">
                <button
                  type="button"
                  className="apply-button"
                  onClick={applyFilters}
                >
                  {isFiltersApplied || totalFlights > 0
                    ? `Show ${totalFlights} Flights`
                    : "Apply Filters"}
                </button>
                <button
                  type="button"
                  className="clear-button"
                  onClick={clearFilters}
                >
                  <RefreshCcw size={16} /> Clear
                </button>
              </div>
            </form>

            {totalFlights > 0 && (
              <button
                type="button"
                className="list-flights-button"
                onClick={() => setShowModal(false)}
              >
                List {totalFlights} Flights
              </button>
            )}
          </div>
        </div>
      )}

      {/* results */}
      <div className="flight-results">
        {loading ? (
          <p>Loading flights...</p>
        ) : (
          flights.map((flight) => (
            <UserFlight key={flight.id} flight={flight} />
          ))
        )}
      </div>

      {/* pagination */}
      <div className="pagination-controls">
        <button
          onClick={() => handlePageChange(-1)}
          disabled={filters.pageNumber <= 0}
        >
          Previous
        </button>
        <span>Page {filters.pageNumber + 1}</span>
        <button onClick={() => handlePageChange(1)} disabled={!hasMorePages}>
          Next
        </button>
      </div>
    </div>
  );
};

export default FlightDisplay;
