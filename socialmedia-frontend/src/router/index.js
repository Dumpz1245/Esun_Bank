import { createRouter, createWebHashHistory } from 'vue-router'
import Register from '../views/Register.vue'

const routes = [
  { path: '/', redirect: '/register' },
  { path: '/register', component: Register }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
