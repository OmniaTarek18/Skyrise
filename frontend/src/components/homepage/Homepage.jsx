import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Nav from "../shared/Nav";
import Promotion from "./Promotion";
import promotionImg from "../../assets/promotion.jpeg";
import Footer from "./Footer";
import About from "./About";
import CustomerSay from "./CustomersSay/CustomerSay";
import useUserAuthenticationStore from "../../store/useUserAuthenticationStore";
import AdminDashboard from "../../pages/AdminDashboard/AdminDashboard";

const Homepage = () => {
  const navigate = useNavigate();
  const { id, role, setUserAuthentication } = useUserAuthenticationStore();
  useEffect(() => {
    if (role === "ADMIN") navigate("/admin-dashboard");
  }, [role]);
  return (
    <>
      {role == "USER" && (
        <>
          {id != null ? <Nav userLoggedIn={true} /> : <Nav />}
          <Promotion img={promotionImg} />
          <CustomerSay />
          <About />
          <Footer />
        </>
      )}
    </>
  );
};

export default Homepage;
