import React from "react";
import Button from "../shared/Button";
import { useNavigate } from "react-router-dom";
import Nav from "../shared/Nav";
import Promotion from "./Promotion";
import promotionImg from "../../assets/promotion.jpeg"
import Footer from "./Footer";
import About from "./About";
import CustomerSay from "./CustomersSay/CustomerSay";

const Homepage = () => {
  const navigate = useNavigate();
  return (
    <>
      <Nav/>
      <Promotion img ={promotionImg}/>
      <CustomerSay/>
      <About/>
      <Footer/>
    </>
  );
};

export default Homepage;
