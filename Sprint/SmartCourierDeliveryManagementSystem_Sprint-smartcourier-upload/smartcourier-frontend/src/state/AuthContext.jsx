import { createContext, useContext, useEffect, useState } from "react";
import { apiFetch, clearToken, getToken, setToken } from "../api";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [auth, setAuth] = useState({ token: getToken(), user: null, loading: true });

  useEffect(() => {
    async function loadUser() {
      if (!getToken()) {
        setAuth({ token: null, user: null, loading: false });
        return;
      }
      try {
        const user = await apiFetch("/auth/validate");
        setAuth({ token: getToken(), user, loading: false });
      } catch {
        clearToken();
        setAuth({ token: null, user: null, loading: false });
      }
    }
    loadUser();
  }, []);

  async function login(payload) {
    const result = await apiFetch("/auth/login", {
      method: "POST",
      body: JSON.stringify(payload)
    });
    setToken(result.token);
    const user = await apiFetch("/auth/validate", {
      headers: { Authorization: `Bearer ${result.token}` }
    });
    setAuth({ token: result.token, user, loading: false });
    return user;
  }

  async function signup(payload) {
    return apiFetch("/auth/signup", {
      method: "POST",
      body: JSON.stringify(payload)
    });
  }

  function logout() {
    clearToken();
    setAuth({ token: null, user: null, loading: false });
  }

  return (
    <AuthContext.Provider value={{ ...auth, login, signup, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}
