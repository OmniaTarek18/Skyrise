import React from "react";
import "./insightcard.css";
export function InsightCard({ title, value, icon, color }) {

  return (
    <div className={`card card--${color}`}>
      <div className="card__content">
        <div className="card__header">
          <div className="card__icon">{icon}</div>
        </div>
        <div className="card__body">
          <h3 className="card__value">{value}</h3>
          <div className="card__divider"></div>
          <p className="card__title">{title}</p>
        </div>
      </div>
    </div>
  );
}
