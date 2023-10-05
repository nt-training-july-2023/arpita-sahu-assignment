import React from "react";

function ButtonComponent(props) {
  const { type, onClick, className, label } = props;

  return (
    <button type={type} className={className} onClick={onClick}>
      {label}
    </button>
  );
}

export default ButtonComponent;
