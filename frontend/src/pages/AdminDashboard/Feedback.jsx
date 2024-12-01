import React, { useState } from "react";
import { Star, Filter, X, StarIcon } from "lucide-react";
import ReviewCard from "../../components/adminDashboard/Review/ReviewCard";
import "./feedback.css";
import { ReviewStatCard } from "../../components/adminDashboard/Review/ReviewStatCard";

const Feedback = () => {
  // mock data for feedback
  const feedback = [
    {
      id: 1,
      userName: "nada",
      source: "alex",
      destination: "london",
      date: "2024-11-15",
      starRating: 4,
      ratings: {
        punctuality: "Excellent",
        comfort: "Fair",
        service: "Good",
        foodAndBeverage: "Fair",
        cleanliness: "Excellent",
      },
      comments: "Great experience.",
    },
    {
      id: 2,
      userName: "akfdls",
      source: "paris",
      destination: "tokyo",
      date: "2024-11-12",
      starRating: 5,
      ratings: {
        punctuality: "Excellent",
        comfort: "Excellent",
        service: "Excellent",
        foodAndBeverage: "Good",
        cleanliness: "Excellent",
      },
      comments:
        "Would fly again. the food was really good and i enjoyed alot it was such a good experiece",
    },
  ];

  // dummy values for averageRating
  const averageRating = 4.5;
  const totalReviews = feedback.length;

  const [starFilter, setStarFilter] = useState(null);
  const [categoryFilter, setCategoryFilter] = useState("");

  return (
    <div className="feedback-container">
      {/* average rating and total no of reviews */}
      <div>
        <ReviewStatCard
          totalReviews={totalReviews}
          averageRating={averageRating}
        />
      </div>

      {/* filter section */}
      <div className="feedback-filters">
        <div className="filter-group">
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
          </div>
        </div>

        <div className="filter-group">
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
            <ReviewCard
              userName={item.userName}
              source={item.source}
              destination={item.destination}
              date={item.date}
              starRating={item.starRating}
              ratings={item.ratings}
              comments={item.comments}
            />
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
