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

export default newRequest;
