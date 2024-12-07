import React from "react";
import Logo from "../Logo";
import BlueLogo from "../../../assets/blueLogo.svg";
import "./style.css";
import { useNavigate } from "react-router-dom";

const Nav = ({ userLoggedIn = false }) => {
  const nav = useNavigate();
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <a className="navbar-brand" onClick={() => nav("/")}>
        <Logo color={BlueLogo} className="logo navbar-nav" />
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
          >
            Home
          </a>
          <a className="nav-item nav-link" href="#">
            SEARCH FLIGHTS
          </a>
          <a className="nav-item nav-link" href="#review">
            REVIEWS
          </a>
          <a className="nav-item nav-link" href="#about">
            ABOUT US
          </a>
          <a className="nav-item nav-link" href="#footer">
            CONTACT US
          </a>
          {userLoggedIn ? (
            <div className="user-profile-container">
              <a className="nav-item nav-link" onClick={() => nav("/login")}>
                Profile
              </a>
              <a className="nav-item nav-link" onClick={() => nav("/signup")}>
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
