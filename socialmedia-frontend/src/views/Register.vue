<template>
  <div class="register-form">
    <h2>Register</h2>
    <form @submit.prevent="register">
      <input v-model="form.user_name" placeholder="Name" required />
      <input v-model="form.email" type="email" placeholder="Email" required />
      <input v-model="form.password_hash" type="password" placeholder="Password" required />
      <input v-model="form.phoneNumber" placeholder="Phone Number" required />
      <input v-model="form.cover_image" placeholder="Cover Image" />
      <input v-model="form.biography" placeholder="Biography" />
      <button type="submit">Register</button>
    </form>

    <p v-if="success" style="color: green;">✅ Registered successfully!</p>
    <p v-if="error" style="color: red;">❌ {{ error }}</p>

    <!-- ✅ Back Button -->
    <button @click="$emit('changeView', 'Home')">⬅ Back</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

const form = ref({
  user_name: '',
  email: '',
  password_hash: '',
  phoneNumber: '',
  cover_image: 'default.jpg',
  biography: ''
})

const success = ref(false)
const error = ref('')

const register = async () => {
  console.log("📤 Submitting form:", JSON.stringify(form.value, null, 2))
  success.value = false
  error.value = ''

  try {
    const res = await axios.post('http://localhost:8081/api/users/register', form.value)
    console.log('✅ Response:', res.data)
    success.value = true
  } catch (err) {
    console.error('❌ Error:', err)
    error.value = err.response?.data?.message 
  }
}
</script>

<style scoped>
.register-form {
  max-width: 400px;
  margin: auto;
  display: flex;
  flex-direction: column;
  gap: 10px;
}
</style>
