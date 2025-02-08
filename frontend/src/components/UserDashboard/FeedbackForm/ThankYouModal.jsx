import React, { useEffect } from "react";
import { X, Star } from "lucide-react";
import FeedbackBadge from "./FeedbackBadge";
import ParticleEffect from "./ParticleEffect"; 
import "./ThankYouModal.css";

const ThankYouModal = ({ isOpen, onClose }) => {
  useEffect(() => {
    if (isOpen) {
      document.body.style.overflow = "hidden";
    }
    return () => {
      document.body.style.overflow = "unset";
    };
  }, [isOpen]);

  if (!isOpen) return null;

  return (
    <div className="feedback-modal-overlay">
      <ParticleEffect />

      <div className="feedback-modal-content">
        <button
          className="thank-u-close-button"
          onClick={onClose}
          aria-label="Close"
        >
          <X size={24} />
        </button>

        <FeedbackBadge />

        <div className="text-content">
          <div className="stars-row">
            {[...Array(5)].map((_, i) => (
              <Star
                key={i}
                className="thanks-star-icon"
                fill="#FFD700"
                color="#FFD700"
                size={24}
              />
            ))}
          </div>
          <h2>Thanks for Your Feedback!</h2>
          <p className="main-message">Your feedback help us improve</p>
          <p className="sub-message">We appreciate your valuable comment</p>
        </div>
      </div>
    </div>
  );
};


export default ThankYouModal;
