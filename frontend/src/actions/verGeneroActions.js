import axios from 'axios';

const api = axios.create({
    baseURL: import.meta.env?.VITE_API_BASE_URL || 'http://127.0.0.1:8080'
})

export const getGeneros = async () => {
    const response = await api.get('/generos');
    return response.data;
}
