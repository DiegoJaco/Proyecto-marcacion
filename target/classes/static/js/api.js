
// Funciones de acceso a API con manejo de token y errores.
import { API_BASE, API } from './config.js';

function authHeaders() {
  const token = localStorage.getItem('token');
  return token ? { 'Authorization': `Bearer ${token}` } : {};
}

async function http(method, path, body=null, extraHeaders={}) {
  const opts = {
    method,
    headers: {
      'Accept': 'application/json',
      ...(body ? {'Content-Type': 'application/json'} : {}),
      ...authHeaders(),
      ...extraHeaders
    },
    ...(body ? { body: JSON.stringify(body) } : {})
  };
  const res = await fetch(API_BASE + path, opts);
  const contentType = res.headers.get('content-type') || '';
  const isJSON = contentType.includes('application/json');
  if (!res.ok) {
    let detail = isJSON ? await res.json().catch(() => ({})) : await res.text();
    const err = new Error(`HTTP ${res.status}`);
    err.status = res.status;
    err.detail = detail;
    throw err;
  }
  return isJSON ? res.json() : res.text();
}

export const api = {
  // Auth
  login: (username, password) => http('POST', API.login, { username, password }),
  me:    () => http('GET', API.me),

  // Turnos
  listTurnos: (page=0, size=10) => http('GET', `${API.turnos}?page=${page}&size=${size}`),
  createTurno: (payload) => http('POST', API.turnos, payload),
  updateTurno: (id, payload) => http('PUT', `${API.turnos}/${id}`, payload),
  deleteTurno: (id) => http('DELETE', `${API.turnos}/${id}`),

  // Marcaciones
  listMarcaciones: (page=0, size=10) => http('GET', `${API.marcaciones}?page=${page}&size=${size}`),
  createMarcacion: (payload) => http('POST', API.marcaciones, payload),
  updateMarcacion: (id, payload) => http('PUT', `${API.marcaciones}/${id}`, payload),
  deleteMarcacion: (id) => http('DELETE', `${API.marcaciones}/${id}`),
};
