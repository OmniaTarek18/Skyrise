import React, { useState, useEffect } from "react";
import { Star, X } from "lucide-react";
import { ReviewStatCard } from "../../components/adminDashboard/Review/ReviewStatCard";
import FilterButton from "../../components/shared/Button/FilterButton";
import {
  getFilteredFeedback,
  getAverageRating,
  getAllFeedback,
} from "../../api/feedbackAPI";
import Post from "./feedbackObject";

const Feedback = () => {
  const [page, setPage] = useState(0);
  const [feedback, setFeedback] = useState([]);
  const [averageRating, setAverageRating] = useState(0.0);
  const [totalReviews, setTotalReviews] = useState(0);
  const [starFilter, setStarFilter] = useState(null);
  const [categoryFilter, setCategoryFilter] = useState("");
  const [performanceFilter, setPerformanceFilter] = useState("");
  const [sortDirection, setSortDirection] = useState("asc"); 

  useEffect(() => {
    const filterCriteria = {
      stars: starFilter,
      direction: sortDirection, 
      ...(categoryFilter &&
        performanceFilter && {
          [categoryFilter]: performanceFilter,
        }),
    };

    // determining which api to call based on the criteria or no criteria
    if (
      starFilter !== null ||
      (categoryFilter !== "" && performanceFilter !== "") || sortDirection !== ""
    ) {
  
      getFilteredFeedback(filterCriteria, page)
        .then((response) => {
          setFeedback(response.content || []); 
          setTotalReviews(response.totalElements || 0); 
        })
        .catch((error) => {
          console.error("Fetching filtered feedback failed:", error);
          setFeedback([]); 
        });
    } else {
      getAllFeedback(page)
        .then((response) => {
          setFeedback(response.content || []); 
          setTotalReviews(response.totalElements || 0); 
        })
        .catch((error) => {
          console.error("Fetching all feedback failed:", error);
          setFeedback([]);
        });
    }
  }, [starFilter, categoryFilter, performanceFilter, page, sortDirection]); 
  useEffect(() => {
    getAverageRating()
      .then((response) => setAverageRating(response || 0.0))
      .catch((error) => {
        console.error("Error fetching average rating:", error);
        setAverageRating(0);
      });
  }, []);

  const handleCategoryChange = (category) => {
    setCategoryFilter(category);
    setPerformanceFilter(""); 
    setPage(0); 
  };

  const handlePerformanceClick = (performance) => {
    setPerformanceFilter((prev) => (prev === performance ? "" : performance)); 
    setPage(0);
  };

  const content = feedback.map((f) => (
    <Post key={`${f.id}-${f.timestamp}`} post={f} />
  ));

  const nextPage = () => setPage((prev) => prev + 1);
  const prevPage = () => setPage((prev) => Math.max(prev - 1, 0));

  return (
    <div className="feedback-container">
      {/* average rating and total no of reviews */}
      <ReviewStatCard
        totalReviews={totalReviews}
        averageRating={averageRating}
      />

      {/* filter section */}
      <div className="feedback-filters">
        {/* star filtering */}
        <div className="filter-group">
          <div className="filter-buttons">
            {[1, 2, 3, 4, 5].map((rating) => (
              <button
                key={rating}
                onClick={() =>
                  setStarFilter(starFilter === rating ? null : rating)
                }
                className={`star-filter-btn ${
                  starFilter === rating ? "active" : ""
                }`}
              >
                {rating} <Star className="inline-icon" size={16} />
              </button>
            ))}
          </div>
        </div>

        {/* category filters*/}
        <div className="filter-group">
          <select
            className="filter-dropdown"
            onChange={(e) => handleCategoryChange(e.target.value)}
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

      <div className="sort-and-performance-filters">
        {/* sort radio buttons*/}
        <div className="sort-direction">
          <label>
            <input
              className="radio-btn"
              type="radio"
              name="sortDirection"
              value="asc"
              checked={sortDirection === "asc"}
              onChange={() =>
                setSortDirection(sortDirection === "asc" ? "" : "asc")
              }
            />
            Oldest
          </label>
          <label>
            <input
              className="radio-btn"
              type="radio"
              name="sortDirection"
              value="desc"
              checked={sortDirection === "desc"}
              onChange={() =>
                setSortDirection(sortDirection === "desc" ? "" : "desc")
              }
            />
            Latest
          </label>
        </div>

        {/* performance filters */}
        <div className="performance-filters">
          <FilterButton
            label="Excellent"
            isActive={performanceFilter === "EXCELLENT"}
            disabled={!categoryFilter}
            onClick={() => handlePerformanceClick("EXCELLENT")}
          />
          <FilterButton
            label="Fair"
            isActive={performanceFilter === "FAIR"}
            disabled={!categoryFilter}
            onClick={() => handlePerformanceClick("FAIR")}
          />
          <FilterButton
            label="Poor"
            isActive={performanceFilter === "POOR"}
            disabled={!categoryFilter}
            onClick={() => handlePerformanceClick("POOR")}
          />
        </div>
      </div>

      {/* feedback list */}
      <div className="feedback-list">{content}</div>

      {/* no feedback message */}
      {feedback.length === 0 && (
        <div className="no-feedback-container">
          <div className="no-feedback-message">
            <X size={48} className="text-red-600" />
            <p>No feedback to be shown</p>
          </div>
        </div>
      )}

      {/*pagintation*/}
      <nav className="pagination-buttons">
        <button onClick={prevPage} disabled={page === 0}>
          Prev
        </button>
        <button onClick={nextPage} disabled={feedback.length < 10}>
          Next
        </button>
      </nav>
    </div>
  );
};

export default Feedback;
