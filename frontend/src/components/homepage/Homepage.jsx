import React from "react";
import Button from "../shared/Button";
import promotionImg from "../../assets/promotion.jpeg"
import { useNavigate } from "react-router-dom";

const Homepage = () => {
  const navigate = useNavigate()
  return (
    <>
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
      {/* <About/> */}
      {/* <Footer/> */}
    </>
  );
};

export default Homepage;
