import React from "react";

function InputComponent(props){
    const {label,type, placeholder, onChange, value, error, className, pattern, name} = props;

    return(
        <div className={className}>
            <label>{label}</label>
           <input 
            type = {type}
            placeholder={placeholder}
            onChange={onChange}
            value={value}
            pattern={pattern}
            name={name}
            />
            {error && <p className="error-message">{error}</p>}
        </div>
    );
}

export default InputComponent;