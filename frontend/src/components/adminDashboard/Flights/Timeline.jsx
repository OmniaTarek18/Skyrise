import React, { useState, useEffect } from "react";
import { ChevronLeft, ChevronRight } from "lucide-react";
import "./timeline.css"; 

function formatDate(date) {
  const options = {
    weekday: "short",
    month: "short",
    day: "numeric",
    year: "numeric",
  };
  return date.toLocaleDateString(undefined, options);
}


function getWeekDates(date) {
  const startOfWeek = new Date(date);
  startOfWeek.setDate(date.getDate() - date.getDay()); 

  return Array.from({ length: 7 }, (_, i) => {
    const day = new Date(startOfWeek);
    day.setDate(startOfWeek.getDate() + i);
    return day;
  });
}


function addDays(date, days) {
  const newDate = new Date(date);
  newDate.setDate(newDate.getDate() + days);
  return newDate;
}

export const Timeline = ({ currentDate, onDateChange }) => {
  const [dates, setDates] = useState(getWeekDates(currentDate));

 
  useEffect(() => {
    setDates(getWeekDates(currentDate));
  }, [currentDate]);

  return (
    <div className="timeline-container">
      {/* navigation Section */}
      <div className="timeline-nav">
        {/* left arrow to go to the rrevious week */}
        <button
          onClick={() => onDateChange(addDays(currentDate, -7))}
          className="timeline-nav-button"
        >
          <ChevronLeft className="timeline-nav-icon" />
        </button>

        {/* week dates */}
        <div className="timeline-dates">
          {dates.map((date) => (
            <button
              key={date.toISOString()} 
              onClick={() => onDateChange(date)} 
              className={`timeline-date-button ${
                date.toDateString() === currentDate.toDateString()
                  ? "selected"
                  : ""
              }`}
            >
              {formatDate(date)}
            </button>
          ))}
        </div>

        {/* right arrow to go to the next week */}
        <button
          onClick={() => onDateChange(addDays(currentDate, 7))}
          className="timeline-nav-button"
        >
          <ChevronRight className="timeline-nav-icon" />
        </button>
      </div>
    </div>
  );
};
