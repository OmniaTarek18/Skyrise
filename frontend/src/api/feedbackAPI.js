import axios from "axios";


export const feedbackapi = axios.create({
    baseURL: 'http://localhost:8080/admin',
});

// function to get all feedbacks (no filters)
export const getAllFeedback = async (pageNumber = 0) => {
    try {
        const response = await feedbackapi.get('/feedback', {
            params: { pageNumber }, 
        });
        console.log("Fetched All Feedback: ", response);
        return response.data; 
    } catch (error) {
        console.error("Error fetching all feedback: ", error);
    }
};

// function to get feedback with filter criteria (with filters and pagination)
export const getFilteredFeedback = async (filterCriteria = { stars: null, direction: "desc", category: "" }, pageNumber = 0) => {
    try {
        const response = await feedbackapi.post('/feedback', filterCriteria, {
            params: { pageNumber }, 
        });
        console.log("Fetched Filtered Feedback: ", response);
        return response.data;  
    } catch (error) {
        console.error("Error fetching filtered feedback: ", error);
    }
};

export const getAverageRating = async () => {
    try {
        const response = await feedbackapi.get('/average-rating');
        console.log("Average Rating Response: ", response);
        return response.data; 
    } catch (error) {
        console.error("Error fetching average rating: ", error);
    }
};
