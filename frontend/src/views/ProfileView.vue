<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { authService } from '../services/authService'

const router = useRouter()
const user = ref<any>(null)

onMounted(() => {
  user.value = authService.getCurrentUser()
  if (!user.value) {
    router.push('/login')
  }
})

function seDeconnecter() {
  authService.logout()
  router.push('/login')
}
</script>

<template>
  <div class="page" v-if="user">
    <div class="carte">
      <h2>Mon Profil</h2>

      <div class="info">
        <div class="ligne">
          <span class="cle">Nom d'utilisateur :</span>
          <span class="valeur">{{ user.username }}</span>
        </div>
        <div class="ligne">
          <span class="cle">Roles :</span>
          <span class="valeur">{{ user.roles?.join(', ') }}</span>
        </div>
      </div>

      <div class="actions">
        <router-link to="/orders" class="btn-secondaire">Mes commandes</router-link>
        <router-link to="/place-order" class="btn-principal">Passer une commande</router-link>
        <button @click="seDeconnecter" class="btn-deconnexion">Se deconnecter</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.page {
  display: flex;
  justify-content: center;
  padding: 2rem;
}

.carte {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 500px;
}

h2 {
  color: #333;
  margin-bottom: 1.5rem;
}

.info {
  background: #f5f5f5;
  padding: 1rem;
  border-radius: 6px;
  margin-bottom: 1.5rem;
}

.ligne {
  display: flex;
  padding: 0.4rem 0;
  border-bottom: 1px solid #eee;
}

.ligne:last-child {
  border-bottom: none;
}

.cle {
  font-weight: bold;
  color: #555;
  width: 180px;
}

.valeur {
  color: #333;
}

.actions {
  display: flex;
  flex-direction: column;
  gap: 0.7rem;
}

.btn-principal,
.btn-secondaire,
.btn-deconnexion {
  display: block;
  text-align: center;
  padding: 0.7rem;
  border-radius: 4px;
  text-decoration: none;
  font-size: 1rem;
  cursor: pointer;
  border: none;
}

.btn-principal {
  background: #42b883;
  color: white;
}

.btn-secondaire {
  background: #4a90d9;
  color: white;
}

.btn-deconnexion {
  background: #e74c3c;
  color: white;
}
</style>
