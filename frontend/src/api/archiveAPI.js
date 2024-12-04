import axios from "axios";

const archiveAPI = axios.create({
    baseURL: "http://localhost:8080/admin",
});

export const fetchArchivedFlights = async (pageNumber = 0) => {
    try {
        console.log(`Fetching archived flights for page ${pageNumber}...`);
        const response = await archiveAPI.get(`/archive`, {
            params: { pageNumber },
        });
        console.log("Archived flights data received:", response.data);
        return response.data;
    } catch (error) {
        console.error("Error fetching archived flights:", error.response?.data || error.message);
        throw error;
    }
};


export const filterArchivedFlights = async (filters, pageNumber = 0) => {
    try {
        const response = await archiveAPI.post(`/archive`, filters, {
            params: { pageNumber },
        });
        return response.data;
    } catch (error) {
        console.error("Error filtering archived flights:", error);
        throw error;
    }
};
