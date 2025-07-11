import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

const app = createApp(App)
app.use(router) // ✅ THIS IS ESSENTIAL
app.mount('#app')
