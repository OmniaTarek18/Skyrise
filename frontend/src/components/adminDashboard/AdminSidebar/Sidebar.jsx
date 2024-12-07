import React from "react";
import { NavLink } from "react-router-dom";
import "./sidebar.css";
import {
  UilDashboard,
  UilPlane,
  UilCommentAlt,
  UilArchive,
  UilUser,
  UilKeySkeleton,
  UilSignOutAlt,
  UilTrashAlt,
} from "@iconscout/react-unicons";

export default function Sidebar() {
  return (
    <div className="mainSidebar">
      <div>
        <h4 className="appName">SKY RISE</h4>
        <hr className="separator" />
        <ul className="ulContainer">
          <li className="liContainer">
            <NavLink
              to="/"
              className={({ isActive }) => (isActive ? "link active" : "link")}
            >
              <UilDashboard className="sidebar-icon" />
              <p>Overview</p>
            </NavLink>
          </li>
          <li className="liContainer">
            <NavLink
              to="/flights"
              className={({ isActive }) => (isActive ? "link active" : "link")}
            >
              <UilPlane className="sidebar-icon" />
              <p>FLIGHTS</p>
            </NavLink>
          </li>
          <li className="liContainer">
            <NavLink
              to="/feedback"
              className={({ isActive }) => (isActive ? "link active" : "link")}
            >
              <UilCommentAlt className="sidebar-icon" />
              <p>FEEDBACK</p>
            </NavLink>
          </li>
          <li className="liContainer">
            <NavLink
              to="/archive"
              className={({ isActive }) => (isActive ? "link active" : "link")}
            >
              <UilArchive className="sidebar-icon" />
              <p>ARCHIVE</p>
            </NavLink>
          </li>
          <li className="liContainer">
            <NavLink
              to="/upgrade-user"
              className={({ isActive }) => (isActive ? "link active" : "link")}
            >
              <UilUser className="sidebar-icon" />
              <p>UPGRADE USER</p>
            </NavLink>
          </li>
          <li className="liContainer">
            <NavLink
              to="/change-password"
              className={({ isActive }) => (isActive ? "link active" : "link")}
            >
              <UilKeySkeleton className="sidebar-icon" />
              <p>CHANGE PASSWORD</p>
            </NavLink>
          </li>
          <li className="liContainer">
            <NavLink
              to="/logout"
              className={({ isActive }) => (isActive ? "link active" : "link")}
            >
              <UilSignOutAlt className="sidebar-icon" />
              <p>LOGOUT</p>
            </NavLink>
          </li>
        </ul>
      </div>
      <div className="deleteSection">
        <hr className="separator" />
        <li className="liContainer">
          <NavLink
            to="/delete-account"
            className={({ isActive }) => (isActive ? "link active" : "link")}
          >
            <UilTrashAlt className="sidebar-icon" />
            <p>DELETE ACCOUNT</p>
          </NavLink>
        </li>
      </div>
    </div>
  );
}
