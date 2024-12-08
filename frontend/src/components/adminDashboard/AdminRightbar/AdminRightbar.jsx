import { React, useEffect, useRef } from "react";
import { Routes, Route, useNavigate } from "react-router-dom";
import Overview from "../../../pages/AdminDashboard/Overview";
import Flights from "../../../pages/AdminDashboard/Flights";
import Feedback from "../../../pages/AdminDashboard/Feedback";
import ChangePassword from "../../../pages/AdminDashboard/ChangePassword";
import DeleteAccount from "../../../pages/AdminDashboard/DeleteAccount";
import ArchivePage from "../../../pages/AdminDashboard/ArchivePage";
import AdminDashboard from "../../../pages/AdminDashboard/AdminDashboard";

export default function AdminRightbar() {
  const navigate = useNavigate();
  const hasNavigated = useRef(false);
  useEffect(() => {
    if (!hasNavigated.current) {
      navigate("overview");
      hasNavigated.current = true;
    }
  }, [navigate]);

  return (
    <div className="mainRightbar">
      <Routes>
        <Route path="overview" element={<Overview />} />
        <Route path="flights" element={<Flights />} />
        <Route path="feedback" element={<Feedback />} />
        <Route path="archive" element={<ArchivePage />} />
        <Route path="change-password" element={<ChangePassword />} />
        <Route path="delete-account" element={<DeleteAccount />} />
      </Routes>
    </div>
  );
}
