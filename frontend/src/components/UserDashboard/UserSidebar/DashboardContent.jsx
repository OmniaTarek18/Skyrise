import { React, useEffect, useRef } from "react";
import { Routes, Route, useNavigate } from "react-router-dom";
import UserFlights from "../../../pages/UserDashboard/UserFlights";
import ChangePassword from "../../../pages/AdminDashboard/ChangePassword";
import DeleteAccount from "../../../pages/AdminDashboard/DeleteAccount";


export default function DashboardContent() {
  const navigate = useNavigate();
  const hasNavigated = useRef(false);
  useEffect(() => {
    if (!hasNavigated.current) {
      navigate("user-Flights");
      hasNavigated.current = true;
    }
  }, [navigate]);

  return (
    <div className="dashboard-content">
      <Routes>
          <Route path="user-Flights" element={<UserFlights />} />
          <Route path="change-password" element={<ChangePassword />} />
          <Route path="delete-account" element={<DeleteAccount />} />
      </Routes>
    </div>
  );
}
