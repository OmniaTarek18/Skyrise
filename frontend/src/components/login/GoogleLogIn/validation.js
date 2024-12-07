import { useGoogleLogin } from "@react-oauth/google"; // Import the useGoogleLogin hook from the Google OAuth library for login functionality
import axios from "axios"; // Import axios to make HTTP requests
import { useNavigate } from "react-router-dom"; // Import useNavigate from react-router-dom for navigation after login

/**
 * useLogin Custom Hook
 * A custom hook that encapsulates the logic for logging in a user using Google OAuth.
 * This hook uses the `useGoogleLogin` hook to trigger the Google login process and
 * handles the response by fetching user data from Google's API.
 * It also provides navigation to the home page (`"/"`) upon successful login.
 *
 * The hook performs the following:
 * 1. Initiates Google login via the `useGoogleLogin` hook.
 * 2. On successful login, it fetches user info from the Google API using the provided access token.
 * 3. Logs the user data to the console (could be replaced with further actions like backend validation).
 * 4. Navigates to the homepage (or any other page) after a successful login.
 *
 * Returns:
 * - `login`: A function that triggers the Google login process when called.
 */
export const useLogin = (GoogleLoginAPI) => {
  const navigate = useNavigate(); // Initialize the navigate function to navigate to different pages

  // Call useGoogleLogin hook to manage Google login process
  const login = useGoogleLogin({
    onSuccess: async (response) => {
      try {
        // Fetch user data from Google API using the access token
        const res = await axios.get(
          "https://www.googleapis.com/oauth2/v3/userinfo",
          {
            headers: {
              Authorization: `Bearer ${response.access_token}`, // Pass the access token as a header for authentication
            },
          }
        );
        console.log(res.data); // Log the user data (this could be replaced with backend validation)
        // After successful login, navigate to the home page
        const isLoggedIn = await GoogleLoginAPI(res.data.email);
        if (isLoggedIn) {
          navigate("/"); // Redirect user to the home page or a dashboard
        }
      } catch (err) {
        // Handle any errors during the login process
        console.error("Login error:", err); // Log any errors to the console for debugging
      }
    },
  });

  return login; // Return the login function for the component to use
};
