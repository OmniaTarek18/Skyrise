import React from "react";
import Button from "../Button";
import "./style.css"

const CallToAction = ({action, actionText = false, handleClick}) => {
  return (
    <>
      <div className="help">
        {actionText && <p className="helper-text">{actionText}</p>}
        <Button btnText={action} btnColor="link" handleClick={handleClick}/>
      </div>
    </>
  );
};

export default CallToAction;
