import React from "react";
import { useState, useEffect } from "react";
import Logo from "../Logo";
import BlueLogo from "../../../assets/blueLogo.svg";
import "./style.css";
import { useNavigate } from "react-router-dom";
import useUserAuthenticationStore from "../../../store/useUserAuthenticationStore";
import { getNotification } from "../../../api/flightsAfterSearch"; // Adjust the path as necessary
import { MdLogout } from "react-icons/md";
import { CgProfile } from "react-icons/cg";
import Notification from "../../UserDashboard/Notification";
import { Stomp } from "@stomp/stompjs";

const Nav = ({ userLoggedIn = false, isProfile = false }) => {
  const nav = useNavigate();
  const { setUserAuthentication } = useUserAuthenticationStore();
  const { id, role } = useUserAuthenticationStore();
  const [notifications, setNotifications] = useState([]);

  const handleGetNotifications = async (id, pageNum) => {
    try {
      const data = await getNotification(id, pageNum);
      setNotifications((prev) => [...prev, ...data.content]);
      return data.content;
    } catch (error) {
      console.error("Failed to get notifications:", error);
    }
  };

  useEffect(() => {
    handleGetNotifications(id, 0);
    const socketUrl = "http://localhost:8080/notifications";
    const socket = new WebSocket(socketUrl);
    const stompClient = Stomp.over(socket);

    stompClient.connect(
      { withCredentials: true }, // Ensure credentials are sent
      () => {
        console.log("Connected to WebSocket");

        stompClient.subscribe(`/topic/user-${id}`, (message) => {
          console.log("Received message:", JSON.parse(message.body));
          const newNotification = JSON.parse(message.body);
          setNotifications((prevNotifications) => [
            newNotification,
            ...prevNotifications,
          ]);
        });
      },
      (error) => {
        console.error("Connection failed:", error);
      }
    );

    return () => {
      if (stompClient) stompClient.disconnect();
    };
  }, []);
  return (
    <nav className="nav-container navbar navbar-expand-lg navbar-light bg-light">
      <a className="navbar-brand" onClick={() => nav("/")}>
        <Logo color={BlueLogo} className="navLogo navbar-nav" />
      </a>
      <button
        className="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#menu-bar"
        aria-controls="menu-bar"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>

      <div className="collapse navbar-collapse nav-style" id="menu-bar">
        <div className="navbar-nav">
          <a
            className={"nav-item nav-link " + (isProfile ? "" : "active-page")}
            href="#promotion-container"
            onClick={() => nav("/")}
          >
            Home
          </a>
          <a
            className="nav-item nav-link"
            href="#promotion"
            onClick={() => nav("/")}
          >
            SEARCH FLIGHTS
          </a>
          <a
            className="nav-item nav-link"
            href="#review"
            onClick={() => nav("/")}
          >
            REVIEWS
          </a>
          <a
            className="nav-item nav-link"
            href="#about"
            onClick={() => nav("/")}
          >
            ABOUT US
          </a>
          {userLoggedIn && (
            <Notification
              number={
                notifications.filter((obj) => obj.status === "UNSEEN").length
              }
              init={notifications}
              onClick={handleGetNotifications}
            />
          )}
          {userLoggedIn ? (
            <div className="user-profile-container">
              <a
                className={
                  "nav-item nav-link " + (isProfile ? "active-page" : "")
                }
                onClick={() => nav("/user-dashboard")}
                style={{ paddingRight: "1.2rem" }}
              >
                <CgProfile
                  style={{ fontSize: "20px", margin: "0 0.5rem 0.2rem 0.5rem" }}
                />{" "}
                Profile
              </a>
              <a
                className="nav-item nav-link"
                onClick={() => {
                  setUserAuthentication(null, "USER");
                  nav("/");
                }}
                style={{ paddingRight: "1.2rem" }}
              >
                <MdLogout
                  style={{ fontSize: "20px", margin: "0 0.5rem 0.2rem 0.5rem" }}
                />{" "}
                Log out
              </a>
            </div>
          ) : (
            <div className="user-registeration-container">
              <a className="nav-item nav-link" onClick={() => nav("/login")}>
                log in
              </a>
              <a className="nav-item nav-link" onClick={() => nav("/signup")}>
                sign up
              </a>
            </div>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Nav;
