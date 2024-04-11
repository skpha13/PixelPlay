<script setup lang="ts">
const props = defineProps<{
  title: string
}>()
</script>

<template>
  <div class="flex flex-row justify-center items-center">
    <h1 class="text-7xl sm:text-8xl lg:text-9xl font-extrabold
               bg-gradient-to-br from-indigo-500 via-purple-500 to-pink-500
  <!--             bg-gradient-to-br from-blue-900 from-40% to-sky-400-->
               inline-block p-6 bg-clip-text" style="color: rgba(235, 235, 235, 0.2)">{{ props.title }}</h1>
  </div>
</template>