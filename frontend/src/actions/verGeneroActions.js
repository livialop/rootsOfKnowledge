import axios from 'axios';

const api = axios.create({
    baseURL: import.meta.env?.VITE_API_BASE_URL || 'http://127.0.0.1:8080'
})

export const getGeneros = async () => {
    const response = await api.get('/generos');
    return response.data;
}

export const getGenero = async (id) => {
    const response = await api.get(`/genero/${id}`);
    return response.data;
}

export const editarGenero = async (id, generoData) => {
    const response = await api.put(`/genero/${id}`, generoData);
    return response.data;
}

export const deleteGenero = async (id) => {
    const response = await api.delete(`/genero/${id}`);
    return response.data;
}
