import React from "react";
import Avatar from "../Avatar";
import { useQuery } from "@tanstack/react-query";
import { getReviewsByListingId } from "../../utils/newRequest";
import { useSelector } from "react-redux";
import EmptyState from "../EmptyState";
import EmptyReview from "../EmptyReview";
import { useEffect } from "react";

const ListingReview = ({ listingId, currentUser }) => {
  const token = useSelector((state) => state?.token);

  const {
    isLoading,
    isError,
    data: reviews,
    error,
  } = useQuery(
    ["review"],
    async () => await getReviewsByListingId(listingId, token)
  );

  if (isError) {
    return <span>Error: {error.message}</span>;
  }
  if (isLoading) {
    return <span>Loading..</span>;
  }

  if (!reviews || reviews.length === 0) {
    return (
      <>
        <EmptyReview disabledButton={true} />
      </>
    );
  }

  console.log(reviews.length === 0);
  return (
    <>
      <div className="pb-5 mb-2">
        <div className="py-10 text-lg font-bold">
          <span>별점 후기 {reviews.length}개</span>
        </div>

        <div className="grid grid-cols-2 gap-8">
          {reviews.map((review) => (
            <div key={review.id} className="flex flex-col h-auto gap-2">
              <div className="grid gap-2 grid-cols-[50px,2fr_1fr] items-center">
                <div className="w-10">
                  <Avatar src={null} />
                </div>
                <div className="grid font-semibold">
                  호스트 : {review.reviewer}
                  <div className="font-thin">{review.updated_at}</div>
                </div>
              </div>
              <div>{review.comment}</div>
            </div>
          ))}
        </div>
      </div>

      {/* 페이지 버튼 */}
      <div className="items-center justify-center mx-auto">
        <nav aria-label="Page navigation example">
          <ul className="inline-flex -space-x-px">
            <li>
              <a
                href="#"
                className="px-3 py-2 ml-0 leading-tight text-gray-500 bg-white border border-gray-300 rounded-l-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
              >
                Previous
              </a>
            </li>
            <li>
              <a
                href="#"
                className="px-3 py-2 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
              >
                1
              </a>
            </li>

            <li>
              <a
                href="#"
                className="px-3 py-2 leading-tight text-gray-500 bg-white border border-gray-300 rounded-r-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
              >
                Next
              </a>
            </li>
          </ul>
        </nav>
      </div>
    </>
  );
};

export default React.memo(ListingReview);
