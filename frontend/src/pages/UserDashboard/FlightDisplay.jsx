import React, { useState, useEffect } from "react";
import Select from "react-select";
import { FaFilter, FaSyncAlt } from "react-icons/fa";
import {
  fetchFlightSearchResults,
  fetchFlightDetails,
} from "../../api/flightsAfterSearch";
import UserFlight from "../../components/userdashboard/UserFlights/UserFlight";
import "./flightdisplay.css";

const FlightDisplay = () => {
  const [flights, setFlights] = useState([]);
  const [filters, setFilters] = useState({
    departureCity: "",
    arrivalCity: "",
    seatClass: "",
    numberOfTickets: "",
    departureDate: "",
    sortby: "",
    flightType: "",
    direction: "",
    pageNumber: 0,
  });
  const [loading, setLoading] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const [cityOptions, setCityOptions] = useState([]);
  const [hasMorePages, setHasMorePages] = useState(false);

  const seatClassOptions = [
    { value: "ECONOMY", label: "Economy" },
    { value: "BUSINESS", label: "Business" },
  ];

  const flightTypeOptions = [
    { value: "DIRECT", label: "Direct" },
    { value: "INDIRECT", label: "Indirect" },
  ];

  const sortByOptions = [{ value: "price", label: "Price" }];

  const directionOptions = [
    { value: "asc", label: "Ascending" },
    { value: "desc", label: "Descending" },
  ];

  // fetch all flights on initial page load
  const fetchFlights = async () => {
    setLoading(true);
    try {
      const flightResults = await fetchFlightSearchResults(filters);
      setFlights(flightResults.content);
      setHasMorePages(flightResults.hasMorePages);

      // populate city options based on available fetched flight data
      const cities = [
        ...new Set([
          ...flightResults.content.map((flight) => flight.source),
          ...flightResults.content.map((flight) => flight.destination),
        ]),
      ];
      setCityOptions(
        cities.map((city) => ({
          value: city,
          label: city,
        }))
      );
    } catch (error) {
      console.error("error fetching flights:", error);
    } finally {
      setLoading(false);
    }
  };

  // fetch filtered flights based on selected filters
  const fetchFilteredFlights = async () => {
    setLoading(true);
    try {
      const flightResults = await fetchFlightSearchResults(filters);
      setFlights(flightResults.content);
      setHasMorePages(flightResults.hasMorePages);
    } catch (error) {
      console.error("error fetching the fligts:", error);
    } finally {
      setLoading(false);
    }
  };

  // fetch flight details when a flight card details button is clicked
  const fetchFlightDetailsHandler = async (flightId) => {
    try {
      const flightDetails = await fetchFlightDetails(flightId);
      console.log("flight Details:", flightDetails);
    } catch (error) {
      console.error("error fetching flight details:", error);
    }
  };

  // source, destination, seat class
  const handleSelectChange = (selectedOption, actionMeta) => {
    setFilters((prevFilters) => ({
      ...prevFilters,
      [actionMeta.name]: selectedOption ? selectedOption.value : "",
    }));
  };

  // number of tickets, departure date
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFilters((prevFilters) => ({
      ...prevFilters,
      [name]: value,
    }));
  };

  // clear all filters
  const clearFilters = () => {
    setFilters({
      departureCity: "",
      arrivalCity: "",
      seatClass: "",
      numberOfTickets: "",
      departureDate: "",
      sortby: "",
      flightType: "",
      direction: "",
      pageNumber: 0,
    });
  };

  const handlePageChange = (direction) => {
    setFilters((prevFilters) => ({
      ...prevFilters,
      pageNumber: prevFilters.pageNumber + direction,
    }));
  };

  // fetch flights when the page number changes
  useEffect(() => {
    fetchFlights();
  }, [filters.pageNumber]);

  return (
    <div className="flight-display">
      <button className="filter-button" onClick={() => setShowModal(true)}>
        <FaFilter size={18} />
      </button>

      {showModal && (
        <div className="filter-modal">
          <div className="filter-modal-content">
            <h2>Filter Flights</h2>
            <form>
              <div className="filter-row">
                <div className="filter-item">
                  <label htmlFor="departureCity">Departure City:</label>
                  <Select
                    id="departureCity"
                    name="departureCity"
                    options={cityOptions}
                    onChange={(selectedOption) =>
                      handleSelectChange(selectedOption, {
                        name: "departureCity",
                      })
                    }
                    isClearable
                    placeholder="Select departure city"
                  />
                </div>
                <div className="filter-item">
                  <label htmlFor="arrivalCity">Arrival City:</label>
                  <Select
                    id="arrivalCity"
                    name="arrivalCity"
                    options={cityOptions}
                    onChange={(selectedOption) =>
                      handleSelectChange(selectedOption, {
                        name: "arrivalCity",
                      })
                    }
                    isClearable
                    placeholder="Select arrival city"
                  />
                </div>
              </div>

              <div className="filter-row">
                <div className="filter-item">
                  <label htmlFor="seatClass">Seat Class:</label>
                  <Select
                    id="seatClass"
                    name="seatClass"
                    options={seatClassOptions}
                    value={seatClassOptions.find(
                      (option) => option.value === filters.seatClass
                    )}
                    onChange={handleSelectChange}
                    isClearable
                    placeholder="Select seat class"
                  />
                </div>
                <div className="filter-item">
                  <label htmlFor="numberOfTickets">Number of Tickets:</label>
                  <input
                    type="number"
                    id="numberOfTickets"
                    name="numberOfTickets"
                    min="1"
                    value={filters.numberOfTickets}
                    onChange={handleInputChange}
                  />
                </div>
              </div>

              <div className="filter-row">
                <div className="filter-item">
                  <label htmlFor="departureDate">Departure Date:</label>
                  <input
                    type="date"
                    id="departureDate"
                    name="departureDate"
                    value={filters.departureDate}
                    onChange={handleInputChange}
                  />
                </div>
                <div className="filter-item">
                  <label htmlFor="sortby">Sort By:</label>
                  <Select
                    id="sortby"
                    name="sortby"
                    options={sortByOptions}
                    value={sortByOptions.find(
                      (option) => option.value === filters.sortby
                    )}
                    onChange={handleSelectChange}
                    isClearable
                    placeholder="Select sort criteria"
                  />
                </div>
              </div>

              <div className="filter-row">
                <div className="filter-item">
                  <label htmlFor="flightType">Flight Type:</label>
                  <Select
                    id="flightType"
                    name="flightType"
                    options={flightTypeOptions}
                    value={flightTypeOptions.find(
                      (option) => option.value === filters.flightType
                    )}
                    onChange={handleSelectChange}
                    isClearable
                    placeholder="Select flight type"
                  />
                </div>
                <div className="filter-item">
                  <label htmlFor="direction">Direction:</label>
                  <Select
                    id="direction"
                    name="direction"
                    options={directionOptions}
                    value={directionOptions.find(
                      (option) => option.value === filters.direction
                    )}
                    onChange={handleSelectChange}
                    isClearable
                    placeholder="Select direction"
                  />
                </div>
              </div>

              <div className="filter-buttons-row">
                <button
                  type="button"
                  className="apply-filters"
                  onClick={() => {
                    fetchFilteredFlights(); // call the function to fetch filtered flights when  apply button is clicked
                    setShowModal(false);
                  }}
                >
                  Apply
                </button>
                <button
                  type="button"
                  className="close-modal"
                  onClick={() => setShowModal(false)}
                >
                  Close
                </button>
                <button
                  type="button"
                  className="clear-filters"
                  onClick={clearFilters}
                >
                  <FaSyncAlt size={14} />
                </button>
              </div>
            </form>
          </div>
        </div>
      )}

      <div className="flight-results">
        {loading ? (
          <p>Loading flights...</p>
        ) : (
          flights.map((flight) => (
            <UserFlight
              key={flight.id}
              flight={{
                departureTime: flight.departureTime,
                source: flight.source,
                departureDate: flight.departureDate,
                arrivalTime: flight.arrivalTime,
                destination: flight.destination,
                arrivalDate: flight.arrivalDate,
                price: flight.economyPrice,
              }}
              // to do put the flight leg details on dedicated cards
              onShowDetails={fetchFlightDetailsHandler} // pass handler to show flight legs
            />
          ))
        )}
      </div>

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
