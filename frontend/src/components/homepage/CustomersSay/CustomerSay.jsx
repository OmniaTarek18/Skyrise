import React from "react";
import CustomerSayCard from "./CustomersSayCard";
import "./style.css";

const REVIEWS = [
  {
    rating: 4,
    name: "john doe",
    review:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed laoreet condimentum orci,.Lorem ipsum dolor sit amet, consectetur adipiscing elit. ",
    date: "19/4/2003",
  },
  {
    rating: 5,
    name: "will smith",
    review:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed laoreet condimentum orci,.Lorem ipsum dolor sit amet, consectetur adipiscing elit. ",
    date: "12/4/2003",
  },
  {
    rating: 3,
    name: "jane doe",
    review:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed laoreet condimentum orci,.Lorem ipsum dolor sit amet, consectetur adipiscing elit. ",
    date: "16/10/1971",
  },
  ,
  {
    rating: 3,
    name: "jane doe",
    review:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed laoreet condimentum orci,.Lorem ipsum dolor sit amet, consectetur adipiscing elit. ",
    date: "16/10/1971",
  },
];

const CustomerSay = () => {
  return (
    <div className="review-container" id="review">
      <h1 className="card-header">What our Clients say!</h1>
      <div className="cards">
        {REVIEWS.map((review, index) => (
          <CustomerSayCard
            key={index}
            name={review.name}
            rating={review.rating}
            date={review.date}
            review={review.review}
          />
        ))}
      </div>
    </div>
  );
};

export default CustomerSay;
