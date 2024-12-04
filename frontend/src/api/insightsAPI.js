import axios from "axios";

export const insightsights = axios.create({
    baseURL: 'http://localhost:8080/admin',
});

export const fetchInsights = async () => {
    try {
        const response = await insightsights.get("/insights");
        console.log(response);
        return response.data;
    } catch (error) {
        console.error("Error fetching insights:", error);
        throw error; 
    }
};
