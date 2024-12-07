import React from "react";
import aboutImg from "../../../assets/about.jpeg"
import "./style.css";
const About = () => {
  return (
    <div className="about-container" id="about">
      <section className="about-text-container">
        <h1 className="about-title">SkyRise</h1>
        <h5 className="about-location">Palestine</h5>
        <p className="about-description">
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed laoreet
          condimentum orci,.Lorem ipsum dolor sit amet, consectetur adipiscing
          elit.orci,.Lorem.
          <br></br>
          Sed laoreet condimentum orci,.Lorem ipsum dolor sit amet, consectetur
          adipiscing elit. Sed laoreet condimentum orci,.
        </p>
      </section>
      <img className="img-container" src={aboutImg}/>
    </div>
  );
};

export default About;
