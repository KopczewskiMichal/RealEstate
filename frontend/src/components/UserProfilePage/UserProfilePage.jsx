import React, { useEffect, useState } from 'react';
import { getToken, decodeToken, removeToken } from './../../auth';
import { useNavigate } from 'react-router-dom';

export default function UserProfilePage () {
  const [user, setUser] = useState(null);
  const history = useNavigate();

  useEffect(() => {
    const token = getToken();
    if (token) {
      const userData = decodeToken();
      if (userData) {
        setUser(userData);
      } else {
        history.push('/');
      }
    } else {
      history.push('/');
    }
  }, [history]);

  const handleLogout = () => {
    removeToken();
    history.push('/');
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

