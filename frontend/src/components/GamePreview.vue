<script setup lang="ts">
import router from "@/router";

const props = defineProps<{
  title: string,
  imgSrc: string,
  routeName: string
}>()

const redirectToGame = () => {
  router.push({name: props.routeName});
}
</script>

<template>
  <div @click="redirectToGame"
       id="container" class="w-[300px] h-[200px] rounded-lg bg-purple-600 relative hover:cursor-pointer">
    <img :src="props.imgSrc" :alt="props.title"
         class="w-full h-full object-cover rounded-lg"  />

    <div id="overlay" class="w-full h-full bg-emerald-400 absolute bottom-0 left-0 rounded-lg"/>

    <div id="text" class="absolute bottom-0 left-0 p-4 text-lg font-bold">
      {{ props.title }}
    </div>
  </div>
</template>

<style>
#overlay {
  opacity: 0;
  transition: opacity 0.3s ease-in-out;
}

#text {
  opacity: 0;
  transition: opacity 0.3s ease-in-out;
}

#container:hover > #text {
  opacity: 1;
}

#container:hover > #overlay {
  opacity: 0.3;
}
</style>