import React, { useEffect } from "react";
import qs from "query-string";
import ListingCard from "../components/listing/listingCard";
import { useQuery, useQueryClient } from "@tanstack/react-query";
import { useSelector } from "react-redux";
import newRequest, { getAllListing } from "../utils/newRequest";
import { useParams, useSearchParams } from "react-router-dom";
import Categories from "../components/navbar/Categories";
import EmptyState from "../components/EmptyState";

const home = () => {
  const user = useSelector((state) => state?.user);
  const token = useSelector((state) => state?.token);

  const [searchParams, setSearchParams] = useSearchParams();
  let locationValue = searchParams.get("locationValue");
  let category = searchParams.get("category");
  const queryClient = useQueryClient();
  const currentParams = Object.fromEntries([...searchParams]);

  const {
    isLoading,
    isError,
    data: listings,
    error,
  } = useQuery(["listings"], async () => await getAllListing(token, ""), {
    enabled: !locationValue && !category,
  });

  useEffect(() => {
    if (locationValue || category) {
      const url = qs.stringifyUrl(
        {
          url: "",
          query: currentParams,
        },
        { skipNull: true }
      );

      queryClient.prefetchQuery({
        queryKey: ["listings"],
        queryFn: () => getAllListing(token, url),
      });
    }
  }, [searchParams, locationValue, category, currentParams]);

  if (isError) {
    return <span>Error: {error.message}</span>;
  }
  if (isLoading) {
    return <span>Loading...</span>;
  }
  if (listings.length === 0 && (category || locationValue)) {
    return (
      <>
        <EmptyState />
      </>
    );
  }

  return (
    <>
      <Categories />
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
    </>
  );
};

export default home;
