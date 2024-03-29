import { AxiosResponse, AxiosError } from 'axios';
import highClient from "@/Utils/axios";

export const getAllPublicBlogs = async () => {
    try {
        const response: AxiosResponse = await highClient.get('/api/blogs/',{withCredentials:true});
        // Handle successful response
        return response.data;
    } catch (error) {
        // Handle error
        throw error;
    }
};

// Define an async function to fetch personalized blogs by categories
export const getAllPersonalizedBlogsByCategories = async (categories: string[]) => {
    try {
        const requestBody = { categories: categories };
        const response: AxiosResponse = await highClient.post('/api/blogs/personalized', requestBody, { withCredentials: true });
        // Handle successful response
        return response.data;
    } catch (error) {
        // Handle error
        throw error;
    }
};

