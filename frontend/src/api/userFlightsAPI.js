import axios from "axios";

export const userFlightsApi = axios.create({
    baseURL: "http://localhost:8080/user",
});

export const fetchUserFlights = async (filters, pageNumber = 0) => {
    console.log(filters)
    try {
        const response = await userFlightsApi.post("/reservedFlights", filters, {
            params: { pageNumber }, 
        });

        return response.data;
    } catch (error) {
        console.error("error fetching flights:", error);
        throw error; 
    }
};
