import logo from './../../logo.svg';
import './../../App.css';

import axios from "./../../axiosConfig";
import React, { useEffect, useState} from "react";



export default function MainPage () {
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

  useEffect(() => {
    console.log("Siema")
    saveTokensToCookies()
    getOffers();
  }, []);

  const setCookie = (name, value, days) => {
    const d = new Date();
    d.setTime(d.getTime() + (days * 24 * 60 * 60 * 1000));
    const expires = "expires=" + d.toUTCString();
    document.cookie = name + "=" + value + ";" + expires + ";path=/";
  };

  const extractHashParams = () => {
    const hash = window.location.hash.substring(1);
    const params = new URLSearchParams(hash);
    return {
      accessToken: params.get('access_token'),
      tokenType: params.get('token_type'),
      expiresIn: params.get('expires_in'),
      scope: params.get('scope'),
      authUser: params.get('authuser'),
      prompt: params.get('prompt')
    };
  };
  const saveTokensToCookies = () => {
    const params = extractHashParams();
    if (params.accessToken) {
      setCookie('access_token', params.accessToken, params.expiresIn / 86400); // expires_in is in seconds
    }
    if (params.tokenType) {
      setCookie('token_type', params.tokenType, params.expiresIn / 86400);
    }
    if (params.scope) {
      setCookie('scope', params.scope, params.expiresIn / 86400);
    }
    if (params.authUser) {
      setCookie('authuser', params.authUser, params.expiresIn / 86400);
    }
    if (params.prompt) {
      setCookie('prompt', params.prompt, params.expiresIn / 86400);
    }
  };
  

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <p>URL api: {process.env.REACT_APP_API_URL}</p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}