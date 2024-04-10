<script setup lang="ts">
const props = defineProps<{
  title: string
  link: string
}>()
</script>

<template>
  <RouterLink class="bg-white/10 backdrop-blur-sm hover:backdrop-blur-md hover:bg-white/20
                     rounded-lg px-4 py-2 min-w-14
                     border border-neutral-100 drop-shadow-sm
                     font-bold"
              :to="link">{{ props.title }}
  </RouterLink>
</template>