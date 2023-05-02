import React, { useEffect } from "react";
import qs from "query-string";
import ListingCard from "../components/listing/listingCard";
import { useQueries, useQuery, useQueryClient } from "@tanstack/react-query";
import { useSelector } from "react-redux";
import newRequest, { getAllListing } from "../utils/newRequest";
import { useParams, useSearchParams } from "react-router-dom";

const home = () => {
  const user = useSelector((state) => state?.user);
  const token = useSelector((state) => state?.token);

  const [searchParams, setSearchParams] = useSearchParams();
  let locationValue = searchParams.get("locationValue");

  const queryClient = useQueryClient();

  const {
    isLoading,
    isError,
    data: listings,
    error,
  } = useQuery(
    ["listings"],
    async () =>
      await getAllListing(
        token,
        locationValue?.length > 0 ? locationValue : ""
      ),
    {
      enabled: !locationValue?.length > 0,
    }
  );

  useEffect(() => {
    //const currentParams = Object.fromEntries([...searchParams]);

    if (locationValue?.length > 0) {
      queryClient.prefetchQuery({
        queryKey: ["listings"],
        queryFn: () => getAllListing(token, locationValue),
      });
    }

    // get new values on change
    // console.log("useEffect:", currentParams);
    // update the search params programmatically
    // setSearchParams({ sort: "name", order: "ascending" });
  }, [searchParams, setSearchParams, locationValue]);

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
