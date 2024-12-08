import React from "react";
import { NavLink, useNavigate } from "react-router-dom";
import WhiteLogo from "../../../assets/whiteLogo.svg";
import "./sidebar.css";
import useUserAuthenticationStore from "../../../store/useUserAuthenticationStore";
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
import Logo from "../../shared/Logo";

export default function Sidebar() {
  const { id, role, setUserAuthentication } = useUserAuthenticationStore();
  const nav = useNavigate();

  const logout = () => {
    setUserAuthentication(null, "USER");
  };
  return (
    <div className="mainSidebar">
      <div>
        <Logo color={WhiteLogo} className="logo" />
        <hr className="separator" />
        <ul className="ulContainer">
          <li className="liContainer">
            <NavLink
              to="/admin-dashboard/overview"
              className={({ isActive }) => (isActive ? "link active" : "link")}
            >
              <UilDashboard className="sidebar-icon" />
              <p>Overview</p>
            </NavLink>
          </li>
          <li className="liContainer">
            <NavLink
              to="/admin-dashboard/flights"
              className={({ isActive }) => (isActive ? "link active" : "link")}
            >
              <UilPlane className="sidebar-icon" />
              <p>FLIGHTS</p>
            </NavLink>
          </li>
          <li className="liContainer">
            <NavLink
              to="/admin-dashboard/feedback"
              className={({ isActive }) => (isActive ? "link active" : "link")}
            >
              <UilCommentAlt className="sidebar-icon" />
              <p>FEEDBACK</p>
            </NavLink>
          </li>
          <li className="liContainer">
            <NavLink
              to="/admin-dashboard/archive"
              className={({ isActive }) => (isActive ? "link active" : "link")}
            >
              <UilArchive className="sidebar-icon" />
              <p>ARCHIVE</p>
            </NavLink>
          </li>
          <li className="liContainer">
            <NavLink
              to="/admin-dashboard/upgrade-user"
              className={({ isActive }) => (isActive ? "link active" : "link")}
            >
              <UilUser className="sidebar-icon" />
              <p>UPGRADE USER</p>
            </NavLink>
          </li>
          <li className="liContainer">
            <NavLink
              to="/admin-dashboard/change-password"
              className={({ isActive }) => (isActive ? "link active" : "link")}
            >
              <UilKeySkeleton className="sidebar-icon" />
              <p>CHANGE PASSWORD</p>
            </NavLink>
          </li>
          <li className="liContainer">
            <NavLink
              to="/"
              className={({ isActive }) => (isActive ? "link active" : "link")}
              onClick={() => {
                logout();
              }}
            >
              <UilSignOutAlt className="sidebar-icon" />
              <p>LOG OUT</p>
            </NavLink>
          </li>
        </ul>
      </div>
      <div className="deleteSection">
        <hr className="separator" />
        <li className="liContainer">
          <NavLink
            to="/admin-dashboard/delete-account"
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
