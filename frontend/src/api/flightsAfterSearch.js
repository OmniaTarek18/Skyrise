import axios from 'axios';

export const feedbackapi = axios.create({
    baseURL: 'http://localhost:8080/user',
});

export const fetchFlightSearchResults = async (filters) => {
    try {
        const response = await feedbackapi.post('/search', filters, {
            params: { pageNumber: filters.pageNumber },
        });
        return response.data.content;
    } catch (error) {
        console.error('Error fetching flight search results:', error);
        throw error;
    }
};

export const fetchFlightDetails = async (flightId) => {
    try {
        const response = await feedbackapi.get(`/flights/${flightId}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching flight details:', error);
        throw error;
    }
};
