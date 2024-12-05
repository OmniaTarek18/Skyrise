import React from "react";
import "./filterbutton.css";

function FilterButton({ label, isActive, onClick, disabled = false }) {
  return (
    <button
      onClick={onClick}
      disabled={disabled}
      className={`filter-button 
        ${isActive ? "filter-button--active" : ""} 
        ${disabled ? "filter-button--disabled" : ""} 
        ${!isActive && !disabled ? "filter-button--default" : ""}`}
    >
      {label}
    </button>
  );
}

export default FilterButton;
