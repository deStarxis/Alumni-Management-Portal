import { Navigate, Route } from "react-router";
import { isLoggedIn } from "./auth";

const getRoutes = (allRoutes) =>
allRoutes.map((route) => {
  if (route.collapse) {
    return getRoutes(route.collapse);
  }

  if (route.route) {
    if (route.private) {
      return <Route
        key={route.key}
        exact
        path={route.route}
        element={
          <RequireAuth>
            {route.component}
          </RequireAuth>
        }
      />
    }
    return <Route
      key={route.key}
      exact
      path={route.route}
      element={
        <NoAuth>
          {route.component}
        </NoAuth>
      }
    />
  }

  return null;
});

function RequireAuth({ children }) {
let loggedIn = isLoggedIn();

return loggedIn === true ? (
  children
) : (
  <Navigate to="/login" />
);
}

function NoAuth({ children }) {
let loggedIn = isLoggedIn();

return loggedIn !== true ? (
  children
) : (
  <Navigate to="/" />
);
}


export default getRoutes;