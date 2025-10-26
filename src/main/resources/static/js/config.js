
// Centraliza endpoints del backend.
// Ajusta API_BASE si tu servidor corre en otro puerto o con contexto.
export const API_BASE = ''; // '' -> mismo host:puerto. Ej: 'http://localhost:8080'
export const API = {
  login:  '/auth/login',
  me:     '/auth/me',
  turnos: '/api/turnos',
  marcaciones: '/api/marcaciones'
};
