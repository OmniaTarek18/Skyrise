import React, { useState } from "react";
import { Send, RotateCcw, X } from "lucide-react";
import StarRating from "./StarRating";
import Slider from "./Slider";
import "./Feedback.css";

const initialFeedback = {
  overallRating: 0,
  punctuality: "",
  comfort: "",
  service: "",
  foodBeverage: "",
  cleanliness: "",
  comments: "",
};

const Feedback = ({ onSubmit, onClose }) => {
  const [feedback, setFeedback] = useState(initialFeedback);
  const [isSubmitting, setIsSubmitting] = useState(false);

  const categories = [
    { key: "punctuality", label: "Punctuality" },
    { key: "comfort", label: "Comfort" },
    { key: "service", label: "Service" },
    { key: "foodBeverage", label: "Food/Beverage" },
    { key: "cleanliness", label: "Cleanliness" },
  ];

  const handleStarRating = (rating) => {
    setFeedback((prev) => ({
      ...prev,
      overallRating: prev.overallRating === rating ? 0 : rating,
    }));
  };

  const handleSliderChange = (category, value) => {
    setFeedback((prev) => ({ ...prev, [category]: value }));
  };

  const clearCategory = (category) => {
    setFeedback((prev) => ({ ...prev, [category]: "" }));
  };

  const clearAll = () => {
    setFeedback(initialFeedback);
  };

  const isFormValid = () => {
    return (
      feedback.overallRating > 0 &&
      categories.every((cat) => feedback[cat.key] !== "") &&
      feedback.comments.trim() !== ""
    );
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!isFormValid()) return;

    setIsSubmitting(true);
    await new Promise((resolve) => setTimeout(resolve, 1500));
    onSubmit(feedback);
    setIsSubmitting(false);
  };

  return (
    <div className="feedback-form-container">
      <div className="feedback-form-header">
        <h2 className="feedback-form-title">Last Flight Feedback</h2>
        <button
          className="reset-button"
          type="button"
          onClick={clearAll}
          aria-label="Reset Form"
        >
          <RotateCcw size={16} />
          <span>Reset Form</span>
        </button>
        <button
          className="feedback-form-close-button"
          onClick={onClose}
          aria-label="Close Feedback Form"
        >
          <X size={20} />
        </button>
      </div>
      <form onSubmit={handleSubmit} className="feedback-form">
        <div className="rating-section">
          <label htmlFor="overallRating">Overall Rating</label>
          <StarRating
            rating={feedback.overallRating}
            onRate={handleStarRating}
          />
        </div>
        {categories.map(({ key, label }) => (
          <Slider
            key={key}
            label={label}
            value={feedback[key]}
            onChange={(value) => handleSliderChange(key, value)}
            onClear={() => clearCategory(key)}
          />
        ))}
        <div className="comments-section">
          <div className="comments-header">
            <label htmlFor="comments">Additional Comments</label>
            {feedback.comments && (
              <button
                type="button"
                className="clear-option"
                onClick={() =>
                  setFeedback((prev) => ({ ...prev, comments: "" }))
                }
                aria-label="Clear Comments"
              >
                Clear
              </button>
            )}
          </div>
          <textarea
            id="comments"
            placeholder="Share your thoughts..."
            value={feedback.comments}
            onChange={(e) =>
              setFeedback((prev) => ({ ...prev, comments: e.target.value }))
            }
          />
        </div>
        <button
          type="submit"
          className={`submit-button ${!isFormValid() ? "disabled" : ""} ${
            isSubmitting ? "submitting" : ""
          }`}
          disabled={!isFormValid() || isSubmitting}
        >
          <Send className="send-icon" size={20} />
          <span>{isSubmitting ? "Submitting..." : "Submit Feedback"}</span>
        </button>
      </form>
    </div>
  );
};

export default Feedback;
