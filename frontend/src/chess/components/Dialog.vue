<script setup lang="ts">
  import GlassButon from "@/components/GlassButon.vue";

  const props = defineProps({
    text: String,
    link: {type: String, required: true},
  })
</script>

<template>
  <div class="z-50 fixed top-0 left-0 w-full h-full flex items-center justify-center">
    <div class="bg-white/10 backdrop-blur rounded-lg text-center
                border border-neutral-100 drop-shadow-sm
                px-4 pt-8 w-48 h-1/4 md:w-[500px]
                flex flex-col items-center justify-center" >
      <h2 class="flex-1 font-extrabold text-5xl text-white drop-shadow-lg">{{ props.text }}</h2>
      <div class="bg-white/20 hover:bg-white/30 border rounded-lg p-4 px-6 w-full">
        <RouterLink :to="props.link"
                    class="font-bold text-4xl"> Back </RouterLink>
      </div>
    </div>

  </div>
</template>

<style scoped>

</style>