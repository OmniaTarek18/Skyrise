import React from "react";
import { CiStar } from "react-icons/ci";
import { FaStar } from "react-icons/fa6";
import "./style.css";

const CustomerSayCard = ({ rating, name, review, date }) => {
  const stars = Array.from({ length: 5 }, (_, index) => {
    return index < rating ? <FaStar style={{color: "var(--navy-blue)"}}/> :  <CiStar style={{color: "var(--navy-blue)"}}/>;
  });
  return (
    <article className="review-card">
      <p className="rating">
        {stars.map((star, index) => (
          <span key={index}>{star}</span>
        ))}
      </p>
      <h3 className="name">{name}</h3>
      <p className="review">{review}</p>
      <time className="date">{date}</time>
    </article>
  );
};

export default CustomerSayCard;
