<template>
  <div class="login-form">
    <h2>Login</h2>
    <form @submit.prevent="login">
      <input v-model="credentials.phone_number" placeholder="Phone Number" required />
      <input v-model="credentials.password" type="password" placeholder="Password" required />
      <button type="submit">Login</button>
    </form>

    <p v-if="success" style="color: green;">✅ Logged in successfully!</p>
    <p v-if="error" style="color: red;">❌ {{ error }}</p>

    <!-- Back button (if needed) -->
    <button @click="$emit('changeView', 'Home')">⬅ Back</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
const emit = defineEmits(['changeView'])
const credentials = ref({
  phone_number: '',
  password: ''
})

const success = ref(false)
const error = ref('')

const login = async () => {
  success.value = false
  error.value = ''

  try {
    const res = await axios.post('http://localhost:8081/api/users/login', credentials.value)
    console.log("✅ Login success:", res.data)
    localStorage.setItem('user', JSON.stringify(res.data))
    localStorage.setItem('phoneNum', res.data.phoneNumber)

    success.value = true
    emit('changeView', 'dashboard')
    // optional: emit or route change
    // emit('changeView', 'Dashboard') or store token etc.

  } catch (err) {
    console.error("❌ Login failed:", err)
    error.value = err.response?.data?.message || err.message || 'Login failed.'
  }
}
</script>

<style scoped>
.login-form {
  max-width: 400px;
  margin: auto;
  display: flex;
  flex-direction: column;
  gap: 10px;
}
</style>
