import React from "react"; // Import React for component creation
import Button from "../../shared/Button"; // Import the reusable Button component
import { FcGoogle } from "react-icons/fc"; // Import the Google logo icon from react-icons
import { GoogleOAuthProvider } from "@react-oauth/google"; // Import the provider for Google OAuth integration
import { useSignup } from "./api"; // Import the useSignup custom hook that handles the signup logic

/**
 * GoogleSignUpComp Component
 * This component handles the signup flow using Google OAuth. It uses the `useSignup` custom hook to manage
 * the signup process, which includes fetching the user's data (email, first name, and last name) from Google.
 * It triggers the Google signup process when the button is clicked.
 *
 * Props:
 * - `handleEmail`: Function to update the email state in the parent component.
 * - `handleFirstName`: Function to update the first name state in the parent component.
 * - `handleLastName`: Function to update the last name state in the parent component.
 *
 * The component renders a Button component with a Google icon and triggers the `signup` function from the custom hook
 * when the button is clicked. The button is styled with Bootstrap's light color theme and displays the text "Sign up using Gmail".
 */
const GoogleSignUpComp = ({ handleEmail, handleFirstName, handleLastName }) => {
  // Use the useSignup hook to manage the signup logic, passing the state handlers as arguments
  const signup = useSignup({
    handleEmail: handleEmail,
    handleFirstName: handleFirstName,
    handleLastName: handleLastName,
  });

  return (
    <Button
      // Set the button icon to a Google logo
      icon={<FcGoogle style={{ fontSize: "40px", padding: "10px" }} />}
      btnText={" Sign up using Gmail"} // Set the button text
      btnColor="light" // Set the button color to light using Bootstrap's color class
      handleClick={() => signup(handleEmail, handleFirstName, handleLastName)} // Trigger the signup function on button click
    />
  );
};

/**
 * GoogleSignUp Component
 * This is a wrapper component that provides the `GoogleOAuthProvider` to enable the use of Google OAuth features.
 * It renders the `GoogleSignUpComp` component, passing the email, first name, and last name state handler functions
 * as props. It ensures that the component is correctly wrapped with the `GoogleOAuthProvider`, which is required for
 * the Google login integration to work.
 *
 * Props:
 * - `handleEmail`: Function to update the email state in the parent component.
 * - `handleFirstName`: Function to update the first name state in the parent component.
 * - `handleLastName`: Function to update the last name state in the parent component.
 */
const GoogleSignUp = ({ handleEmail, handleFirstName, handleLastName }) => {
  return (
    <GoogleOAuthProvider clientId="838411676468-ove9bo5rm1rtguuks5co7632t8nc5ahm.apps.googleusercontent.com">
      {/* Render the GoogleSignUpComp component with the state handler functions as props */}
      <GoogleSignUpComp
        handleEmail={handleEmail}
        handleFirstName={handleFirstName}
        handleLastName={handleLastName}
      />
    </GoogleOAuthProvider>
  );
};

export default GoogleSignUp;
