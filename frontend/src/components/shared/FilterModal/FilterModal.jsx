import React, { useState } from "react";
import { X } from "lucide-react";
import Select from "react-select";
import "./filtermodal.css";

const FilterModal = ({
  isOpen,
  onClose,
  flightStatuses = ["Complete", "Incomplete"],
  locations = { from: ["Select departure city"], to: ["Select arrival city"] },
}) => {
  const [activeStatus, setActiveStatus] = useState("");

  if (!isOpen) return null;

  const handleStatusClick = (status) => {
    setActiveStatus((prevStatus) => (prevStatus === status ? null : status));
  };


  const formatOptions = (options) =>
    options.map((option) => ({
      value: option,
      label: option,
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
          {/* locations */}
          <div className="filter-group">
            <div>
              <label className="filter-label">From</label>
              <Select
                className="select-input"
                options={formatOptions(locations.from)}
                isSearchable={true} 
              />
            </div>
            <div>
              <label className="filter-label">To</label>
              <Select
                className="select-input"
                options={formatOptions(locations.to)}
                isSearchable={true} 
              />
            </div>
          </div>

          {/* flight status */}
          <div className="filter-group">
            <label className="filter-label">Flight Status</label>
            <div className="status-buttons">
              {flightStatuses.map((status, index) => (
                <button
                  key={index}
                  className={`status-button ${
                    activeStatus === status ? "active" : ""
                  }`}
                  onClick={() => handleStatusClick(status)}
                >
                  {status}
                </button>
              ))}
            </div>
          </div>
        </div>

        <div className="modal-footer">
          <button onClick={onClose} className="cancel-button">
            Cancel
          </button>
          <button onClick={onClose} className="apply-button">
            Apply
          </button>
        </div>
      </div>
    </div>
  );
};

export default FilterModal;
