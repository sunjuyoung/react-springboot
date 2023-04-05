import React from "react";

const Avatar = ({ src }) => {
  return (
    <div>
      <img
        className="rounded-full"
        height="30"
        width="30"
        alt="Avatar"
        src={src || "/images/placeholder.jpg"}
      />
    </div>
  );
};

export default Avatar;
