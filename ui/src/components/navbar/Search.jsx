import React from "react";
import useSearchModal from "../../hooks/useSearchModal";

const Search = () => {
  const searchModal = useSearchModal();
  return (
    <div
      className="
      border-[1px] 
      w-full 
      md:w-auto 
      py-2 
      rounded-full 
      shadow-sm 
      hover:shadow-md 
      transition 
      
    "
    >
      <div className="flex flex-row items-center justify-between ">
        <div
          onClick={searchModal.onOpen}
          className="px-6 text-sm font-semibold cursor-pointer"
        >
          어디든지
        </div>
        <div
          className="
          hidden 
          sm:block 
          text-sm 
          font-semibold 
          px-6 
          border-x-[1px] 
          flex-1 
          text-center
        "
        >
          언제든
        </div>
        <div className="flex flex-row items-center gap-3 pl-6 pr-2 text-sm text-gray-600 ">
          <div className="hidden cursor-pointer sm:block">search</div>
          <div className="p-2 text-white rounded-full bg-rose-500"></div>
        </div>
      </div>
    </div>
  );
};

export default Search;
