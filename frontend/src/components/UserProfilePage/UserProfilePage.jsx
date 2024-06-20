import React, { useEffect, useState } from 'react';
import { getToken, decodeToken, removeToken } from './../../auth';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

export default function UserProfilePage () {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

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
        navigate.push('/');
      }
    } else {
      navigate.push('/');
    }
  }

  useEffect(() => {
    handleLogin();
    getOffers();
  }, []);

  const handleLogout = () => {
    removeToken();
    navigate.push('/');
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
};

