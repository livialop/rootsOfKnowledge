import axios from 'axios';

const api = axios.create({
    baseURL: import.meta.env?.VITE_API_BASE_URL || 'http://127.0.0.1:8080'
})

export const getLivros = async () => {
    const response = await api.get('/livros');
    return response.data;
}

export const getLivro = async (id) => {
    const response = await api.get(`/livro/${id}`);
    return response.data;
}

export const deleteLivro = async (id) => {
    const response = await api.delete(`/livro/${id}`);
    return response.data;
}