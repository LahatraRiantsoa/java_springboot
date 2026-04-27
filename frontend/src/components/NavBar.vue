<script setup lang="ts">
import { computed } from 'vue'
import { useRouter } from 'vue-router'
import { authService } from '../services/authService'

const router = useRouter()

const estConnecte = computed(() => authService.isAuthenticated())
const estAdmin = computed(() => authService.isAdmin())
const utilisateur = computed(() => authService.getCurrentUser())

function seDeconnecter() {
  authService.logout()
  router.push('/login')
}
</script>

<template>
  <nav class="navbar">
    <div class="navbar-gauche">
      <router-link to="/" class="logo">🛍 MonShop</router-link>
      <!-- Liens publics (pour Kevin - pages produits) -->
      <router-link to="/" class="lien">Accueil</router-link>
    </div>

    <div class="navbar-droite">
      <!-- Non connecte -->
      <template v-if="!estConnecte">
        <router-link to="/login" class="lien">Se connecter</router-link>
        <router-link to="/register" class="lien btn-inscription">S'inscrire</router-link>
      </template>

      <!-- Connecte -->
      <template v-else>
        <router-link to="/profile" class="lien">Mon profil</router-link>
        <router-link to="/orders" class="lien">Mes commandes</router-link>

        <!-- Lien admin (visible seulement pour les admins) -->
        <router-link v-if="estAdmin" to="/admin" class="lien lien-admin">Admin</router-link>

        <span class="bienvenue">{{ utilisateur?.username }}</span>
        <button @click="seDeconnecter" class="btn-deconnexion">Deconnexion</button>
      </template>
    </div>
  </nav>
</template>

<style scoped>
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.8rem 2rem;
  background: #2c3e50;
  color: white;
}

.logo {
  font-size: 1.3rem;
  font-weight: bold;
  color: #42b883;
  text-decoration: none;
  margin-right: 1.5rem;
}

.navbar-gauche,
.navbar-droite {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.lien {
  color: #ecf0f1;
  text-decoration: none;
  padding: 0.3rem 0.6rem;
  border-radius: 4px;
  transition: background 0.2s;
}

.lien:hover {
  background: rgba(255, 255, 255, 0.1);
}

.btn-inscription {
  background: #42b883;
  color: white;
}

.lien-admin {
  background: #e74c3c;
  color: white;
}

.bienvenue {
  color: #bdc3c7;
  font-style: italic;
}

.btn-deconnexion {
  background: transparent;
  color: #e74c3c;
  border: 1px solid #e74c3c;
  padding: 0.3rem 0.8rem;
  border-radius: 4px;
  cursor: pointer;
}

.btn-deconnexion:hover {
  background: #e74c3c;
  color: white;
}
</style>
