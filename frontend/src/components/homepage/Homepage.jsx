import React from "react";
import Button from "../shared/Button";
<<<<<<< HEAD
import { useNavigate } from "react-router-dom";


const Homepage = () => {
  const navigate = useNavigate();
=======
import promotionImg from "../../assets/promotion.jpeg"
import { useNavigate } from "react-router-dom";

const Homepage = () => {
  const navigate = useNavigate()
>>>>>>> SCRUM-65-Reset-Password
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
<<<<<<< HEAD
      {/* <About/> */}
      {/* <Footer/> */}
=======
>>>>>>> SCRUM-65-Reset-Password
    </>
  );
};

export default Homepage;
