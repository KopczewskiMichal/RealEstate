import { BrowserRouter, Routes, Route } from "react-router-dom";
import UserProfilePage from "./components/UserProfilePage/UserProfilePage";
import LoginPage from "./components/loginPage/LoginPage";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route
          path='/profile/:token'
          element={<UserProfilePage/>}
          />
          <Route
          path="/login"
          element={<LoginPage/>}
          />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
