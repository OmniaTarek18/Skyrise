import axios from 'axios';

export const feedbackapi = axios.create({
    baseURL: 'http://localhost:8080/user',
});

export const fetchFlightSearchResults = async (filters) => {
    try {
        
        // Create the request body
        const requestBody = {
            arrivalAirportId: filters.arrivalAirportId,
            departureAirportId: filters.departureAirportId,
            seatClass: filters.seatClass,
            numberOfTickets: filters.numberOfTickets,
            departureDate: filters.departureDate,
            sortby: filters.sortby,
            flightType: filters.flightType,
            direction: filters.direction,
        };

        // Send the POST request with filters and pageNumber as query parameter
        const response = await feedbackapi.post('/search', requestBody, {
            params: { pageNumber: filters.pageNumber },
        });

        // Return the flight data from the response
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
