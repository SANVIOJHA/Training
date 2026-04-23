const BASE_URL = "/gateway";

export function getToken() {
  return localStorage.getItem("smartcourier_token");
}

export function setToken(token) {
  localStorage.setItem("smartcourier_token", token);
}

export function clearToken() {
  localStorage.removeItem("smartcourier_token");
}

export async function apiFetch(path, options = {}) {
  const token = getToken();
  const headers = {
    "Content-Type": "application/json",
    ...(options.headers ?? {})
  };

  if (token) {
    headers.Authorization = `Bearer ${token}`;
  }

  const response = await fetch(`${BASE_URL}${path}`, {
    ...options,
    headers
  });

  if (!response.ok) {
    const body = await response.text();
    throw new Error(body || `Request failed with ${response.status}`);
  }

  const contentType = response.headers.get("content-type") ?? "";
  return contentType.includes("application/json") ? response.json() : response.text();
}
