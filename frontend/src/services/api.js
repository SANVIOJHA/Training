import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/gateway';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request interceptor to add JWT token
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Response interceptor:
// 1. Auto-unwrap ApiResponse envelope (response.data.data → response.data)
// 2. Handle 401 token expiration with auto-logout
api.interceptors.response.use(
  (response) => {
    // If the response has the ApiResponse envelope structure, unwrap it
    if (response.data && typeof response.data === 'object' && 'success' in response.data) {
      // Keep the full ApiResponse accessible via response.apiResponse
      response.apiResponse = response.data;
      // Unwrap: put the inner data as the top-level data for backward compatibility
      response.data = response.data.data !== undefined ? response.data.data : response.data;
    }
    return response;
  },
  (error) => {
    if (error.response && error.response.status === 401) {
      // Don't auto-logout on login/signup pages
      const path = window.location.pathname;
      if (!path.includes('/auth/login') && !path.includes('/auth/signup')) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        window.location.href = '/auth/login';
      }
    }
    return Promise.reject(error);
  }
);

export default api;
