// const { useNavigate } = require("react-router-dom");

const isLoggedIn = () => {
    return !!getToken();
}

const getToken = () => {
    let token = localStorage.getItem("token")
    if (!isTokenValid(token))
        token = null;
    return token;
}

const processToken = (token) => {
    if (isTokenValid(token)) {
        localStorage.setItem("token", token);
        return true;
    } else {
        alert("Session expired. Please login again.");
        // logout();
        return false;
    }
}

const parseJwt = (token) => {
    try {
        return JSON.parse(atob(token.split('.')[1]));
    } catch (e) {
        return null;
    }
};

const isTokenValid = (token) => {
    if (!token) return false;
    let payload = parseJwt(token);
    if (payload.exp * 1000 > Date.now())
        return true;
    return false;
}

const logout = () => {
    localStorage.clear();
    // const navigate = useNavigate();
    // navigate("/login");
}

const getUser = () => {
    let payload = parseJwt(getToken());

    return { firstname: payload.firstname, lastname: payload.lastname, sub: payload.sub, userId: payload.userId, rol: payload.rol };
}

const getUserRole = () => {
    return getUser().rol[0].role;
}

module.exports = { isLoggedIn, processToken, logout, getToken, getUser, getUserRole };