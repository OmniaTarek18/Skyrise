import React from "react";
import { User, Globe, Ticket, Star } from "lucide-react"; 
import "./overview.css";

const Overview = () => {
  // dummy data
  const data = {
    totalUsers: 50,
    activeFlights: 100,
    reservations: 831,
    averageRating: 3.0,
  };

  return (
    <div className="dashboard-overview">
      <h1 className="overview-title">Dashboard Overview</h1>
      <div className="overview-grid">
        <div className="overview-card">
          <h2>Total Users</h2>
          <div className="card-content">
            <span className="card-value">{data.totalUsers}</span>
            <User className="icon user-icon" size={40} />
          </div>
        </div>
        <div className="overview-card">
          <h2>Active Flights</h2>
          <div className="card-content">
            <span className="card-value">{data.activeFlights}</span>
            <Globe className="icon flight-icon" size={40} />
          </div>
        </div>
        <div className="overview-card">
          <h2>Reservations</h2>
          <div className="card-content">
            <span className="card-value">{data.reservations}</span>
            <Ticket className="icon ticket-icon" size={40} />
          </div>
        </div>
        <div className="overview-card">
          <h2>Average Rating</h2>
          <div className="card-content">
            <span className="card-value">{data.averageRating.toFixed(1)}</span>
            <Star className="icon star-icon" size={40} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Overview;
