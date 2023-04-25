import { useParams } from "react-router-dom";
import { getListingById } from "../../utils/newRequest";
import { useSelector } from "react-redux";
import { useQuery } from "@tanstack/react-query";
import Heading from "../../components/Heading";
import ListingInfo from "../../components/listing/ListingInfo";

const ListingPage = () => {
  const param = useParams();
  const token = useSelector((state) => state?.token);
  const user = useSelector((state) => state?.user);

  const listing_id = parseInt(param.id);

  const {
    isLoading,
    isError,
    data: listing,
    error,
  } = useQuery(
    ["listing"],
    async () => await getListingById(listing_id, token)
  );

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
            <img src={""} className="object-cover w-full" alt="Image" />
            {/* <div className="absolute top-5 right-5">
          <HeartButton listingId={id} currentUser={currentUser} />
        </div> */}
          </div>

          <div className="grid grid-cols-1 mt-6 md:grid-cols-7 md:gap-10">
            <ListingInfo
              username={listing.name}
              //image={null}
              category={listing.category}
              description={listing.description}
              roomCount={listing.roomCount}
              guestCount={listing.guestCount}
              bathroomCount={listing.bathroomCount}
              location={listing.location}
            />
            {/* <div className="order-first mb-10 md:order-last md:col-span-3">
              <ListingReservation
                price={listing.price}
                totalPrice={totalPrice}
                onChangeDate={(value) => setDateRange(value)}
                dateRange={dateRange}
                onSubmit={onCreateReservation}
                disabled={isLoading}
                disabledDates={disabledDates}
              />
            </div> */}
          </div>
        </div>
      </div>
    </div>
  );
};

export default ListingPage;
