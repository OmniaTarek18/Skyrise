import React, { useState, useEffect } from "react";
import Select from "react-select";
import "./searchflight.css";

const SearchFlight = ({ filters, locations, onInputChange, onSearch }) => {
  const [searchFilters, setSearchFilters] = useState({
    source: filters.source || null,
    destination: filters.destination || null,
    departureDate: filters.departureDate || "",
    arrivalDate: filters.arrivalDate || "",
    sortBy: filters.sortBy || null,
    direction: filters.direction || "asc",
    pastFlights: filters.pastFlights || false,
    recentFlights: filters.recentFlights || false,
  });

  useEffect(() => {
    setSearchFilters({
      source: filters.source,
      destination: filters.destination,
      departureDate: filters.departureDate,
      arrivalDate: filters.arrivalDate,
      sortBy: filters.sortBy,
      direction: filters.direction,
      pastFlights: filters.pastFlights,
      recentFlights: filters.recentFlights,
    });
  }, [filters]);

  const handleChange = (key, value) => {
    setSearchFilters((prev) => ({ ...prev, [key]: value }));
    onInputChange(key, value);
  };

  const handleRadioChange = (key) => {
    setSearchFilters((prev) => ({
      ...prev,
      pastFlights: key === "pastFlights",
      recentFlights: key === "recentFlights",
    }));
    onInputChange("pastFlights", key === "pastFlights");
    onInputChange("recentFlights", key === "recentFlights");
  };

  const formatSelectedOption = (value) =>
    value ? { value, label: value } : null;

  return (
    <div className="filters">
      <div className="filter-row">
        <div className="filter-item">
          <label className="filter-label">From</label>
          <Select
            className="select-input"
            options={locations.from}
            isSearchable
            isClearable
            value={formatSelectedOption(searchFilters.source)}
            onChange={(selectedOption) =>
              handleChange("source", selectedOption?.value || null)
            }
            placeholder="Select Source"
          />
        </div>
        <div className="filter-item">
          <label className="filter-label">To</label>
          <Select
            className="select-input"
            options={locations.to}
            isSearchable
            isClearable
            value={formatSelectedOption(searchFilters.destination)}
            onChange={(selectedOption) =>
              handleChange("destination", selectedOption?.value || null)
            }
            placeholder="Select Destination"
          />
        </div>
        <div className="filter-item">
          <label className="filter-label">Departure Date</label>
          <input
            type="date"
            className="datepicker"
            value={searchFilters.departureDate}
            onChange={(e) => handleChange("departureDate", e.target.value)}
          />
        </div>
        <div className="filter-item">
          <label className="filter-label">Arrival Date</label>
          <input
            type="date"
            className="datepicker"
            value={searchFilters.arrivalDate}
            onChange={(e) => handleChange("arrivalDate", e.target.value)}
          />
        </div>
      </div>

      <div className="radio-filters">
        <div className="radio-item">
          <input
            type="radio"
            id="allFlights"
            name="flightType"
            checked={!searchFilters.pastFlights && !searchFilters.recentFlights}
            onChange={() => handleRadioChange("allFlights")}
          />
          <label htmlFor="allFlights">All</label>
        </div>
        <div className="radio-item">
          <input
            type="radio"
            id="pastFlights"
            name="flightType"
            checked={searchFilters.pastFlights}
            onChange={() => handleRadioChange("pastFlights")}
          />
          <label htmlFor="pastFlights">Past Flights</label>
        </div>
        <div className="radio-item">
          <input
            type="radio"
            id="recentFlights"
            name="flightType"
            checked={searchFilters.recentFlights}
            onChange={() => handleRadioChange("recentFlights")}
          />
          <label htmlFor="recentFlights">Recent Flights</label>
        </div>
      </div>

      <button className="search-btn" onClick={onSearch}>
        Search Flights
      </button>
    </div>
  );
};

export default SearchFlight;
