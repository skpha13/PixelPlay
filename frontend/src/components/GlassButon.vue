<script setup lang="ts">
const props = defineProps<{
  title: string
  link: string
}>()

const emits = defineEmits<{
  (e: 'clicked') : void
}>()
</script>

<template>
  <RouterLink class="bg-white/10 backdrop-blur-sm hover:backdrop-blur-md hover:bg-white/20
                     rounded-lg px-4 py-2 min-w-14
                     border border-neutral-100 drop-shadow-sm
                     font-bold block"
              :to="link"
              @click="emits('clicked')"
  >{{ props.title }}
  </RouterLink>
</template>