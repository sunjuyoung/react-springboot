import React from "react";
import ListingCard from "../components/listing/listingCard";
import { useQueries, useQuery } from "@tanstack/react-query";
import { useSelector } from "react-redux";
import newRequest, { getAllListing } from "../utils/newRequest";

const home = () => {
  const user = useSelector((state) => state?.user);
  const token = useSelector((state) => state?.token);

  const {
    isLoading,
    isError,
    data: listings,
    error,
  } = useQuery(["listings"], async () => await getAllListing(token));

  if (isError) {
    return <span>Error: {error.message}</span>;
  }
  if (isLoading) {
    return <span>Loading...</span>;
  }
  return (
    <div
      className="max-w-[2520px] mx-auto
xl:px-20 md:px-10 px-4 sm:px-2"
    >
      <div className="grid grid-cols-1 gap-8 pt-24 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 2xl:grid-cols-6">
        {listings.map((listing) => {
          return (
            <ListingCard
              key={listing.listing_id}
              data={listing}
              currentUser={user}
              buttonLabel={"보기"}
            />
          );
        })}
      </div>
    </div>
  );
};

export default home;
