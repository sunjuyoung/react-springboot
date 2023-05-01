import qs from "query-string";
import { useCallback, useMemo, useState } from "react";

import Modal from "./Modal";
import CountrySelect from "../inputs/CountrySelect";
import Heading from "../Heading";
import Map from "../../components/Map";
import useSearchModal from "../../hooks/useSearchModal";
import { useNavigate, useParams } from "react-router-dom";

const SearchLocationModal = () => {
  const searchModal = useSearchModal();
  const navigate = useNavigate();

  const [location, setLocation] = useState(null);
  const params = useParams();
  console.log(location);

  const onSubmit = useCallback(async () => {
    let currentQuery = {};

    if (params) {
      currentQuery = qs.parse(params.toString());
    }

    const updatedQuery = {
      ...currentQuery,
      locationValue: location?.value,
    };

    console.log(updatedQuery);
    const url = qs.stringifyUrl(
      {
        url: "/",
        query: updatedQuery,
      },
      { skipNull: true }
    );
    console.log(url);

    searchModal.onClose();
    navigate(url);
  }, [searchModal, location, params]);

  let bodyContent = (
    <div className="flex flex-col gap-8">
      <Heading title="Location Filter" subtitle="Find the location!" />
      <CountrySelect
        value={location}
        onChange={(value) => setLocation(value)}
      />
      <hr />
      <Map center={location?.latlng} />
    </div>
  );

  return (
    <Modal
      isOpen={searchModal.isOpen}
      title="Filters"
      onSubmit={onSubmit}
      onClose={searchModal.onClose}
      body={bodyContent}
      actionLabel={"찾기"}
    />
  );
};

export default SearchLocationModal;
