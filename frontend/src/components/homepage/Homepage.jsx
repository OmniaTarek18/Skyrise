import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Nav from "../shared/Nav";
import Promotion from "./Promotion";
import promotionImg from "../../assets/promotion.jpeg";
import Footer from "./Footer";
import About from "./About";
import CustomerSay from "./CustomersSay/CustomerSay";
import useUserAuthenticationStore from "../../store/useUserAuthenticationStore";
import Feedback from "../UserDashboard/FeedbackForm/Feedback";
import ThankYouModal from "../UserDashboard/FeedbackForm/ThankYouModal";
import {
  fetchUserName,
  fetchMostRecentReservation,
  updateDismissCount,
  submitFeedback,
} from "../../api/feedbackFormAPI";

const Homepage = () => {
  const navigate = useNavigate();
  const { id, role } = useUserAuthenticationStore();
  const [reservation, setReservation] = useState(null);
  const [userName, setUserName] = useState("");
  const [isFeedbackOpen, setIsFeedbackOpen] = useState(false);
  const [showThankYou, setShowThankYou] = useState(false);

  const getUserName = async () => {
    try {
      const { firstName, lastName } = await fetchUserName(id);
      setUserName(`${firstName} ${lastName}`);
    } catch (error) {
      console.error(error);
    }
  };

  const getMostRecentReservation = async () => {
    try {
      const reservationData = await fetchMostRecentReservation(id);
      setReservation(reservationData);

      // show the feedback modal if dismissCount is less than 3
      if (reservationData.dismissCount < 3) {
        setIsFeedbackOpen(true);
      }
    } catch (error) {
      console.error(error);
    }
  };

  const handleThankYouClose = () => {
    setShowThankYou(false);
    setIsFeedbackOpen(false);
  };

  const increaseDismissCount = async () => {
    if (reservation) {
      try {
        const newDismissCount = reservation.dismissCount + 1;
        await updateDismissCount(id, reservation.flightId, newDismissCount);
        setReservation((prev) => ({ ...prev, dismissCount: newDismissCount }));
      } catch (error) {
        console.error(error);
      }
    }
  };

  const handleFeedbackSubmit = async (feedbackData) => {
    if (reservation) {
      try {
        const feedbackDTO = {
          userId: id,
          flightId: reservation.flightId,
          stars: feedbackData.overallRating,
          comment: feedbackData.comments,
          userName: userName,
          flightSource: reservation.flightSource,
          flightDestination: reservation.flightDestination,
          flightDepartureDate: reservation.flightDepartureDate,
          comfort: feedbackData.comfort,
          service: feedbackData.service,
          punctuality: feedbackData.punctuality,
          foodAndBeverage: feedbackData.foodBeverage,
          cleanliness: feedbackData.cleanliness,
          dateOfCreation: new Date().toISOString(),
        };

        await submitFeedback(feedbackDTO);

        // set dismiss count to a value greater than 3 after feedback submission so that the modal doesn't show in future opens
        await updateDismissCount(id, reservation.flightId, 4);

        setShowThankYou(true);
        setIsFeedbackOpen(false);
      } catch (error) {
        console.error("Error submitting feedback:", error);
      }
    }
  };

  useEffect(() => {
    if (role === "ADMIN") {
      navigate("/admin-dashboard");
    } else if (role === "USER" && id != null) {
      getUserName();
      getMostRecentReservation();
    }
  }, [role, id, navigate]);

  return (
    <>
      {role === "USER" && (
        <>
          {id != null ? <Nav userLoggedIn={true} /> : <Nav />}
          <Promotion img={promotionImg} />
          <CustomerSay />
          <About />
          <Footer />

          {/* feedback modal */}
          {isFeedbackOpen && (
            <Feedback
              onSubmit={handleFeedbackSubmit}
              onClose={() => {
                increaseDismissCount(); // increase dismiss count when the user closes the modal
                setIsFeedbackOpen(false);
              }}
            />
          )}

          {/* thank you modal shows post feedback submission */}
          {showThankYou && (
            <ThankYouModal
              isOpen={showThankYou}
              onClose={handleThankYouClose}
            />
          )}
        </>
      )}
    </>
  );
};

export default Homepage;
