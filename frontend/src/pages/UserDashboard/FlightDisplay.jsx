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
import { getCountriesAndAirportsToTravelAPI } from "../../components/homepage/SearchFlights/api";
import CardButton from "../../components/userdashboard/DisplayAfterSearch/CardButton";

const FlightDisplay = ({ searchDetails }) => {
  const initialFilters = {
    departureCity: "",
    arrivalCity: "",
    numberOfTickets: "",
    departureDate: "",
    arrivalDate: "",
    flightType: "",
    seatClass: "",
    sortby: "",
    pageNumber: 0, // Page number for pagination
  };

  const [airports, setAirports] = useState([]);
  const [filters, setFilters] = useState(initialFilters);
  const [showModal, setShowModal] = useState(false);
  const [flights, setFlights] = useState([]);
  const [loading, setLoading] = useState(false);
  const [hasMorePages, setHasMorePages] = useState(false);
  const [cityOptions, setCityOptions] = useState([]);
  const [totalFlights, setTotalFlights] = useState(0);
  const [isFiltersApplied, setIsFiltersApplied] = useState(false);

  useEffect(() => {
    const fetchAirports = async () => {
      const data = await getCountriesAndAirportsToTravelAPI();
      if (data) {
        setAirports(data);
      }
    };
    fetchAirports();
  }, []);

  const getCityName = (airportId) => {
    const airport = airports.find(
      (airport) => String(airport.id) === String(airportId)
    );
    return airport ? airport.airportCity : "Unknown";
  };

  const departureCity = getCityName(searchDetails.departureAirportId);
  const arrivalCity = getCityName(searchDetails.arrivalAirportId);

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
    const requestBody = {
      arrivalAirportId: parseInt(searchDetails.arrivalAirportId),
      departureAirportId: parseInt(searchDetails.departureAirportId),
      seatClass: searchDetails.seatClass,
      numberOfTickets: searchDetails.numberOfTickets,
      departureDate: searchDetails.departureDate || "",
      sortby: filters.sortby || "",
      flightType: filters.flightType || "DIRECT",
      direction: filters.direction || "",
    };
    console.log("=============================");
    console.log(requestBody);

    // Fetch flight results from the API
    const flightResults = await fetchFlightSearchResults({
      ...requestBody,
      pageNumber: filters.pageNumber, // Include the current pageNumber
    });

    if (flightResults.content && flightResults.content.length > 0) {
      setFlights(flightResults.content);
      setHasMorePages(flightResults.hasMorePages);
      setTotalFlights(flightResults.totalElements);
    } else {
      setFlights([]); // Set flights to empty array if no results
      setHasMorePages(false); // No more pages available if no results
      setTotalFlights(0); // Reset total flights count
    }
  } catch (error) {
    console.error("Error fetching flights:", error);
    setFlights([]); // Ensure flights is empty in case of error
    setHasMorePages(false);
    setTotalFlights(0);
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

  // Clear all filters
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

  // Handle pagination when changing pages
  const handlePageChange = (direction) => {
    setFilters((prev) => ({
      ...prev,
      pageNumber: prev.pageNumber + direction,
    }));
  };

  // Fetch flights when page number changes
  useEffect(() => {
    fetchFlights();
  }, [filters.pageNumber]);

  // Conditionally render round-trip buttons
  const departureDate = searchDetails.departureDate;
  const returnDate = searchDetails.arrivalDate;

  return (
    <div className="flight-display">
      {/* Conditionally render the round-trip buttons */}
      {searchDetails.tripType === "round-trip" && (
        <div className="card-buttons">
          <CardButton
            date={departureDate}
            source={departureCity}
            destination={arrivalCity}
          />
          <CardButton
            date={returnDate}
            source={arrivalCity}
            destination={departureCity}
            isReturn={true}
          />
        </div>
      )}

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
              <div className="modal-row">
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
              <div className="modal-row">
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
              <div className="modal-row">
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
              <div className="modal-row">
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
                  Clear Filters
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
      <div className="flight-list">
        {loading ? (
          <p>Loading...</p>
        ) : (
          <>
            {flights.length === 0 ? (
              // Card showing "No Flights" message
              <div className="no-flights-card">
                <h3>No Flights Available</h3>
                <p>
                  Try adjusting your search filters to find available flights.
                </p>
              </div>
            ) : (
              flights.map((flight) => (
                <UserFlight key={flight.id} flight={flight} />
              ))
            )}
            <div className="pagination">
              <button
                onClick={() => handlePageChange(-1)}
                disabled={filters.pageNumber <= 0}
              >
                Previous
              </button>
              <button
                onClick={() => handlePageChange(1)}
                disabled={!hasMorePages}
              >
                Next
              </button>
            </div>
          </>
        )}
      </div>
    </div>
  );
};

export default FlightDisplay;
