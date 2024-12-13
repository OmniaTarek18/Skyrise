import LogInPage from "./components/login/LogInPage";
import SignUpPage from "./components/signup/SignUpPage";
import { Routes, Route, useNavigate } from "react-router-dom";
import ForgetPassword from "./components/login/ForgetPassword";
import ResetPassword from "./components/shared/ResetPassword";
import Homepage from "./components/homepage/Homepage";
import UserDashboard from "./components/userdashboard/UserDashboard";
import AdminDashboard from "./pages/AdminDashboard/AdminDashboard";
import Overview from "./pages/AdminDashboard/Overview";
import Flights from "./pages/AdminDashboard/Flights";
import Feedback from "./pages/AdminDashboard/Feedback";
import ChangePassword from "./pages/AdminDashboard/ChangePassword";
import DeleteAccount from "./pages/AdminDashboard/DeleteAccount";
import ArchivePage from "./pages/AdminDashboard/ArchivePage";
import "./App.css";


function App() {
  return (
    <Routes className="App">
      <Route path="/" element={<Homepage />} />
      <Route path="signup" element={<SignUpPage />} />
      <Route path="login" element={<LogInPage />} />
      <Route path="forget-password" element={<ForgetPassword />} />
      <Route path="reset-password" element={<ResetPassword />} />
      <Route path="admin-dashboard" element={<AdminDashboard />}>
        <Route path="overview" element={<Overview />} />
        <Route path="flights" element={<Flights />} />
        <Route path="feedback" element={<Feedback />} />
        <Route path="archive" element={<ArchivePage />} />
        <Route path="change-password" element={<ChangePassword />} />
        <Route path="delete-account" element={<DeleteAccount />} />
      </Route>
      <Route path="user-dashboard" element={<UserDashboard />} />
    </Routes>
  );
}

export default App;
