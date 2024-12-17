import React from "react";
import Select from "react-select";
import {
  X,
  PlaneTakeoff,
  PlaneLanding,
  Users,
  Route,
  SortAsc,
  Ticket,
} from "lucide-react";

const FilterModal = ({
  showModal,
  onClose,
  filters,
  locations,
  flightTypeOptions,
  seatClassOptions,
  sortByOptions,
  totalFlights,
  isFiltersChanged,
  handleSelectChange,
  handleInputChange,
  clearFilters,
  applyFilters,
}) => {
  return (
    <>
      {showModal && (
        <div className="modal-overlay">
          <div className="modal-content">
            <button className="close-button" onClick={onClose}>
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
                    options={locations}
                    placeholder="Select Source"
                    onChange={(option) =>
                      handleSelectChange(option, "departureAirportId")
                    }
                    isClearable
                    value={
                      filters.departureAirportId
                        ? locations.find(
                            (loc) => loc.value === filters.departureAirportId
                          )
                        : null
                    }
                  />
                </div>
                <div className="form-group">
                  <label>
                    <PlaneLanding color="#28a745" /> Destination
                  </label>
                  <Select
                    options={locations}
                    placeholder="Select Destination"
                    onChange={(option) =>
                      handleSelectChange(option, "arrivalAirportId")
                    }
                    isClearable
                    value={
                      filters.arrivalAirportId
                        ? locations.find(
                            (loc) => loc.value === filters.arrivalAirportId
                          )
                        : null
                    }
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
                    isClearable
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
                    isClearable
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
                    isClearable
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
                  onClick={() => {
                    if (isFiltersChanged) {
                      applyFilters();
                    } else {
                      applyFilters(true);
                    }
                  }}
                >
                  {isFiltersChanged ? "Apply" : `Show ${totalFlights} Flights`}
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
    </>
  );
};

export default FilterModal;
