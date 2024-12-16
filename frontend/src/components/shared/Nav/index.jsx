import React from "react";
import Logo from "../Logo";
import BlueLogo from "../../../assets/blueLogo.svg";
import "./style.css";
import { useNavigate } from "react-router-dom";
import useUserAuthenticationStore from "../../../store/useUserAuthenticationStore";

const Nav = ({ userLoggedIn = false }) => {
  const nav = useNavigate();
  const { setUserAuthentication } = useUserAuthenticationStore();
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
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
            className="nav-item nav-link active-page"
            href="#promotion-container"
            onClick={() => nav("/")}
          >
            Home
          </a>
          <a className="nav-item nav-link" href="#promotion">
            SEARCH FLIGHTS
          </a>
          <a className="nav-item nav-link" href="#review" onClick={() => nav("/")}>
            REVIEWS
          </a>
          <a className="nav-item nav-link" href="#about" onClick={() => nav("/")}>
            ABOUT US
          </a>
          <a className="nav-item nav-link" href="#footer" onClick={() => nav("/")}>
            CONTACT US
          </a>
          {userLoggedIn ? (
            <div className="user-profile-container">
              <a className="nav-item nav-link" onClick={() => nav("/user-dashboard")}>
                Profile
              </a>
              <a
                className="nav-item nav-link"
                onClick={() => {
                  setUserAuthentication(null, "USER");
                  nav("/");
                }}
              >
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
