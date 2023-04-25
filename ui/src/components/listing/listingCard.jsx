import { useCallback, useMemo } from "react";
import { format } from "date-fns";

import Button from "../Button";
import { Navigate, useNavigate } from "react-router-dom";

const ListingCard = ({ data, currentUser, reservation = [] }) => {
  const navigate = useNavigate();

  // const handleCancel = useCallback(
  //   (e) => {
  //     e.stopPropagation();

  //     if (disabled) {
  //       return;
  //     }

  //     onAction?.(actionId);
  //   },
  //   [disabled, onAction, actionId]
  // );

  // const price = useMemo(() => {
  //   if (reservation) {
  //     return reservation.totalPrice;
  //   }

  //   return data.price;
  // }, [reservation, data.price]);

  // const reservationDate = useMemo(() => {
  //   if (!reservation) {
  //     return null;
  //   }

  //   const start = new Date(reservation.startDate);
  //   const end = new Date(reservation.endDate);

  //   return `${format(start, "PP")} - ${format(end, "PP")}`;
  // }, [reservation]);

  return (
    <div
      onClick={() => navigate(`/listing/${data.listing_id}`)}
      className="col-span-1 cursor-pointer group"
    >
      <div className="flex flex-col w-full gap-2">
        <div className="relative w-full overflow-hidden aspect-square rounded-xl">
          <img
            className="object-cover w-full h-full transition group-hover:scale-110"
            src={`images/listing/${data.image_src}`}
            alt="Listing"
          />
          {/* <div className="absolute top-3 right-3">
            <HeartButton listingId={data.listing_id} currentUser={currentUser} />
          </div> */}
        </div>
        <div className="text-lg font-semibold">{data.location}</div>
        <div className="font-light text-neutral-500">{data.categories}</div>
        <div className="flex flex-row items-center gap-1">
          <div className="font-semibold">$ {data.price}</div>
          <div className="font-light">night</div>
        </div>

        <Button label="보기" />
      </div>
    </div>
  );
};

export default ListingCard;
