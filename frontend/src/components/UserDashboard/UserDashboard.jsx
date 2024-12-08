import React from "react";
import Nav from "../shared/Nav";
import useUserAuthenticationStore from "../../store/useUserAuthenticationStore";

const UserDashboard = () => {
  const { id, role, setUserAuthentication } = useUserAuthenticationStore();
  return (
    <>
      <Nav userLoggedIn={true} />
      <main>
        <h1>User Dashboard</h1>
      </main>
    </>
  );
};

export default UserDashboard;
