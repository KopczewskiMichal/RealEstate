import React, { useEffect, useState } from 'react';
import { getToken, decodeToken, removeToken } from './../../auth';
import { useNavigate, useParams } from 'react-router-dom'; // Dodane użycie useParams
import axios from '../../axiosConfig';

export default function UserProfilePage() {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();
  const params = useParams(); // Zainicjowanie używania parametrów

  const [offers, setOffers] = useState([]);

  const getOffers = () => {
    axios.get(`${process.env.REACT_APP_API_URL}/secured`).then((res) => {
      // setOffers(res.data);
      console.log(res.data);
    }).catch((err) => {
      console.error(err.getMessage);
      // console.log("Wykonano próbę pobrania danych z api, sprawdź czy serwer i baza stoją.")
    }).finally(() => {
    });
  };

  const handleLogin = () => {
    const token = getToken();
    if (token) {
      const userData = decodeToken();
      if (userData) {
        setUser(userData);
      } else {
        navigate('/');
      }
    } else if (params.token) { // Sprawdzenie, czy istnieje parametr 'token' w URL
      const tokenValue = params.token;
      document.cookie = `token=${tokenValue}; path=/; max-age=86400`; // Ustawienie ciasteczka na 24h
      navigate('/profile');
    } else {
      navigate('/login');
    }
  };

  useEffect(() => {
    handleLogin();
    getOffers();
  }, []);

  const handleLogout = () => {
    removeToken();
    navigate('/login');
  };

  if (!user) return null;

  return (
    <div>
      <h1>User Profile</h1>
      <p>Name: {user.name}</p>
      <p>Email: {user.email}</p>
      <button onClick={handleLogout}>Log Out</button>
    </div>
  );
}
