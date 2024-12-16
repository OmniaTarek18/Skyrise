import React from "react";
import Nav from "../../shared/Nav";
import useUserAuthenticationStore from "../../../store/useUserAuthenticationStore";
import useSearchFlightDetails from "../../../store/useSearchFlightDetails";

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
      <h1>display flights</h1>
    </div>
  );
};

export default DisplayFlights;
