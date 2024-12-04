import React, { useEffect, useState } from "react";
import "./overview.css";
import { SkyriseCard } from "../../components/adminDashboard/Overview/SkyriseCard";
import { fetchInsights } from "../../api/insightsAPI";
import { DashboardInsights } from "../../components/adminDashboard/Overview/DashboardInsights";

const Overview = () => {
  const [insights, setInsights] = useState({
    totalUsers: 0,
    totalFlights: 0,
    totalBookings: 0,
  });

  useEffect(() => {
    const loadInsights = async () => {
      try {
        const data = await fetchInsights();
        setInsights(data);
      } catch (error) {
        console.error("Error fetching insights:", error);
      }
    };
    loadInsights();
  }, []);

  return (
    <div className="overview-page">
      <SkyriseCard />
      <DashboardInsights
        totalPassengers={insights.numOfUsers}
        flights={insights.numOfActiveFlights}
        bookings={insights.numOfReservations}
      />
    </div>
  );
};

export default Overview;
