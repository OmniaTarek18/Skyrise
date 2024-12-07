import React from "react";
import { BrowserRouter} from "react-router-dom";
import Sidebar from "../../components/adminDashboard/AdminSidebar/Sidebar";
import AdminRightbar from "../../components/adminDashboard/AdminRightbar/AdminRightbar";
import "./admindashboard.css";


export default function AdminDashboard() {
  return (
 
      <div className="mainDashboardContainer">
        <Sidebar />
        <AdminRightbar />
      </div>

  );
}
