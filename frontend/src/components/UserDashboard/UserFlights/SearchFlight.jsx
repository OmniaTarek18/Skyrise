import React, { useState, useEffect } from "react";
import Select from "react-select";
import "./searchflight.css";

const SearchFlight = ({
  filters,
  locations,
  onInputChange,
  onSearch,
  onFilterCategory,
}) => {
  const [selectedSource, setSelectedSource] = useState(null);
  const [selectedDestination, setSelectedDestination] = useState(null);
  const [selectedSourceDate, setSelectedSourceDate] = useState(
    filters.sourceDate || ""
  );
  const [selectedDestinationDate, setSelectedDestinationDate] = useState(
    filters.destinationDate || ""
  );
  const [selectedCategory, setSelectedCategory] = useState(filters.category);

  useEffect(() => {
    setSelectedSource(filters.source);
    setSelectedDestination(filters.destination);
    setSelectedSourceDate(filters.sourceDate);
    setSelectedDestinationDate(filters.destinationDate);
    setSelectedCategory(filters.category);
  }, [filters]);

  const handleSourceChange = (selectedOption) => {
    setSelectedSource(selectedOption);
    onInputChange("source", selectedOption ? selectedOption.value : null);
  };

  const handleDestinationChange = (selectedOption) => {
    setSelectedDestination(selectedOption);
    onInputChange("destination", selectedOption ? selectedOption.value : null);
  };

  const handleSourceDateChange = (e) => {
    setSelectedSourceDate(e.target.value);
    onInputChange("sourceDate", e.target.value);
  };

  const handleDestinationDateChange = (e) => {
    setSelectedDestinationDate(e.target.value);
    onInputChange("destinationDate", e.target.value);
  };

  const handleCategoryChange = (category) => {
    setSelectedCategory(category);
    onFilterCategory(category);
  };

  const handleSearch = () => {
    onSearch();
  };

  const formatOptions = (options) =>
    options.map((option) => ({
      value: option,
      label: option,
    }));

  return (
    <div className="filters">
      <div className="filter-row">
        <div className="filter-item">
          <label className="filter-label">From</label>
          <Select
            className="select-input"
            options={formatOptions(locations.from)}
            isSearchable
            isClearable
            value={selectedSource}
            onChange={handleSourceChange}
            placeholder="Select Source"
          />
        </div>
        <div className="filter-item">
          <label className="filter-label">To</label>
          <Select
            className="select-input"
            options={formatOptions(locations.to)}
            isSearchable
            isClearable
            value={selectedDestination}
            onChange={handleDestinationChange}
            placeholder="Select Destination"
          />
        </div>
        <div className="filter-item">
          <label className="filter-label">Source Date</label>
          <input
            type="date"
            className="datepicker"
            value={selectedSourceDate}
            onChange={handleSourceDateChange}
          />
        </div>
        <div className="filter-item">
          <label className="filter-label">Destination Date</label>
          <input
            type="date"
            className="datepicker"
            value={selectedDestinationDate}
            onChange={handleDestinationDateChange}
          />
        </div>
      </div>

      <div className="category-filters">
        <button
          className={`category-button ${
            selectedCategory === "all" ? "active" : ""
          }`}
          onClick={() => handleCategoryChange("all")}
        >
          All
        </button>
        <button
          className={`category-button ${
            selectedCategory === "recent" ? "active" : ""
          }`}
          onClick={() => handleCategoryChange("recent")}
        >
          Recent
        </button>
        <button
          className={`category-button ${
            selectedCategory === "past" ? "active" : ""
          }`}
          onClick={() => handleCategoryChange("past")}
        >
          Past
        </button>
      </div>

      <button className="search-btn" onClick={handleSearch}>
        Search Flights
      </button>
    </div>
  );
};

export default SearchFlight;
