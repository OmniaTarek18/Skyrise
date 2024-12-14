import axios from "axios";

export const flightsApi = axios.create({
    baseURL: 'http://localhost:8080/admin',
});


export const filterFlights = async (filterCriteria, pageNumber = 0) => {
    console.log(filterCriteria)
    const response = await flightsApi.post("/flights", filterCriteria, {
        params: { pageNumber },
    });
    console.log(response.data)
    return response.data;
};
