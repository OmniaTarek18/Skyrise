import React from "react";
import Button from "../shared/Button";
import { useNavigate } from "react-router-dom";
import Nav from "../shared/Nav";
import Promotion from "./Promotion";
import promotionImg from "../../assets/promotion.jpeg"
import Footer from "./Footer";
import About from "./About";

const Homepage = () => {
  const navigate = useNavigate();
  return (
    <>
      <Nav />
      <Promotion img ={promotionImg}/>
      <Button
        btnText={"Sign up"}
        btnColor="dark"
        handleClick={() => navigate("/signup")}
      />
      <Button
        btnText={"Log in"}
        btnColor="light"
        handleClick={() => navigate("/login")}
      />
      <About/>
      {/* <Footer/> */}
    </>
  );
};

export default Homepage;
