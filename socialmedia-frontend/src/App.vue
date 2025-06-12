<template>
  <div>
    <component :is="views[current]" @changeView="changeView" />
  </div>
</template>

<script setup>
import { ref } from 'vue'
import Home from './views/Home.vue'
import Login from './views/Login.vue'
import Register from './views/Register.vue'
import dashboard from './views/Dashboard.vue'

const current = ref('Home')

const views = {
  Home,
  Login,
  Register,
  dashboard
}

const changeView = (view) => {
  if (views[view]) current.value = view
}
</script>
