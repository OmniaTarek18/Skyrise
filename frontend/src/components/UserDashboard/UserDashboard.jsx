import React, { useState } from "react";
import Nav from "../shared/Nav";
import useUserAuthenticationStore from "../../store/useUserAuthenticationStore";
import "./userdashboard.css";
import UserSidebar from "./UserSidebar/UserSidebar";
import DashboardContent from "./UserSidebar/DashboardContent";
const UserDashboard = () => {
  const { id, role } = useUserAuthenticationStore();
  const [isSidebarCollapsed, setIsSidebarCollapsed] = useState(false);
  
  return (
    <div className="dashboard-container">
      <Nav userLoggedIn={true} isProfile={true}/>
      <div className="dashboard-layout">
        <UserSidebar
          isCollapsed={isSidebarCollapsed}
          setIsCollapsed={setIsSidebarCollapsed}
        />
        <DashboardContent isSidebarCollapsed={isSidebarCollapsed} />
      </div>
    </div>
  );
};

export default UserDashboard;
