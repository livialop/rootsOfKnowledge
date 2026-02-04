import axios from 'axios';

const api = axios.create({
    baseURL: import.meta.env?.VITE_API_BASE_URL || 'http://127.0.0.1:8080'
})

export const createNovoGenero = async (generoData) => {
    const form = new URLSearchParams();
    Object.keys(generoData || {}).forEach(key => {
        if (generoData[key] !== undefined && generoData[key] !== null) {
            form.append(key, generoData[key]);
        }
    });
    const response = await api.post('/novogenero', form, {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    });
    return response.data;
}