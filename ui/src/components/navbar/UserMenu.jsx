import React, { useCallback, useState } from "react";
import MenuItem from "./MenuItem";

import { AiOutlineMenu } from "react-icons/ai";
import Avatar from "../Avatar";

import { Navigate, useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { setLogout } from "../../state";
import { toast } from "react-hot-toast";

const UserMenu = ({ user }) => {
  const [isOpen, setIsOpen] = useState(false);

  const dispatch = useDispatch();

  const navigate = useNavigate();

  const toggleOpen = useCallback(() => {
    setIsOpen((value) => !value);
  }, []);

  const logoutHandle = () => {
    dispatch(setLogout());
    toast.error("Logout");
    navigate("/");
  };
  const onListingForm = useCallback(() => {
    if (!user) {
      navigate("/login");
    }
    navigate("/listingForm");
  }, []);

  return (
    <div className="relative">
      <div className="flex flex-row items-center gap-3">
        <div
          onClick={onListingForm}
          className="hidden px-4 py-3 text-sm font-semibold transition rounded-full cursor-pointer md:block hover:bg-neutral-100"
        >
          Airbnb your home
        </div>
        <div
          onClick={toggleOpen}
          className="
          p-4
          md:py-1
          md:px-2
          border-[1px] 
          border-neutral-200 
          flex 
          flex-row 
          items-center 
          gap-3 
          rounded-full 
          cursor-pointer 
          hover:shadow-md 
          transition
          "
        >
          <AiOutlineMenu />
          <div className="hidden md:block">
            <Avatar />
          </div>
        </div>
      </div>
      {isOpen && (
        <div
          className="
            absolute 
            z-10
            rounded-xl 
            shadow-md
            w-[40vw]
            md:w-3/4 
            bg-white 
            overflow-hidden 
            right-0 
            top-12 
            text-sm
          "
        >
          <div className="flex flex-col cursor-pointer">
            {user ? (
              <>
                <MenuItem label="My trips" onClick={() => {}} />
                <MenuItem label="My favorites" onClick={() => {}} />
                <MenuItem label="My reservations" onClick={() => {}} />
                <MenuItem label="My properties" onClick={() => {}} />
                <MenuItem label="Airbnb your home" onClick={() => {}} />
                <hr />
                <MenuItem label="Logout" onClick={() => logoutHandle()} />
              </>
            ) : (
              <>
                <MenuItem label="Login" onClick={() => navigate("/login")} />
                <MenuItem
                  label="Sign up"
                  onClick={() => navigate("/register")}
                />
              </>
            )}
          </div>
        </div>
      )}
    </div>
  );
};

export default UserMenu;
