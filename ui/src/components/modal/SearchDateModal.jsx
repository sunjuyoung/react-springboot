import qs from "query-string";
import { useCallback, useMemo, useState } from "react";
import { Range } from "react-date-range";
import { formatISO } from "date-fns";
import { useRouter, useSearchParams } from "next/navigation";

import useSearchModal from "@/app/hooks/useSearchModal";

import Modal from "./Modal";
import Calendar from "../inputs/Calendar";
import Counter from "../inputs/Counter";
import CountrySelect, { CountrySelectValue } from "../inputs/CountrySelect";
import Heading from "../Heading";

const SearchDateModal = () => {
  const router = useRouter();
  const searchModal = useSearchModal();
  const params = useSearchParams();
  const [dateRange, setDateRange] = useState({
    startDate: new Date(),
    endDate: new Date(),
    key: "selection",
  });

  let currentQuery = {};

  if (params) {
    currentQuery = qs.parse(params.toString());
  }

  const updatedQuery = {
    ...currentQuery,
    locationValue: location?.value,
    guestCount,
    roomCount,
    bathroomCount,
  };

  if (dateRange.startDate) {
    updatedQuery.startDate = formatISO(dateRange.startDate);
  }

  if (dateRange.endDate) {
    updatedQuery.endDate = formatISO(dateRange.endDate);
  }

  const url = qs.stringifyUrl(
    {
      url: "/",
      query: updatedQuery,
    },
    { skipNull: true }
  );

  bodyContent = (
    <div className="flex flex-col gap-8">
      <Heading
        title="When do you plan to go?"
        subtitle="Make sure everyone is free!"
      />
      <Calendar
        onChange={(value) => setDateRange(value.selection)}
        value={dateRange}
      />
    </div>
  );

  return (
    <Modal
      isOpen={searchModal.isOpen}
      title="Filters"
      actionLabel={actionLabel}
      onSubmit={onSubmit}
      onClose={searchModal.onClose}
      body={bodyContent}
    />
  );
};

export default SearchDateModal;
