import axios from 'axios'

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080'

// Ajoute automatiquement le token JWT a chaque requete
axios.interceptors.request.use((config) => {
  const token = localStorage.getItem('token')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

// Redirige vers /login si le backend repond 401 (token invalide ou expire)
axios.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem('token')
      localStorage.removeItem('user')
      window.location.href = '/login'
    }
    return Promise.reject(error)
  },
)

export const authService = {
  async register(username: string, email: string, password: string) {
    const response = await axios.post(`${API_URL}/api/auth/register`, {
      username,
      email,
      password,
    })
    return response.data
  },

  async login(username: string, password: string) {
    const response = await axios.post(`${API_URL}/api/auth/login`, {
      username,
      password,
    })
    // Stocke le token et les infos utilisateur
    localStorage.setItem('token', response.data.token)
    localStorage.setItem('user', JSON.stringify(response.data))
    return response.data
  },

  logout() {
    localStorage.removeItem('token')
    localStorage.removeItem('user')
  },

  isAuthenticated(): boolean {
    return !!localStorage.getItem('token')
  },

  getCurrentUser() {
    const user = localStorage.getItem('user')
    return user ? JSON.parse(user) : null
  },

  isAdmin(): boolean {
    const user = this.getCurrentUser()
    return user?.roles?.includes('ADMIN') ?? false
  },
}
