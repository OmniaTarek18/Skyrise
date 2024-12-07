import React from "react"; // Import React for JSX and functional component creation
import Button from "../../shared/Button"; // Import the reusable Button component
import { FcGoogle } from "react-icons/fc"; // Import the Google icon from react-icons library
import { GoogleOAuthProvider } from "@react-oauth/google"; // Import the Google OAuth provider for Google login functionality
import { useLogin } from "./validation"; // Import the custom login hook for handling Google login
import { GoogleLoginAPI } from "./api";
/**
 * GoogleLogInComp Component
 * A functional component that renders a button for logging in via Google.
 * It uses the Button component to display a button with a Google icon and text.
 * Upon clicking, it triggers the login process using the provided login function from the custom useLogin hook.
 *
 * Props:
 * - No props are directly passed to GoogleLogInComp; it uses the login function from the useLogin hook.
 *
 * Returns:
 * - A Button component that is configured to trigger the Google login onClick.
 */
const GoogleLogInComp = () => {
  // Call the custom login hook to get the login function
  const login = useLogin(GoogleLoginAPI);

  return (
    <Button
      // The icon to display inside the button (Google icon from react-icons)
      icon={<FcGoogle style={{ fontSize: "40px", padding: "10px" }} />}
      // The text displayed on the button
      btnText={" Sign in with Gmail"}
      // Set the button color to "light" using the Bootstrap button class
      btnColor="light"
      // Trigger the login function when the button is clicked
      handleClick={login}
    />
  );
};

/**
 * GoogleLogIn Component
 * This is the higher-level component that wraps the GoogleLogInComp component with the Google OAuth provider.
 * The GoogleOAuthProvider is necessary to enable Google login using OAuth.
 * It provides the required clientId to authenticate with Google's OAuth service.
 *
 * Returns:
 * - A wrapped GoogleLogInComp component inside the GoogleOAuthProvider.
 */
const GoogleLogIn = () => {
  return (
    // Wrap the GoogleLogInComp component with GoogleOAuthProvider for OAuth login
    <GoogleOAuthProvider clientId="838411676468-ove9bo5rm1rtguuks5co7632t8nc5ahm.apps.googleusercontent.com">
      <GoogleLogInComp />
    </GoogleOAuthProvider>
  );
};

// Export the main GoogleLogIn component as the default export for use in other parts of the app
export default GoogleLogIn;
