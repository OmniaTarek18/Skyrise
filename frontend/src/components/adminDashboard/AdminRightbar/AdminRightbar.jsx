import React from "react";
import { Routes, Route } from "react-router-dom"; 
import Overview from "../../../pages/AdminDashboard/Overview";
import Flights from "../../../pages/AdminDashboard/Flights";
import Feedback from "../../../pages/AdminDashboard/Feedback";
import Archive from "../../../pages/AdminDashboard/Archive";
import ChangePassword from "../../../pages/AdminDashboard/ChangePassword";
import DeleteAccount from "../../../pages/AdminDashboard/DeleteAccount";

export default function AdminRightbar() {
  return (
    <div className="mainRightbar">
      <Routes>
        <Route path="/" element={<Overview />} />
        <Route path="flights" element={<Flights />} />
        <Route path="feedback" element={<Feedback />} />
        <Route path="archive" element={<Archive />} />
        <Route path="change-password" element={<ChangePassword />} />
        <Route path="delete-account" element={<DeleteAccount />} />
      </Routes>
    </div>
  );
}

