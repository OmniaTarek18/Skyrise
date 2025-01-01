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

export const cancelFlight = async (flightId) => {
    try {
        await flightsApi.put(`/flights/cancel/${flightId}`);
    } catch (error) {
        console.error('Error Cancelling flight:', error);
        throw error;
    }
};

export const updateFlight = async (body) => {
    try {
        await flightsApi.put(`/flights/update`, body);
    } catch (error) {
        console.error('Error updating flight:', error);
        throw error;
    }
};
