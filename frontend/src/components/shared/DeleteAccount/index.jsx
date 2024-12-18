import React from "react";
import Button from "../Button";
import { deleteAccountAPI } from "./api";
import useUserAuthenticationStore from "../../../store/useUserAuthenticationStore";
import "./style.css";
import { useNavigate } from "react-router-dom";

const DeleteAccount = () => {
  const { id, setUserAuthentication } = useUserAuthenticationStore();
  const nav = useNavigate();
  const handleSubmit = async (e) => {
    e.preventDefault();
    const isSuccess = await deleteAccountAPI(id);
    if (isSuccess) {
      setUserAuthentication(null, "USER");
      nav("/");
    }
  };
  return (
    <form onSubmit={handleSubmit} className="delete-account-container">
      <h1 className="delete-account-header">Delete Account</h1>
      <p className="delete-account-description">
        Are you sure you want to delete your account?
      </p>
      <Button btnText={"Delete Account"} btnColor="dark" type="submit" />
    </form>
  );
};

export default DeleteAccount;
