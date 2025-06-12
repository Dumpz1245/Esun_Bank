<template>
  <div>
    <h2 style="font-size: 64px;">🏠 Welcome to Social Media</h2>
    <button class="main-button" @click="$emit('changeView', 'Login')">Login</button>

    <button @click="$emit('changeView', 'Register')">Register</button>
  </div>
</template>

<script setup></script>
