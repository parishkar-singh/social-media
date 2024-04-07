import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse, AxiosError } from 'axios';


const highClient: AxiosInstance = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 10000,
    headers: {
        'Content-Type': 'application/json',
    },
    auth: {
        username: "admin",
        password: "admin"
    }
});

export default highClient;
