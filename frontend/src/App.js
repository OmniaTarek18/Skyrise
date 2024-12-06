import LogInPage from "./components/login/LogInPage";
import "./App.css";
import SignUpPage from "./components/signup/SignUpPage";
<<<<<<< HEAD
import { Routes, Route } from "react-router-dom";
=======
import { Routes, Route, useNavigate } from "react-router-dom";
>>>>>>> SCRUM-65-Reset-Password
import ChangePassword from "./components/userdashboard/ChangePassword";
import ForgetPassword from "./components/login/ForgetPassword";
import ResetPassword from "./components/shared/ResetPassword";
import Homepage from "./components/homepage/Homepage";
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
<<<<<<< HEAD
  return (
    <Routes className="App">
      {/* <NavBar heading="Navigation Menu" items={items}></NavBar>
      <Button btnText={"Submit"} handleClick={onClickButton}></Button> */}
      {/* <LogInPage></LogInPage> */}
      {/* <SignUpPage></SignUpPage> */}
      {/* <Test/> */}
      <Route path="/" element={<Homepage />} />
=======
  const nav = useNavigate()
  return (
    <Routes className="App">
      <Route path="/" element={<LogInPage />} />
      <Route path="home" element={<Homepage />} />
>>>>>>> SCRUM-65-Reset-Password
      <Route path="signup" element={<SignUpPage />} />
      <Route path="login" element={<LogInPage />} />
      <Route path="change-password" element={<ChangePassword />}/>
      <Route path="forget-password" element={<ForgetPassword />} />
      <Route path="reset-password" element={<ResetPassword />} />
    </Routes>
  );
}

export default App;
