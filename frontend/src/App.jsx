import { BrowserRouter, Routes, Route } from "react-router-dom";
import UserProfilePage from "./components/UserProfilePage/UserProfilePage";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route
          path='/profile'
          element={<UserProfilePage/>}
          />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
