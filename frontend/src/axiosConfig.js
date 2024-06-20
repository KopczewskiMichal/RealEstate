import axios from 'axios';

const getCookie = (name) => {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
};

const instance = axios.create({
  baseURL: process.env.REACT_APP_API_URL,
  headers: {
    // 'Content-Type': 'application/json',
    // Dodaj tutaj inne domyślne nagłówki
  }
});

// Możesz dodać interceptor do dodawania nagłówków do każdego requesta
instance.interceptors.request.use((config) => {
  const token = getCookie('access_token'); // funkcja do pobierania tokena z ciasteczka
  if (token) {
    config.headers['Authorization'] = `Bearer ${token}`;
  }
  return config;
}, (error) => {
  return Promise.reject(error);
});

export default instance;
