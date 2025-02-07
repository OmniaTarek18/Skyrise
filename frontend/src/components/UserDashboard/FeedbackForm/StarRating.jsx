import React, { useState } from "react";
import { Star } from "lucide-react";
import "./StarRating.css";

const StarRating = (
  { rating, onRate }
) => {
  const [hoverRating, setHoverRating] = useState(0);
  const [isAnimating, setIsAnimating] = useState(false);
  const handleStarClick = (selectedRating) => {
    setIsAnimating(true);
    onRate?.(selectedRating);
    setTimeout(() => setIsAnimating(false), 700);
  };
  return (
    <div className="star-rating-container">
      <div className="stars-wrapper">
        {[...Array(5)].map((_, index) => {
          const starValue = index + 1;
          const isActive = starValue <= (hoverRating || rating);
          return (
            <button
              key={index}
              type="button"
              className={`star-btn ${isActive ? "active" : ""} ${
                isAnimating && starValue <= rating ? "pulse" : ""
              }`}
              onMouseEnter={() => setHoverRating(starValue)}
              onMouseLeave={() => setHoverRating(0)}
              onClick={() => handleStarClick(starValue)}
            >
              <Star className="star-icon" />
            </button>
          );
        })}
      </div>
    </div>
  );
};
export default StarRating;