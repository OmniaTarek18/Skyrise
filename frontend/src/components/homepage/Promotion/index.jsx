import React from "react";
import SearchFlights from "../SearchFlights";
import "./style.css";

const Promotion = ({ img }) => {
  return (
    <div className="promotion-container" id="promotion">
      <section className="promotion-text-container">
        <h1 className="promotion-title">SkyRise</h1>
        <h5 className="location">Palestine</h5>
        <p className="description">
          It is a long established fact that a reader will be distracted by the
          readable content of a page when looking at its layout. The point of
          using Lorem Ipsum is that it has a more-or-less normal distribution of
          letters.
        </p>
        <SearchFlights />
      </section>
    </div>
  );
};

export default Promotion;
