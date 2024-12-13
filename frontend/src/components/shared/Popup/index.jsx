import React, { Children, useRef } from "react";
import "./style.css";
import { IoMdClose } from "react-icons/io";

const Popup = ({ onClose, children }) => {
  const popupRef = useRef();

  // Close the popup if clicking outside of it
  const closePopup = (e) => {
    if (popupRef.current === e.target) {
      onClose();
    }
  };

  return (
    <div
      ref={popupRef}
      onClick={closePopup}
      className="popup-page-container position-fixed top-0 start-0 w-100 h-100"
      style={{
        backgroundColor: "rgba(0, 0, 0, 0.3)", // Semi-transparent black
        backdropFilter: "blur(1px)", // Blur effect on elements behind the div
      }}
    >
      <div className="popup-container">
        <button className="cancel-container" onClick={onClose}>
          <IoMdClose />
        </button>
        <div className="form-container">
          {children}
        </div>
      </div>
    </div>
  );
};

export default Popup;
