import React, { useState, useEffect } from "react";
import { X, RefreshCw } from "lucide-react";
import Select from "react-select";
import "./filtermodal.css";

const FilterModal = ({
  isOpen,
  onClose,
  onApply,
  filters, 
  flightStatuses = ["Complete", "Incomplete", "Cancelled"],
  locations = { from: [], to: [] },
}) => {
  const [selectedSource, setSelectedSource] = useState(null);
  const [selectedDestination, setSelectedDestination] = useState(null);
  const [selectedStatus, setSelectedStatus] = useState(null);
  const [selectedSorting, setSelectedSorting] = useState(null);
  const [selectedDirection, setSelectedDirection] = useState(null);

 
  useEffect(() => {
    if (filters) {
      setSelectedSource(
        filters.source ? { value: filters.source, label: filters.source } : null
      );
      setSelectedDestination(
        filters.destination
          ? { value: filters.destination, label: filters.destination }
          : null
      );
      setSelectedStatus(
        filters.status ? { value: filters.status, label: filters.status } : null
      );
      setSelectedSorting(
        filters.sortby ? { value: filters.sortby, label: filters.sortby } : null
      );
      setSelectedDirection(filters.direction || null);
    }
  }, [filters]);

  if (!isOpen) return null;

  const formatOptions = (options) =>
    options.map((option) => ({
      value: option,
      label: option,
    }));

  const handleApply = () => {
    onApply({
      source: selectedSource?.value || null,
      destination: selectedDestination?.value || null,
      status: selectedStatus?.value || null,
      sortby: selectedSorting?.value || null,
      direction: selectedDirection,
    });
    onClose();
  };

  const handleClear = () => {
    setSelectedSource(null);
    setSelectedDestination(null);
    setSelectedStatus(null);
    setSelectedSorting(null);
    setSelectedDirection(null);
  };

  const handleSortChange = (option) => setSelectedSorting(option);
  const handleDirectionChange = (direction) =>
    setSelectedDirection(direction === selectedDirection ? null : direction);

  const sortingOptions = [
    {
      label: "Prices",
      options: [
        { value: "economyPrice", label: "Economy Price" },
        { value: "businessPrice", label: "Business Price" },
      ],
    },
    {
      label: "Seats",
      options: [
        { value: "availableEconomySeats", label: "Economy Seats Available" },
        { value: "availableBusinessSeats", label: "Business Seats Available" },
      ],
    },
  ];

  const statusOptions = flightStatuses.map((status) => ({
    value: status.toLowerCase(),
    label: status,
  }));

  return (
    <div className="modal-overlay">
      <div className="modal-container">
        <div className="modal-header">
          <h2 className="modal-title">Filter Flights</h2>
          <button onClick={onClose} className="close-button">
            <X className="icon" />
          </button>
        </div>

        <div className="modal-content">
          <div className="source-destination-select">
            <div>
              <label className="filter-label">From</label>
              <Select
                className="select-input"
                options={formatOptions(locations.from)}
                isSearchable
                isClearable
                value={selectedSource}
                onChange={(option) => setSelectedSource(option)}
                placeholder="Select Source"
              />
            </div>
            <div>
              <label className="filter-label">To</label>
              <Select
                className="select-input"
                options={formatOptions(locations.to)}
                isSearchable
                isClearable
                value={selectedDestination}
                onChange={(option) => setSelectedDestination(option)}
                placeholder="Select Destination"
              />
            </div>
          </div>

          <div className="status-sortby">
            <div className="status">
              <label className="filter-label">Flight Status</label>
              <Select
                className="select-input"
                options={[{ value: null, label: "All" }, ...statusOptions]}
                isClearable
                value={selectedStatus}
                onChange={(option) => setSelectedStatus(option)}
                placeholder="Select Status"
              />
            </div>
            <div className="sort-by">
              <label className="filter-label">Sort By</label>
              <Select
                className="select-input"
                options={[{ value: null, label: "All" }, ...sortingOptions]}
                isClearable
                value={selectedSorting}
                onChange={handleSortChange}
                placeholder="Sorting Option"
              />
            </div>
          </div>

          <div className="sort-direction">
            <label className="filter-label">Sort Direction</label>
            <div className="direction-buttons">
              <button
                className={`direction-button ${
                  selectedDirection === "asc" ? "active" : ""
                }`}
                onClick={() => handleDirectionChange("asc")}
              >
                Ascending
              </button>
              <button
                className={`direction-button ${
                  selectedDirection === "desc" ? "active" : ""
                }`}
                onClick={() => handleDirectionChange("desc")}
              >
                Descending
              </button>
            </div>
          </div>
        </div>

        <div className="modal-footer">
          <button className="button clear-button" onClick={handleClear}>
            <RefreshCw className="icon" />
          </button>
          <button className="button cancel-button" onClick={onClose}>
            Cancel
          </button>
          <button className="button apply-button" onClick={handleApply}>
            Apply
          </button>
        </div>
      </div>
    </div>
  );
};

export default FilterModal;
