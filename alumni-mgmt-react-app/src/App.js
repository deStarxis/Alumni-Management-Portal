import "./App.css";
import { Navigate, Route, Routes } from "react-router-dom";
import Header from "./common/Header";
import getRoutes from "./helpers/routesManager";
import routes from "./helpers/routes";
import { isLoggedIn } from "./helpers/auth";
import { httpInterceptor } from "./helpers/httpInterceptor";

function App() {
  httpInterceptor();
  let loggedIn = isLoggedIn();
  let defaultRoute = loggedIn ? "/" : "/login";
  let myRoutes = [...routes.privateRoutes, ...routes.publicRoutes];
  return (
    <div>
      <Header />
      <Routes>
        {getRoutes(myRoutes)}
        <Route path="*" element={<Navigate to={defaultRoute} />} />
      </Routes>
    </div>
  );
}

export default App;
