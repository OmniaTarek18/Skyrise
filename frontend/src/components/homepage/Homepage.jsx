import React from "react";
import Button from "../shared/Button";
import { useNavigate } from "react-router-dom";
import promotionImg from "../../assets/promotion.jpeg"


const Homepage = () => {
  const navigate = useNavigate();
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
    </>
  );
};

export default Homepage;
