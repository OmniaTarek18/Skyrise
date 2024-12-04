import React from "react"; // Import React and useState for state management
import "./style.css"; // Import the CSS file where Bootstrap styles are overridden for the Button component

/**
 * Button Component
 * A reusable button component that integrates with Bootstrap and allows customization via props.
 * Props:
 * - btnText: The text displayed on the button.
 * - handleClick: A callback function triggered when the button is clicked.
 * - btnColor: The Bootstrap button color class (e.g., "primary", "dark", "light"). Default is "primary".
 * - icon: Optional. The icon to display inside the button.
 * - type: Optional. The type of the button ("button", "submit", "reset"). Default is "button".
 * - disabled: Optional. A boolean to disable the button.
 */

const Button = ({ icon = null, btnText, handleClick, btnColor = "primary", type = "button", disabled = false }) => {
  // State for handling alerts (currently not in use)
  // const [alert, setAlert] = useState(false);

  return (
    <div>
      {/* The button element */}
      <button
        type={type} // Set the button type (button, submit, or reset)
        className={"btn btn-" + btnColor} // Set the Bootstrap button class based on the provided btnColor
        onClick={handleClick} // Trigger the handleClick function when the button is clicked
        disabled={disabled} // Disable the button if the disabled prop is true
      >
        {/* Display the icon if provided */}
        {icon}
        {/* Display the button text */}
        {btnText}
      </button>
    </div>
  );
};

export default Button;
