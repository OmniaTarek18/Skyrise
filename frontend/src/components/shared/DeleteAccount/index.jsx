import React from "react";
import Button from "../Button"
import { deleteAccountAPI } from "./api";
import useUserAuthenticationStore from "../../../store/useUserAuthenticationStore";
import "./style.css"

const DeleteAccount = () => {
  const {id} = useUserAuthenticationStore();
  return (
    <form onSubmit={() => deleteAccountAPI(id)} className="delete-account-container">
      <h1 className="delete-account-header">Delete Account</h1>
      <p className="delete-account-description">Are you sure you want to delete your account?</p>
      <Button btnText={"Delete Account"} btnColor="pink" type="submit"/>
    </form>
  );
};

export default DeleteAccount;
