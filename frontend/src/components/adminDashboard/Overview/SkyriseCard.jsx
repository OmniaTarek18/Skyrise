import React from "react";
import { Plane } from "lucide-react";
import "./skyrisecard.css";
import backgroundImage from "../../../assets/plane.png"; 

export function SkyriseCard() {
  return (
    <div className="skyrise-card-container">
      <div className="skyrise-card-content">
        <div className="icon-container">
          <Plane className="icon" />
        </div>
        <h1 className="skyrise-card-title">SkyRise</h1>
        <p className="skyrise-card-description">
          Elevating Your Journey Through the Skies
        </p>
      </div>

      {/* background */}
      <div className="skyrise-card-background">
        <div
          className="skyrise-card-image"
          style={{ backgroundImage: `url(${backgroundImage})` }}
        ></div>
        <div className="skyrise-card-gradient"></div>
      </div>
      <div className="decorative-line"></div>
    </div>
  );
}
