import React from "react";
import "./style.css";

const Input = ({
  label = false,
  type = "text",
  min = 1, // used with input type number only
  id,
  placeholder = "",
  onChange,
  value = "",
  onBlur,
  showError = false, // Renamed for clarity
  errorMessage = null, // Customizable error message
  required = false, // Default required attribute
  selectionInput = false,
  defaultSelectionText,
  options,
  disabled= false,
}) => {
  return (
    <div className="mb-3">
      {label && (
        <label htmlFor={id} className="form-label">
          {label}
        </label>
      )}
      {!selectionInput && (
        <input
          type={type === "date" ? "text" : type}
          className={`form-control ${showError ? "is-invalid" : ""}`} // Add Bootstrap's `is-invalid` class conditionally
          id={id}
          placeholder={placeholder}
          onChange={onChange}
          value={value}
          onBlur={
            type === "date"
              ? (e) => {
                  e.target.type = "text"; // Switch back to text on blur
                  if(onBlur) onBlur(e); // Call Formik's onBlur
                }
              : onBlur // Directly call Formik's onBlur for other types
          }
          onFocus={
            type === "date"
              ? (e) => {
                  e.target.type = "date"; // Switch to date picker on focus
                }
              : undefined // No additional behavior for other types
          }
          min={min}
          required={required}
          disabled ={disabled}
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
          <option value=""  disabled hidden>
            {defaultSelectionText}
          </option>
          {options.map((option, index) => (
            <option key={index} value={option}>
              {option}
            </option>
          ))}
        </select>
      )}
      {showError && <div className="invalid-feedback">{errorMessage}</div>}
    </div>
  );
};

export default Input;
