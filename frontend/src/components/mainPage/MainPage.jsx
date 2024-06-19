import logo from './../../logo.svg';
import './../../App.css';

import axios from "axios";
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
    getOffers();
  }, []);

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