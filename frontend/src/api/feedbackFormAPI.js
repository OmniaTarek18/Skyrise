import axios from "axios";

const API_BASE_URL = "http://localhost:8080";

// fetch username
export const fetchUserName = async (userId) => {
    try {
        const response = await axios.get(`${API_BASE_URL}/user/${userId}/name`);
        return response.data; // { firstName, lastName }
    } catch (error) {
        console.error(error);
        throw error;
    }
};

// fetch most recent reservation
export const fetchMostRecentReservation = async (userId) => {
    try {
        const response = await axios.get(
            `${API_BASE_URL}/user/recentReservation/${userId}`
        );
        return response.data; 
    } catch (error) {
        console.error(error);
        throw error;
    }
};

// updating dismiss count
export const updateDismissCount = async (userId, flightId, newDismissCount) => {
    try {
        await axios.put(
            `${API_BASE_URL}/user/updateDismissCount/${userId}/${flightId}`,
            null,
            { params: { newDismissCount } }
        );
    } catch (error) {
        console.error(error);
        throw error;
    }
};

// submitting feedback
export const submitFeedback = async (feedbackDTO) => {
    try {
        await axios.post(`${API_BASE_URL}/add/feedback`, feedbackDTO);
    } catch (error) {
        console.error(error);
        throw error;
    }
};
