import axios from 'axios';

export const feedbackapi = axios.create({
    baseURL: 'http://localhost:8080/',
});

export const fetchFlightSearchResults = async (filters, pageNumber) => {
    try {
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
        console.log(pageNumber, 7777777777)
        console.log(filters)
        const response = await feedbackapi.post('user/search', requestBody, {
            params: { pageNumber }, 
        });

        console.log(response,"lllllllllllllllllllllllllllll")
        return response.data;
    } catch (error) {
        console.error('Error fetching flight search results:', error);
        throw error;
    }
};


export const fetchFlightDetails = async (flightId) => {
    try {
        const response = await feedbackapi.get(`/flights/${flightId}`);
        console.log(response,"ddddddddd")
        return response.data;
    } catch (error) {
        console.error('Error fetching flight details:', error);
        throw error;
    }
};
