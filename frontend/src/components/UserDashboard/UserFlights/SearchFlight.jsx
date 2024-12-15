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
  const [searchFilters, setSearchFilters] = useState({
    source: filters.source || null,
    destination: filters.destination || null,
    sourceDate: filters.sourceDate || "",
    destinationDate: filters.destinationDate || "",
    category: filters.category || "all",
  });

  useEffect(() => {
    setSearchFilters({
      source: filters.source,
      destination: filters.destination,
      sourceDate: filters.sourceDate,
      destinationDate: filters.destinationDate,
      category: filters.category,
    });
  }, [filters]);

  const handleChange = (key, value) => {
    setSearchFilters((prev) => ({ ...prev, [key]: value }));
    onInputChange(key, value);
  };

  const handleCategoryChange = (category) => {
    setSearchFilters((prev) => ({ ...prev, category }));
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
            value={searchFilters.source}
            onChange={(selectedOption) =>
              handleChange(
                "source",
                selectedOption ? selectedOption.value : null
              )
            }
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
            value={searchFilters.destination}
            onChange={(selectedOption) =>
              handleChange(
                "destination",
                selectedOption ? selectedOption.value : null
              )
            }
            placeholder="Select Destination"
          />
        </div>
        <div className="filter-item">
          <label className="filter-label">Source Date</label>
          <input
            type="date"
            className="datepicker"
            value={searchFilters.sourceDate}
            onChange={(e) => handleChange("sourceDate", e.target.value)}
          />
        </div>
        <div className="filter-item">
          <label className="filter-label">Destination Date</label>
          <input
            type="date"
            className="datepicker"
            value={searchFilters.destinationDate}
            onChange={(e) => handleChange("destinationDate", e.target.value)}
          />
        </div>
      </div>

      <div className="category-filters">
        <button
          className={`category-button ${
            searchFilters.category === "all" ? "active" : ""
          }`}
          onClick={() => handleCategoryChange("all")}
        >
          All
        </button>
        <button
          className={`category-button ${
            searchFilters.category === "recent" ? "active" : ""
          }`}
          onClick={() => handleCategoryChange("recent")}
        >
          Recent
        </button>
        <button
          className={`category-button ${
            searchFilters.category === "past" ? "active" : ""
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
