import axios from 'axios';

const getCookie = (name) => {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
};

const axiosInstance = axios.create({
  baseURL: process.env.REACT_APP_API_URL, // Ustaw bazowy URL API
});

// Add a request interceptor
axiosInstance.interceptors.request.use(
  (config) => {
      const jsessionId = getCookie('JSESSIONID');
      if (jsessionId) {
          config.headers['JSESSIONID'] = jsessionId;
      }
      return config;
  },
  (error) => {
      return Promise.reject(error);
  }
);



export default axiosInstance;
