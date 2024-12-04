import React from "react";
import "./button.css"; 

export const Button = ({
  variant = "default",
  onClick,
  children,
  className = "",
}) => {
  return (
    <button onClick={onClick} className={`btn ${variant} ${className}`}>
      {children}
    </button>
  );
};
