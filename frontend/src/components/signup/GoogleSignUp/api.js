import { useGoogleLogin } from "@react-oauth/google"; // Import useGoogleLogin from the Google OAuth library for the login functionality
import axios from "axios"; // Import axios for making HTTP requests to Google API

/**
 * useSignup Custom Hook
 * This custom hook is used for signing up a user via Google OAuth. It triggers the Google login process
 * and fetches the user's information (such as first name, last name, and email) after successful login.
 * The hook then calls the provided state handler functions to update the user data in the parent component.
 *
 * The hook performs the following actions:
 * 1. Initiates the Google login process using the `useGoogleLogin` hook.
 * 2. Once login is successful, it fetches user information (such as first name, last name, and email)
 *    from Google’s API using the access token received in the response.
 * 3. The fetched user data is passed to the state handler functions `handleFirstName`, `handleLastName`,
 *    and `handleEmail` for updating the state in the parent component.
 * 4. If any error occurs during the login or API request, the error is logged in the console.
 *
 * Arguments:
 * - `handleEmail`: A function to update the email state in the parent component.
 * - `handleFirstName`: A function to update the first name state in the parent component.
 * - `handleLastName`: A function to update the last name state in the parent component.
 *
 * Returns:
 * - `signup`: The function that triggers the Google OAuth login process.
 *   This function is to be called by the component using this hook to start the signup process.
 */
export const useSignup = ({ handleEmail, handleFirstName, handleLastName }) => {
  // Call the useGoogleLogin hook to manage Google login process
  const signup = useGoogleLogin({
    onSuccess: async (response) => {
      try {
        // Once login is successful, fetch user info from Google API using the provided access token
        const res = await axios.get(
          "https://www.googleapis.com/oauth2/v3/userinfo",
          {
            headers: {
              Authorization: `Bearer ${response.access_token}`, // Send the access token as an Authorization header
            },
          }
        );
        console.log(res); // Log the response (this could be replaced with additional logic)

        // Call the provided handler functions to update user data in the parent component's state
        handleFirstName(res.data.given_name); // Set the first name in the state
        handleLastName(res.data.family_name); // Set the last name in the state
        handleEmail(res.data.email); // Set the email in the state
      } catch (err) {
        // Handle any errors that may occur during the API request or login process
        console.error("Error fetching user data:", err); // Log error details
      }
    },
  });

  return signup; // Return the signup function that can be used by the component
};
