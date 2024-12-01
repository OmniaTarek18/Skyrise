import React, { useState } from "react";
import { Star, Filter, X, StarIcon } from "lucide-react";
import "./feedback.css";

const Feedback = () => {
  // mock data for feedback
  const feedback = [
    {
      id: 1,
      starRating: 4,
      punctuality: "Excellent",
      comfort: "Fair",
      service: "Good",
      foodAndBeverage: "Fair",
      cleanliness: "Excellent",
      comments: "Great experience.",
      createdAt: "2024-11-15",
    },
    {
      id: 2,
      starRating: 5,
      punctuality: "Excellent",
      comfort: "Excellent",
      service: "Excellent",
      foodAndBeverage: "Good",
      cleanliness: "Excellent",
      comments: "Would fly again.",
      createdAt: "2024-11-12",
    },
  ];

  // dummy values for averageRating
  const averageRating = 4.5;
  const totalReviews = feedback.length;

  // initial state of the filters
  const [starFilter, setStarFilter] = useState(null);
  const [categoryFilter, setCategoryFilter] = useState("");

  return (
    <div className="feedback-container">
      <h1 className="page-title">Dashboard Overview</h1>

      {/* average rating and total no of reviews */}
      <div className="feedback-stats">
        <div className="feedback-stat">
          <h2>Average Rating</h2>
          <div className="feedback-rating">
            <span>{averageRating.toFixed(1)}</span>
            <StarIcon size={20} className="rating-star filled" fill="gold" />
          </div>
        </div>
        <div className="feedback-stat">
          <h2>Total Reviews</h2>
          <span className="total-reviews">{totalReviews}</span>
        </div>
      </div>

      {/* filters section */}
      <div className="feedback-filters">
        <div className="filter-group">
          <h2 className="filter-header">Filter by Star Rating</h2>
          <div className="filter-buttons">
            {[1, 2, 3, 4, 5].map((rating) => (
              <button
                key={rating}
                onClick={() =>
                  setStarFilter(starFilter === rating ? null : rating)
                }
                className={`filter-btn ${
                  starFilter === rating ? "active" : ""
                }`}
              >
                {rating} <Star className="inline-icon" size={16} />
              </button>
            ))}
            <button onClick={() => setStarFilter(null)} className="filter-btn">
              <Filter size={16} />
              <span>Clear</span>
            </button>
          </div>
        </div>

        <div className="filter-group">
          <h2 className="filter-header">Filter by Performance</h2>
          <select
            className="filter-dropdown"
            onChange={(e) => setCategoryFilter(e.target.value)}
            value={categoryFilter}
          >
            <option value="">All Categories</option>
            <option value="punctuality">Punctuality</option>
            <option value="comfort">Comfort</option>
            <option value="service">Service</option>
            <option value="foodAndBeverage">Food & Beverage</option>
            <option value="cleanliness">Cleanliness</option>
          </select>
        </div>
      </div>

      {/* feedback list */}
      <div className="feedback-list">
        {feedback.map((item) => (
          <div key={item.id} className="feedback-card">
            <div className="feedback-header">
              <div className="feedback-stars">
                {Array.from({ length: 5 }).map((_, index) => (
                  <Star
                    key={index}
                    size={20}
                    className={index < item.starRating ? "filled" : "empty"}
                    fill="currentColor"
                  />
                ))}
              </div>
              <p className="feedback-date">
                {new Date(item.createdAt).toLocaleDateString()}
              </p>
            </div>
            <p className="feedback-comment">{item.comments}</p>
            <div className="feedback-categories">
              <div>
                <strong>Punctuality:</strong> {item.punctuality}
              </div>
              <div>
                <strong>Comfort:</strong> {item.comfort}
              </div>
              <div>
                <strong>Service:</strong> {item.service}
              </div>
              <div>
                <strong>Food & Beverage:</strong> {item.foodAndBeverage}
              </div>
              <div>
                <strong>Cleanliness:</strong> {item.cleanliness}
              </div>
            </div>
          </div>
        ))}
      </div>

      {feedback.length === 0 && (
        <div className="no-feedback-container">
          <div className="no-feedback-message">
            <X size={48} className="text-red-600" />
            <p>No feedback to be shown</p>
          </div>
        </div>
      )}
    </div>
  );
};

export default Feedback;
