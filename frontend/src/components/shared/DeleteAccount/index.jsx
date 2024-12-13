import React from "react";
import Button from "../Button"
import { deleteAccountAPI } from "./api";

const index = () => {
  return (
    <form onSubmit={deleteAccountAPI}>
      <h1>Delete Account</h1>
      <p>Are you sure you want to delete your account?</p>
      <Button btnText={"Delete Account"} btnColor="pink" type="submit"/>
    </form>
  );
};

export default index;
