import React, { useState, useEffect } from "react";
import { Filter } from "lucide-react";
import { fetchFlightSearchResults } from "../../api/flightsAfterSearch";
import UserFlight from "../../components/userdashboard/UserFlights/UserFlight";
import "./flightdisplay.css";
import { getCountriesAndAirportsToTravelAPI } from "../../components/homepage/SearchFlights/api";
import CardButton from "../../components/userdashboard/DisplayAfterSearch/CardButton";
import FilterModal from "../../components/userdashboard/DisplayAfterSearch/FilterModal";
import { Timeline } from "../../components/adminDashboard/Flights/Timeline";
import { fetchFlightDetails } from "../../api/flightsAfterSearch";
import DetailsPopup from "../../components/userdashboard/DisplayAfterSearch/DetailsPopup";
const FlightDisplay = ({ searchDetails }) => {
  const initialFilters = {
    arrivalAirportId: null,
    departureAirportId: null,
    numberOfTickets: null,
    departureDate: null,
    arrivalDate: null,
    flightType: null,
    seatClass: null,
    sortby: null,
  };

  const [pageNumber, setPageNumber] = useState(0);
  const [airports, setAirports] = useState([]);
  const [filters, setFilters] = useState(initialFilters);
  const [showModal, setShowModal] = useState(false);
  const [flights, setFlights] = useState([]);
  const [loading, setLoading] = useState(false);
  const [hasMorePages, setHasMorePages] = useState(false);
  const [locations, setLocationsOptions] = useState([]);
  const [totalFlights, setTotalFlights] = useState(0);
  const [, setIsFiltersApplied] = useState(false);
  const [location, setCardButtonLocation] = useState({
    source: searchDetails.departureAirportId,
    destination: searchDetails.arrivalAirportId,
  });
  const [isFiltersChanged, setIsFiltersChanged] = useState(false);
  const [currentDate, setCurrentDate] = useState(
    new Date(searchDetails.departureDate || new Date())
  );
  const [flightDetails, setFlightDetails] = useState(null);
  const [isPopupOpen, setIsPopupOpen] = useState(false);

  useEffect(() => {
    const fetchAirports = async () => {
      const data = await getCountriesAndAirportsToTravelAPI();
      if (data) {
        setAirports(data);
        const locationOptions = data.map((airport) => ({
          label: `${airport.airportCountry}, ${airport.airportCity}`,
          value: airport.id,
        }));
        setLocationsOptions(locationOptions);
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

  const getAirportIdByCityName = (cityName) => {
    const airport = airports.find(
      (airport) => airport.airportCity === cityName
    );
    return airport ? airport.id : null;
  };

  const handleCardButtonClick = (date, source, destination) => {
    const departureAirportId = getAirportIdByCityName(source);
    const arrivalAirportId = getAirportIdByCityName(destination);

    if (departureAirportId && arrivalAirportId) {
      console.log(filters.departureAirportId, "kkkkkkkk");
      filters.departureDate = date;
      filters.arrivalAirportId = arrivalAirportId;
      filters.departureAirportId = departureAirportId;
      console.log(filters.departureAirportId, "kkkkkkkk");
      setCurrentDate(new Date(date)); 
      setPageNumber(0); 
    }
  };

  const handlePageChange = (direction) => {
    setPageNumber((prevPage) => {
      const newPage = prevPage + direction;
      const totalPages = Math.ceil(totalFlights / 10); 
      if (newPage >= 0 && newPage < totalPages) {
        return newPage;
      }
      return prevPage;
    });
  };

  useEffect(() => {
    fetchFlights();
  }, [currentDate, pageNumber]);

  const fetchFlights = async () => {
    setLoading(true);
    try {
      const requestBody = {
        arrivalAirportId:
          parseInt(filters.arrivalAirportId) ||
          parseInt(searchDetails.arrivalAirportId),
        departureAirportId:
          parseInt(filters.departureAirportId) ||
          parseInt(searchDetails.departureAirportId),
        seatClass: filters.seatClass || searchDetails.seatClass,
        numberOfTickets:
          filters.numberOfTickets || searchDetails.numberOfTickets,
        departureDate: currentDate.toISOString().split("T")[0],
        sortby: filters.sortby || "price",
        flightType: filters.flightType || null,
        direction: filters.direction || "asc",
      };

      const flightResults = await fetchFlightSearchResults(
        { ...requestBody },
        pageNumber 
      );

      if (flightResults.content && flightResults.content.length > 0) {
        setFlights(flightResults.content);
        const totalPages = flightResults.totalPages;
        setHasMorePages(pageNumber + 1 < totalPages);
        setTotalFlights(flightResults.totalElements);
      } else {
        setFlights([]);
        setHasMorePages(false);
        setTotalFlights(0);
      }
    } catch (error) {
      console.error("Error fetching flights:", error);
      setFlights([]);
      setHasMorePages(false);
      setTotalFlights(0);
    } finally {
      setLoading(false);
    }
  };

  const handleSelectChange = (option, field) => {
    setFilters((prev) => ({ ...prev, [field]: option ? option.value : "" }));
    setIsFiltersChanged(true);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFilters((prev) => ({ ...prev, [name]: value }));
    setIsFiltersChanged(true);
  };

  const clearFilters = () => {
    setFilters(initialFilters);
    setIsFiltersChanged(true);
  };

  const applyFilters = (closeModal = false) => {
    fetchFlights();
    setPageNumber(0);
    setIsFiltersChanged(false); 
    setIsFiltersApplied(true); 
    setCardButtonLocation({
      source: filters.departureAirportId || searchDetails.departureAirportId,
      destination: filters.arrivalAirportId || searchDetails.arrivalAirportId,
    });
    if (closeModal) {
      setShowModal(false); 
    }
  };

  useEffect(() => {
    fetchFlights();
  }, [currentDate, pageNumber]);

  const handleDateChange = (newDate) => {
    setCurrentDate(newDate);
    setPageNumber(0); 
  };
    const onShowDetails = async (flightId) => {
      try {
        const details = await fetchFlightDetails(flightId);
        console.log(details,"pppppppppppeeeeeeeeeeeeeeeeeeeee")
        setFlightDetails(details);
        setIsPopupOpen(true); 
      } catch (error) {
        console.error("Error fetching flight details:", error);
      }
    };
  const departureDate = searchDetails.departureDate;
  const returnDate = searchDetails.arrivalDate;

  return (
    <div className="flight-display">
      {/* cardbuttons */}
      {searchDetails.tripType === "round-trip" && (
        <div className="card-buttons">
          <CardButton
            date={departureDate}
            source={getCityName(location.source)}
            destination={getCityName(location.destination)}
            onClick={handleCardButtonClick} 
          />
          <CardButton
            date={returnDate}
            source={getCityName(location.destination)}
            destination={getCityName(location.source)}
            isReturn={true}
            onClick={handleCardButtonClick} 
          />
        </div>
      )}
      {/* timeline */}
      <div className="departure-date">
        <Timeline currentDate={currentDate} onDateChange={handleDateChange} />

        {/* icon of modal */}
        <button
          className="after-search-filter-button"
          onClick={() => setShowModal(true)}
        >
          <Filter color="white" size={18} />
        </button>
      </div>
      {/* filtermodal */}
      <FilterModal
        showModal={showModal}
        onClose={() => setShowModal(false)}
        filters={filters}
        locations={locations}
        flightTypeOptions={[
          { value: "DIRECT", label: "Direct" },
          { value: "INDIRECT", label: "Indirect" },
        ]}
        seatClassOptions={[
          { value: "ECONOMY", label: "Economy" },
          { value: "BUSINESS", label: "Business" },
        ]}
        sortByOptions={[{ value: "price", label: "Price" }]}
        totalFlights={totalFlights}
        isFiltersChanged={isFiltersChanged}
        handleSelectChange={handleSelectChange}
        handleInputChange={handleInputChange}
        clearFilters={clearFilters}
        applyFilters={applyFilters}
      />
      <div className="after-search-flight-list">
        {loading ? (
          <p>Loading...</p>
        ) : (
          <>
            {flights.length === 0 ? (
              <div className="no-flights-card">
                <h3>No Flights Available</h3>
                <p>
                  Try adjusting your search filters to find available flights.
                </p>
              </div>
            ) : (
              flights.map((flight) => (
                <UserFlight
                  key={flight.id}
                  flight={flight}
                  departureCity={getCityName(filters.departureAirportId)}
                  arrivalCity={getCityName(filters.arrivalAirportId)}
                  onShowDetails={onShowDetails}
                />
              ))
            )}
          </>
        )}
      </div>
      {/* pagination */}
      <div className="pagination-controls">
        <button
          className="pagination-button"
          onClick={() => handlePageChange(-1)}
          disabled={pageNumber <= 0}
        >
          Previous
        </button>
        <button
          className="pagination-button"
          onClick={() => handlePageChange(1)}
          disabled={!hasMorePages}
        >
          Next
        </button>
      </div>
      {/* legs*/}
      <DetailsPopup
        isOpen={isPopupOpen}
        onClose={() => setIsPopupOpen(false)}
        legs={flightDetails} 
      />
    </div>
  );
};

export default FlightDisplay;
