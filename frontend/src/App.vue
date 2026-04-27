<script setup lang="ts">
import NavBar from './components/NavBar.vue'
</script>

<template>
  <NavBar />
  <RouterView />
</template>

<style>
body {
  margin: 0;
  background-color: #f5f5f5;
}

#app {
  font-family: Inter, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
  -webkit-font-smoothing: antialiased;
  color: #2c3e50;
  max-width: 1280px;
  margin: 0 auto;
  padding: 0;
}
</style>
