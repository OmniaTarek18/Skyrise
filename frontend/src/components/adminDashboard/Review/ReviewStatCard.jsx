import React from "react";
import { Star, MessageSquare } from "lucide-react";
import "./reviewstatcard.css";

const StatDisplay = ({ value, label, icon }) => (
  <div className="stat-display">
    <div className="stat-header">
      {icon}
      <span className="stat-label">{label}</span>
    </div>
    <div className="stat-value-container">
      <span className="stat-value">{value}</span>
    </div>
  </div>
);

const RatingDisplay = ({ rating }) => (
  <div className="stat-display">
    <div className="stat-header">
      <Star className="icon filled" />
      <span className="stat-label">Average Rating</span>
    </div>
    <div className="stat-value-container">
      <span className="stat-value">{rating}</span>
      <span className="stat-max">/5</span>
    </div>
  </div>
);

export const ReviewStatCard = ({ totalReviews, averageRating }) => {
  return (
    <div className="stat-card">
      <div className="decorative-blur top" />
      <div className="decorative-blur bottom" />

        <div className="stats-grid">
          <StatDisplay
            value={totalReviews}
            label="Reviews"
            icon={<MessageSquare className="icon" />}
          />
          <RatingDisplay rating={averageRating} />
      </div>
    </div>
  );
};
