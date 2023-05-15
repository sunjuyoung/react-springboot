import React, { useCallback, useEffect, useRef } from "react";
import qs from "query-string";
import ListingCard from "../components/listing/listingCard";
import {
  useInfiniteQuery,
  useQuery,
  useQueryClient,
} from "@tanstack/react-query";
import { useSelector } from "react-redux";
import newRequest, { getAllListing } from "../utils/newRequest";
import { useParams, useSearchParams } from "react-router-dom";
import Categories from "../components/navbar/Categories";
import EmptyState from "../components/EmptyState";
import { useInView } from "react-intersection-observer";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";

const home = () => {
  const user = useSelector((state) => state?.user);
  const token = useSelector((state) => state?.token);

  const { ref, inView } = useInView();
  const [searchParams, setSearchParams] = useSearchParams();
  let locationValue = searchParams.get("locationValue");
  let category = searchParams.get("category");
  let startDate = searchParams.get("startDate");
  const queryClient = useQueryClient();
  const currentParams = Object.fromEntries([...searchParams]);

  const {
    status,
    data: listings,
    error,
    isFetching,
    isFetchingNextPage,
    isFetchingPreviousPage,
    fetchNextPage,
    fetchPreviousPage,
    hasNextPage,
    hasPreviousPage,
  } = useInfiniteQuery(
    ["listings"],
    async ({ pageParam = 0 }) => await getAllListing(token, "", pageParam),

    {
      getPreviousPageParam: (firstPage) =>
        !firstPage.first && lastPage.number > 1
          ? lastPage.number - 1
          : null ?? undefined,
      getNextPageParam: (lastPage) =>
        !lastPage.last && lastPage.number < lastPage.totalPages
          ? lastPage.number + 1
          : null ?? undefined,
      //enabled: !locationValue && !category && !startDate,
    }
  );

  useEffect(() => {
    if (inView) {
      fetchNextPage();
    }
  }, [inView]);

  // useEffect(() => {
  //   if (locationValue || category || startDate) {
  //     const url = qs.stringifyUrl(
  //       {
  //         url: "",
  //         query: currentParams,
  //       },
  //       { skipNull: true }
  //     );

  //     queryClient.prefetchQuery({
  //       queryKey: ["listings"],
  //       queryFn: () => getAllListing(token, url),
  //     });
  //   }
  // }, [searchParams, locationValue, category, currentParams]);

  if (status === "error") {
    return <span>Error: {error.message}</span>;
  }

  if (!listings || (listings?.length === 0 && (category || locationValue))) {
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
        {status === "loading" ? (
          <p>Loading</p>
        ) : (
          <>
            <div>
              <button
                onClick={() => fetchPreviousPage()}
                disabled={!hasPreviousPage || isFetchingPreviousPage}
              >
                {isFetchingPreviousPage
                  ? "Loading more..."
                  : hasPreviousPage
                  ? "Load Older"
                  : "Nothing more to load"}
              </button>
            </div>
            <div className="grid grid-cols-1 gap-8 pt-24 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-4 2xl:grid-cols-4">
              {listings.pages.map((listing) => {
                return listing.content.map((list) => {
                  return (
                    <ListingCard
                      key={list.listing_id}
                      data={list}
                      currentUser={user}
                      buttonLabel={"보기"}
                    />
                  );
                });
              })}
            </div>

            <div>
              <button
                ref={ref}
                onClick={() => fetchNextPage()}
                disabled={!hasNextPage || isFetchingNextPage}
              >
                {isFetchingNextPage
                  ? "Loading more..."
                  : hasNextPage
                  ? "Load Newer"
                  : "Nothing more to load"}
              </button>
            </div>
            <div>
              {isFetching && !isFetchingNextPage
                ? "Background Updating..."
                : null}
            </div>
          </>
        )}
      </div>
    </>
  );
};

export default home;
