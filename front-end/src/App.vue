<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
    <div class="wrapper">
      <nav>
        <RouterLink to="/">Usuarios</RouterLink>
        <RouterLink to="/cursos">Cursos</RouterLink>
      </nav>
    </div>
  </header>
  <RouterView />
</template>

<style scoped>

nav {
  width: 100%;
  font-size: 18px;
  text-align: center;
  margin-top: 2rem;
}
nav a{
  color: white;

}
nav a.router-link-exact-active {
  color: green;
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

</style>
