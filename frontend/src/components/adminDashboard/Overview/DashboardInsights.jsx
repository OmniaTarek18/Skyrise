import React from "react";
import { Users2, Plane, CalendarRange } from "lucide-react";
import { InsightCard } from "./InsightCard";

export function DashboardInsights({ totalPassengers, flights, bookings }) {
  return (
    <div className="dashboard-insights-container">
      <InsightCard
        title="Total Passengers"
        value={totalPassengers}
        icon={<Users2 size={28} className="text-white" strokeWidth={1.5} />}
        color="blue"
      />
      <InsightCard
        title="Flights"
        value={flights}
        icon={<Plane size={28} className="text-white" strokeWidth={1.5} />}
        color="indigo"
      />
      <InsightCard
        title="Reservations"
        value={bookings}
        icon={
          <CalendarRange size={28} className="text-white" strokeWidth={1.5} />
        }
        color="sky"
      />
    </div>
  );
}
