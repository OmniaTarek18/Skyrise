import axios from "axios";

export const userFlightsApi = axios.create({
    baseURL: "http://localhost:8080/user",
});

export const fetchUserFlights = async (filterCriteria, pageNumber = 0) => {
    const response = await userFlightsApi.post("/flights", filterCriteria, {
        params: { pageNumber },
    });
    return response.data;
};
