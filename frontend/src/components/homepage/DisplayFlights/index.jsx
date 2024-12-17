import React from "react";
import Nav from "../../shared/Nav";
import useUserAuthenticationStore from "../../../store/useUserAuthenticationStore";
import useSearchFlightDetails from "../../../store/useSearchFlightDetails";
import FlightDisplay from "../../../pages/UserDashboard/FlightDisplay";

const DisplayFlights = ({ values }) => {
  const { id, role, setUserAuthentication } = useUserAuthenticationStore();
  const {
    searchFlightDetails,
    setSearchFlightDetails,
  } = useSearchFlightDetails();
  console.log(searchFlightDetails)
  return (
    <div>
      {id != null ? <Nav userLoggedIn={true} /> : <Nav />}
      <FlightDisplay searchDetails={searchFlightDetails} />
    </div>
  );
};

export default DisplayFlights;
