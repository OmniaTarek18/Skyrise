import React from "react";
import "./style.css";

const Section = ({ heading, headingText = false }) => {
  return (
    <section>
      <h1 className="heading">{heading}</h1>
      {headingText && <p className="heading-text">{headingText}</p>}
    </section>
  );
};

export default Section;
