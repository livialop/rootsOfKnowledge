import axios from 'axios';

const api = axios.create({
    baseURL: import.meta.env?.VITE_API_BASE_URL || 'http://127.0.0.1:8080'
})

export const getAutores = async () => {
    const response = await api.get('/autores');
    return response.data;
}

export const deleteAutor = async (id) => {
    const response = await api.delete(`/autor/${id}`);
    return response.data;
}