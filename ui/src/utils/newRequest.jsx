import axios from "axios";

const newRequest = axios.create({
  baseURL: "http://localhost:8081/api/v1",
  // withCredentials: true,
});

export const getAllListing = async (token) => {
  const response = await newRequest.get("/listing", {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

export const getListingById = async (id, token) => {
  const response = await newRequest.get(`/listing/${id}`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

export const useFavorites = async (data, token, hasFavorited) => {
  let res;
  console.log(data);

  if (hasFavorited) {
    res = await newRequest.delete(
      `/favorite/${data.listing_id}/${data.userId}`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );
  } else {
    res = await newRequest.post("/favorite", data, {
      headers: { Authorization: `Bearer ${token}` },
    });
  }

  return res.data;
};

export const addReservation = async (data, token) => {
  const response = await newRequest.post(`/reservation`, data, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

export const getReservationByUser = async (id, token) => {
  const response = await newRequest.get(`/reservation/${id}`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

export const getFavoriteListingListByUserId = async (id, token) => {
  const response = await newRequest.get(`/favorite/listingList/${id}`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

export const getListingsListByUserId = async (id, token) => {
  const response = await newRequest.get(`/listing/list/${id}`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

export default newRequest;
