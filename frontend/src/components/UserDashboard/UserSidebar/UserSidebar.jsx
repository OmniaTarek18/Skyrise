import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import ChangePassword from "../../shared/ChangePassword";
import DeleteAccount from "../../shared/DeleteAccount";
import Popup from "../../shared/Popup";
import {
  Plane,
  User,
  Lock,
  LogOut,
  Trash,
  ChevronLeft,
  ChevronRight,
} from "lucide-react";
import { useNavigate } from "react-router-dom";
import useUserAuthenticationStore from "../../../store/useUserAuthenticationStore";
const UserSidebar = ({ isCollapsed, setIsCollapsed }) => {
  const nav = useNavigate();
  const { setUserAuthentication } = useUserAuthenticationStore();
  const [showChangePasswordPopup, setShowChangePasswordPopup] = useState(false);
  const [showDeleteAccountPopup, setShowDeleteAccountPopup] = useState(false);
  const menuItems = [
    { icon: Plane, label: "Flights", path: "/user-dashboard/user-flights" },
  ];

  return (
    <aside className={`usersidebar ${isCollapsed ? "collapsed" : ""}`}>
      {showChangePasswordPopup && (
        <Popup onClose={() => setShowChangePasswordPopup(false)}>
          <ChangePassword />
        </Popup>
      )}
      {showDeleteAccountPopup && (
        <Popup onClose={() => setShowDeleteAccountPopup(false)}>
          <DeleteAccount />
        </Popup>
      )}
      <div className="usersidebar-header">
        <div className="logo-container">
          {!isCollapsed && <h2>Dashboard</h2>}
        </div>
        <button
          className="collapse-btn"
          onClick={() => setIsCollapsed(!isCollapsed)}
        >
          {isCollapsed ? <ChevronRight size={20} /> : <ChevronLeft size={20} />}
        </button>
      </div>

      <nav className="usersidebar-nav">
        {menuItems.map((item, index) => (
          <NavLink
            key={index}
            to={item.path}
            className={({ isActive }) =>
              `user-nav-link ${isActive ? "active" : ""}`
            }
          >
            <item.icon size={20} />
            {!isCollapsed && <span>{item.label}</span>}
          </NavLink>
        ))}
        <NavLink
          className="user-nav-link"
          onClick={() => setShowChangePasswordPopup(true)}
        >
          <Lock size={20} />
          {!isCollapsed && <span>Change Password</span>}
        </NavLink>
        <a
          className="user-nav-link"
          onClick={() => {
            setUserAuthentication(null, "USER");
            nav("/");
          }}
        >
          <LogOut size={20} />
          {!isCollapsed && <span>Logout</span>}
        </a>
      </nav>

      <div className="usersidebar-footer">
        <NavLink
          to="/user-dashboard/delete-account"
          className="user-nav-link"
          onClick={() => setShowDeleteAccountPopup(true)}
        >
          <Trash size={20} />
          {!isCollapsed && <span>Delete Account</span>}
        </NavLink>
      </div>
    </aside>
  );
};

export default UserSidebar;
