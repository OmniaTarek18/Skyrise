<<<<<<< HEAD
import LogInPage from "./components/login/LogInPage";
import "./App.css";
import SignUpPage from "./components/signup/SignUpPage";
import { Routes, Route, useNavigate } from "react-router-dom";
import ChangePassword from "./components/userdashboard/ChangePassword";
import ForgetPassword from "./components/login/ForgetPassword";
import ResetPassword from "./components/shared/ResetPassword";
import Homepage from "./components/homepage/Homepage";
=======
import logo from './logo.svg';
import './App.css';
import AdminDashboard from './pages/AdminDashboard/AdminDashboard';

>>>>>>> d445baf (merge flight viewing)
function App() {
  // const items = [
  //   "Home",
  //   "Search Flights",
  //   "Reviews",
  //   "About Us",
  //   "Contact Us",
  //   "Login",
  //   "Signup",
  // ];
  // const onClickButton = (onClose) =>{return <LogIn onClose={onClose}>Alert<span> hi</span></LogIn>};
  const nav = useNavigate()
  return (
<<<<<<< HEAD
    <Routes className="App">
      <Route path="/" element={<Homepage />} />
      <Route path="signup" element={<SignUpPage />} />
      <Route path="login" element={<LogInPage />} />
      <Route path="change-password" element={<ChangePassword />}/>
      <Route path="forget-password" element={<ForgetPassword />} />
      <Route path="reset-password" element={<ResetPassword />} />
    </Routes>
=======
    <div className="App">
        <AdminDashboard/>
      </div>
>>>>>>> d445baf (merge flight viewing)
  );
}

export default App;
