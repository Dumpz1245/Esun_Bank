module.exports = {
  globals: {
    defineEmits: 'readonly',
    defineProps: 'readonly',
    defineExpose: 'readonly',
    defineSlots: 'readonly',
  },
  root: true,
  env: {
    node: true,
  },
  parserOptions: {
    requireConfigFile: false,
  },
  extends: [
    'plugin:vue/vue3-essential',
    'eslint:recommended',
  ],
  rules: {
    // Add custom rules here if needed
    'vue/multi-word-component-names': 'off'
  },
}
