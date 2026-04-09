<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { orderService } from '../services/orderService'

const commandes = ref<any[]>([])
const chargement = ref(true)
const erreur = ref('')

onMounted(async () => {
  try {
    commandes.value = await orderService.getMyOrders()
  } catch (e: any) {
    erreur.value = 'Impossible de charger vos commandes'
  } finally {
    chargement.value = false
  }
})

function formatDate(dateStr: string) {
  return new Date(dateStr).toLocaleDateString('fr-FR', {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
  })
}

function badgeStatut(statut: string) {
  const couleurs: Record<string, string> = {
    PENDING: '#f39c12',
    PROCESSING: '#3498db',
    SHIPPED: '#9b59b6',
    DELIVERED: '#27ae60',
  }
  return couleurs[statut] || '#999'
}
</script>

<template>
  <div class="page">
    <h2>Historique de mes commandes</h2>

    <router-link to="/place-order" class="btn-commander">+ Passer une commande</router-link>

    <div v-if="chargement">Chargement...</div>
    <div v-else-if="erreur" class="erreur">{{ erreur }}</div>
    <div v-else-if="commandes.length === 0" class="vide">
      Vous n'avez pas encore de commandes.
    </div>

    <div v-else class="liste">
      <div v-for="commande in commandes" :key="commande.id" class="commande">
        <div class="commande-entete">
          <span class="commande-id">Commande #{{ commande.id }}</span>
          <span class="statut" :style="{ backgroundColor: badgeStatut(commande.status) }">
            {{ commande.status }}
          </span>
        </div>
        <div class="commande-details">
          <p><strong>Produit :</strong> {{ commande.productName }}</p>
          <p><strong>Quantite :</strong> {{ commande.quantite }}</p>
          <p><strong>Total :</strong> {{ commande.totalAmount?.toFixed(2) }} €</p>
          <p><strong>Date :</strong> {{ formatDate(commande.orderDate) }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.page {
  padding: 2rem;
  max-width: 700px;
  margin: 0 auto;
}

h2 {
  color: #333;
  margin-bottom: 1rem;
}

.btn-commander {
  display: inline-block;
  background: #42b883;
  color: white;
  padding: 0.6rem 1.2rem;
  border-radius: 4px;
  text-decoration: none;
  margin-bottom: 1.5rem;
}

.liste {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.commande {
  background: white;
  border: 1px solid #eee;
  border-radius: 8px;
  padding: 1rem;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.06);
}

.commande-entete {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 0.7rem;
}

.commande-id {
  font-weight: bold;
  color: #333;
}

.statut {
  color: white;
  padding: 0.2rem 0.7rem;
  border-radius: 12px;
  font-size: 0.85rem;
}

.commande-details p {
  margin: 0.3rem 0;
  color: #555;
}

.erreur {
  color: #c00;
  background: #ffe0e0;
  padding: 0.5rem;
  border-radius: 4px;
}

.vide {
  color: #888;
  font-style: italic;
}
</style>
