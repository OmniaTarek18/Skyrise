import React from "react";
import "./style.css";

const Input = ({
  label,
  type = "text",
  id,
  placeholder = "",
  onChange,
  value,
  onBlur,
  showError = false, // Renamed for clarity
  errorMessage = null, // Customizable error message
  required = false, // Default required attribute
  selectionInput = false,
  defaultSelectionText,
  options,
}) => {
  return (
    <div className="mb-3">
      <label htmlFor={id} className="form-label">
        {label}
      </label>
      {!selectionInput && (
        <input
          type={type}
          className={`form-control ${showError ? "is-invalid" : ""}`} // Add Bootstrap's `is-invalid` class conditionally
          id={id}
          placeholder={placeholder}
          onChange={onChange}
          value={value}
          onBlur={onBlur}
          required={required}
        />
      )}
      {selectionInput && (
        <select
          id={id}
          className={`form-select ${showError ? "is-invalid" : ""}`} // Add Bootstrap's `is-invalid` class conditionally
          required={required}
          onChange={onChange}
          value={value}
          onBlur={onBlur}
        >
          <option value="" defaultValue disabled hidden>
            {defaultSelectionText}
          </option>
          {options.map((option, index) => (
            <option key={index} value={option}>
              {option}
            </option>
          ))}
        </select>
      )}
      {showError && (
        <div className="invalid-feedback">
          {errorMessage}
        </div>
      )}
    </div>
  );
};

export default Input;
