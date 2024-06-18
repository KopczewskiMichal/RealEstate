import { BrowserRouter, Routes, Route } from "react-router-dom";
import MainPage from './components/mainPage/MainPage.jsx';
import Login from "./components/loginPage/LoginPage.jsx";
import ProfilePage from "./components/ProfilePage.jsx/ProfilePage.jsx";

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
          path='/profile'
          element={<ProfilePage />}
          />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
