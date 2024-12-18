import React from 'react';
import { Plane, Calendar, MapPin } from 'lucide-react';
import './cardbutton.css';

const CardButton = ({ date, source, destination, isReturn = false, onClick}) => {
  return (
    <button className="card-button" onClick={() => onClick(date, source, destination)}>
      <div className="card-content">
        <div className="date-badge">
          <Calendar className="calendar-icon" size={16} />
          <span>{date}</span>
        </div>
        
        <div className="flight-path">
          <div className="location source">
            <MapPin className="location-icon" size={20} />
            <span className="city-code">{source}</span>
          </div>
          
          <div className="flight-line">
            <div className="line"></div>
            <Plane className={`plane-icon ${isReturn ? 'return' : ''}`} size={24} />
          </div>
          
          <div className="location destination">
            <MapPin className="location-icon" size={20} />
            <span className="city-code">{destination}</span>
          </div>
        </div>

        <div className="card-background">
          <div className="circle circle-1"></div>
          <div className="circle circle-2"></div>
          <div className="circle circle-3"></div>
        </div>
      </div>
    </button>
  );
};

export default CardButton;