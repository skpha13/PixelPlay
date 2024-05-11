<script setup lang="ts">
import axios from 'axios'
import {ref} from "vue";
import Logo from "@/components/Logo.vue";

const hello = ref<string>('');

const fetchGreeting = async () => {
  try {
    const response = await axios.get('http://localhost:8080/greeting');
    hello.value = response.data.content;
  } catch (e) {
    hello.value = ":(";
  }
}

fetchGreeting();
</script>

<template>
  <Logo :title="hello" />
</template>