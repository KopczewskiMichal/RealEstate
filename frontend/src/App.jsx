import { BrowserRouter, Routes, Route } from "react-router-dom";
import MainPage from './components/mainPage/MainPage.jsx';
import Login from "./components/loginPage/LoginPage.jsx";
import Secure from "./components/loginPage/SecurePage.jsx";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route
          path='/'
          element={<MainPage />}
          />
          <Route
          path='/login'
          element={<Login />}
          />
          <Route
          path='/secure'
          element={<Secure />}
          />

        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
