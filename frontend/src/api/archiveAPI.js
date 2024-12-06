import axios from 'axios';

export const archiveAPI = axios.create({
    baseURL: 'http://localhost:8080/admin',
});

export const deleteFlight = async (flightId) => {
    try {
        await archiveAPI.delete(`/flights/${flightId}`);
    } catch (error) {
        console.error('Error deleting flight:', error);
        throw error;
    }
};
