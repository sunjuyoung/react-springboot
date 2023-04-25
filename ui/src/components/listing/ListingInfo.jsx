import Avatar from "../Avatar";
import { useMemo } from "react";
import { categories } from "../navbar/Categories";
import React from "react";

const ListingInfo = ({
  username,
  //image,
  category,
  description,
  roomCount,
  guestCount,
  bathroomCount,
  location,
}) => {
  const cate = useMemo(() => {
    return categories.find((items) => items.label === category);
  }, [category]);

  return (
    <div className="flex flex-col col-span-4 gap-8">
      <div className="flex flex-col gap-2">
        <div className="flex flex-row items-center gap-2 text-xl font-semibold ">
          <div>Hosted by {username}</div>
          {/* <Avatar src={image?[0]} /> */}
        </div>
        <div className="flex flex-row items-center gap-4 font-light text-neutral-500">
          <div>{guestCount} guests</div>
          <div>{roomCount} rooms</div>
          <div>{bathroomCount} bathrooms</div>
        </div>
      </div>
      <hr />
      {category && (
        <>
          <div className="flex flex-col gap-6">
            <div className="flex flex-row items-center gap-4">
              <cate.icon size={40} className="text-neutral-600" />
              <div className="flex flex-col">
                <div className="text-lg font-semibold">{cate.label}</div>
                <div className="font-light text-neutral-500">
                  {cate.description}
                </div>
              </div>
            </div>
          </div>
        </>

        // <ListingCategory
        //   icon={category.icon}
        //   label={category?.label}
        //   description={category?.description}
        // />
      )}
      <hr />
      <div className="text-lg font-light text-neutral-500">{description}</div>
      <hr />
      {/* <Map center={coordinates} /> */}
    </div>
  );
};

export default ListingInfo;
