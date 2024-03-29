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
