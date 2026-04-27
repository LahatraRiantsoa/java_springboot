<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { orderService } from '../services/orderService'

const router = useRouter()

const productId = ref<number | null>(null)
const productName = ref('')
const quantite = ref(1)
const prixUnitaire = ref<number | null>(null)
const erreur = ref('')
const succes = ref('')
const chargement = ref(false)

async function passerCommande() {
  erreur.value = ''
  succes.value = ''

  if (!productId.value || !productName.value || !prixUnitaire.value) {
    erreur.value = 'Veuillez remplir tous les champs'
    return
  }

  chargement.value = true

  try {
    await orderService.createOrder(
      productId.value,
      productName.value,
      quantite.value,
      prixUnitaire.value,
    )
    succes.value = 'Commande passee avec succes !'
    // Redirige vers l'historique apres 1.5 secondes
    setTimeout(() => router.push('/orders'), 1500)
  } catch (e: any) {
    erreur.value = e.response?.data?.erreur || 'Erreur lors de la commande'
  } finally {
    chargement.value = false
  }
}

// Calcul du total en temps reel
const total = () => {
  if (quantite.value && prixUnitaire.value) {
    return (quantite.value * prixUnitaire.value).toFixed(2)
  }
  return '0.00'
}
</script>

<template>
  <div class="page-centree">
    <div class="formulaire">
      <h2>Passer une commande</h2>

      <div v-if="erreur" class="erreur">{{ erreur }}</div>
      <div v-if="succes" class="succes">{{ succes }}</div>

      <form @submit.prevent="passerCommande">
        <div class="champ">
          <label>ID du produit</label>
          <input v-model.number="productId" type="number" placeholder="Ex: 1" min="1" required />
        </div>

        <div class="champ">
          <label>Nom du produit</label>
          <input v-model="productName" type="text" placeholder="Nom du produit" required />
        </div>

        <div class="champ">
          <label>Quantite</label>
          <input v-model.number="quantite" type="number" min="1" required />
        </div>

        <div class="champ">
          <label>Prix unitaire (€)</label>
          <input v-model.number="prixUnitaire" type="number" step="0.01" min="0.01" placeholder="Ex: 29.99" required />
        </div>

        <div class="total">
          Total : <strong>{{ total() }} €</strong>
        </div>

        <button type="submit" :disabled="chargement">
          {{ chargement ? 'Envoi...' : 'Commander' }}
        </button>
      </form>

      <p class="lien-bas">
        <router-link to="/orders">Retour a mes commandes</router-link>
      </p>
    </div>
  </div>
</template>

<style scoped>
.page-centree {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 80vh;
  padding: 2rem;
}

.formulaire {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 450px;
}

h2 {
  text-align: center;
  margin-bottom: 1.5rem;
  color: #333;
}

.champ {
  margin-bottom: 1rem;
}

.champ label {
  display: block;
  margin-bottom: 0.3rem;
  font-weight: bold;
  color: #555;
}

.champ input {
  width: 100%;
  padding: 0.6rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
  box-sizing: border-box;
}

.total {
  background: #f0f8f0;
  padding: 0.7rem;
  border-radius: 4px;
  text-align: right;
  font-size: 1.1rem;
  margin-bottom: 1rem;
  color: #333;
}

button {
  width: 100%;
  padding: 0.7rem;
  background: #42b883;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  cursor: pointer;
}

button:disabled {
  background: #a0d8c0;
}

.erreur {
  background: #ffe0e0;
  color: #c00;
  padding: 0.5rem;
  border-radius: 4px;
  margin-bottom: 1rem;
  text-align: center;
}

.succes {
  background: #e0ffe0;
  color: #080;
  padding: 0.5rem;
  border-radius: 4px;
  margin-bottom: 1rem;
  text-align: center;
}

.lien-bas {
  text-align: center;
  margin-top: 1rem;
}
</style>
