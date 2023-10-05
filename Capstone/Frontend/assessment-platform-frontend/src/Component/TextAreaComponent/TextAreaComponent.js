import React from "react";

function TextAreaComponent(props) {
  const { placeholder, onChange, value, error, className, label } = props;

  return (
    <div className={className}>
      <label>{label}</label>
      <textarea
        placeholder={placeholder}
        onChange={onChange}
        value={value}
      />
      {error && <p className="error-message">{error}</p>}
    </div>
  );
}

export default TextAreaComponent;
