import React from "react";
import { NavLink } from "react-router-dom";
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
  const menuItems = [
    { icon: Plane, label: "Flights", path: "/user-dashboard/user-flights" },
    { icon: User, label: "Profile", path: "/user-dashboard/user-info" },
    {
      icon: Lock,
      label: "Change Password",
      path: "/user-dashboard/change-password",
    },


  ];

  return (
    <aside className={`usersidebar ${isCollapsed ? "collapsed" : ""}`}>
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

        <a className="user-nav-link" onClick={() => {
          setUserAuthentication(null, "USER");
          nav("/");
        }}>
          <LogOut size={20} />
          {!isCollapsed && <span>Logout</span>}
        </a>
      </nav>

      <div className="usersidebar-footer">
        <NavLink to="/user-dashboard/delete-account" className="user-nav-link">
          <Trash size={20} />
          {!isCollapsed && <span>Delete Account</span>}
        </NavLink>
      </div>
    </aside>
  );
};

export default UserSidebar;
