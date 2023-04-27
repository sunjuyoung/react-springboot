import { useNavigate, useParams } from "react-router-dom";
import { getListingById } from "../../utils/newRequest";
import { useSelector } from "react-redux";
import { useQuery } from "@tanstack/react-query";
import Heading from "../../components/Heading";
import ListingInfo from "../../components/listing/ListingInfo";
import Button from "../../components/Button";
import { DateRange, Range, RangeKeyDict } from "react-date-range";
import "react-date-range/dist/styles.css";
import "react-date-range/dist/theme/default.css";
import { useState } from "react";
import { toast } from "react-hot-toast";
import { differenceInDays, eachDayOfInterval } from "date-fns";
import { useMemo } from "react";
import ListingReservation from "../../components/listing/ListingReservation";
import { useCallback } from "react";
import { useEffect } from "react";
import HeartButton from "../../components/HeartButton";

const ListingPage = () => {
  const initialDateRange = {
    startDate: new Date(),
    endDate: new Date(),
    key: "selection",
  };
  const [dateRange, setDateRange] = useState(initialDateRange);
  const param = useParams();
  const token = useSelector((state) => state?.token);
  const user = useSelector((state) => state?.user);
  const listing_id = parseInt(param.id);
  const [totalPrice, setTotalPrice] = useState(1000);
  const reservations = [];

  const navigate = useNavigate();
  useEffect(() => {
    if (!token) {
      navigate("/login");
    }
  }, []);
  const {
    isLoading,
    isError,
    data: listing,
    error,
  } = useQuery(
    ["listing"],
    async () => await getListingById(listing_id, token)
  );

  useEffect(() => {
    if (dateRange.startDate && dateRange.endDate) {
      const dayCount = differenceInDays(dateRange.endDate, dateRange.startDate);

      if (dayCount && listing?.price) {
        setTotalPrice(dayCount * listing?.price);
      } else {
        setTotalPrice(listing?.price);
      }
    }
  }, [dateRange, listing?.price]);

  const disabledDates = () => {
    let dates = [];

    reservations?.forEach((reservation) => {
      const range = eachDayOfInterval({
        start: new Date(reservation?.startDate),
        end: new Date(reservation?.endDate),
      });

      dates = [...dates, ...range];
    });

    return dates;
  };

  const onCreateReservation = () => {};

  if (isError) {
    return <span>Error: {error.message}</span>;
  }
  if (isLoading) {
    return <span>Loading..</span>;
  }
  console.log(dateRange);

  return (
    <div
      className="max-w-[2520px] mx-auto
xl:px-20 md:px-10 px-4 sm:px-2"
    >
      <div className="max-w-screen-lg mx-auto ">
        <div className="flex flex-col gap-6">
          <Heading title={listing.title} subtitle={`${listing.location}`} />
          <div
            className="
          w-full
          h-[60vh]
          overflow-hidden 
          rounded-xl
          relative
        "
          >
            <img
              src={`/images/listing/${listing.imageSrc[0]}`}
              className="object-cover w-full"
              alt="Image"
            />

            <div className="absolute top-5 right-5">
              <HeartButton listingId={listing_id} currentUser={user} />
            </div>
          </div>

          <div className="grid grid-cols-1 mt-6 md:grid-cols-7 md:gap-10">
            <ListingInfo
              username={listing.name}
              userImage={null}
              category={listing.category}
              description={listing.description}
              roomCount={listing.roomCount}
              guestCount={listing.guestCount}
              bathroomCount={listing.bathroomCount}
              location={listing.location}
              latlng={listing.latlng}
            />

            <div className="order-first mb-10 md:order-last md:col-span-3">
              <ListingReservation
                price={listing.price}
                totalPrice={totalPrice}
                onChangeDate={(value) =>
                  setDateRange({
                    startDate: value.startDate,
                    endDate: value.endDate,
                  })
                }
                dateRange={dateRange}
                onSubmit={onCreateReservation}
                disabled={isLoading}
                disabledDates={disabledDates}
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ListingPage;
