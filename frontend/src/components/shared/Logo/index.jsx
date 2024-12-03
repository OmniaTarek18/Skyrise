import React from "react";
import WhiteLogo from "../../../assets/blueLogo.svg"
import BlueLogo from "../../../assets/blueLogo.svg"
import "./style.css"

const Logo = ({color = BlueLogo}) => {
  return (
    <>
      <img src={color} alt="The company logo" className="logo" />
    </>
  );
};

export default Logo;
