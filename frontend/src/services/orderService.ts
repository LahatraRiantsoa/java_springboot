import axios from 'axios'

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080'

export const orderService = {
  // Creer une commande
  async createOrder(productId: number, productName: string, quantite: number, prixUnitaire: number) {
    const response = await axios.post(`${API_URL}/api/orders`, {
      productId,
      productName,
      quantite,
      prixUnitaire,
    })
    return response.data
  },

  // Mes commandes
  async getMyOrders() {
    const response = await axios.get(`${API_URL}/api/orders/my-orders`)
    return response.data
  },
}
