<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { authService } from '../services/authService'

const router = useRouter()

const username = ref('')
const email = ref('')
const password = ref('')
const confirmation = ref('')
const erreur = ref('')
const chargement = ref(false)

async function sInscrire() {
  erreur.value = ''

  // Validation cote client
  if (password.value !== confirmation.value) {
    erreur.value = 'Les mots de passe ne correspondent pas'
    return
  }

  if (password.value.length < 6) {
    erreur.value = 'Le mot de passe doit contenir au moins 6 caracteres'
    return
  }

  chargement.value = true

  try {
    await authService.register(username.value, email.value, password.value)
    router.push('/profile')
  } catch (e: any) {
    erreur.value = e.response?.data?.erreur || 'Inscription echouee'
  } finally {
    chargement.value = false
  }
}
</script>

<template>
  <div class="page-centree">
    <div class="formulaire">
      <h2>Inscription</h2>

      <div v-if="erreur" class="erreur">{{ erreur }}</div>

      <form @submit.prevent="sInscrire">
        <div class="champ">
          <label>Nom d'utilisateur</label>
          <input v-model="username" type="text" placeholder="Choisissez un nom d'utilisateur" required />
        </div>

        <div class="champ">
          <label>Email</label>
          <input v-model="email" type="email" placeholder="votre@email.com" required />
        </div>

        <div class="champ">
          <label>Mot de passe</label>
          <input v-model="password" type="password" placeholder="Minimum 6 caracteres" required />
        </div>

        <div class="champ">
          <label>Confirmer le mot de passe</label>
          <input v-model="confirmation" type="password" placeholder="Retapez votre mot de passe" required />
        </div>

        <button type="submit" :disabled="chargement">
          {{ chargement ? 'Inscription...' : "S'inscrire" }}
        </button>
      </form>

      <p class="lien-bas">
        Deja un compte ? <router-link to="/login">Se connecter</router-link>
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
}

.formulaire {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
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

button {
  width: 100%;
  padding: 0.7rem;
  background: #42b883;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  cursor: pointer;
  margin-top: 0.5rem;
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

.lien-bas {
  text-align: center;
  margin-top: 1rem;
  color: #666;
}
</style>
