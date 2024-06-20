import Cookies from 'js-cookie';
import { jwtDecode } from 'jwt-decode';

export const setToken = (token) => {
  Cookies.set('token', token, { expires: 1 });
};

export const getToken = () => {
  return Cookies.get('token');
};

export const decodeToken = () => {
  const token = getToken();
  if (token) {
    try {
      return jwtDecode(token);
    } catch (error) {
      console.error("Invalid token");
    }
  }
  return null;
};

export const removeToken = () => {
  Cookies.remove('token');
};
