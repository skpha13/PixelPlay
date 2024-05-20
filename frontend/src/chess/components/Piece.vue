<script setup lang="ts">
  import {computed} from "vue";
  import {getImagePath, PieceModel} from "@/chess/models/PieceModel";

  const props = defineProps<{
    model: PieceModel,
    size: number
  }>();

  const imgSrc = computed(() => getImagePath(props.model.type))
  console.log(imgSrc)
  const height = computed(() => props.size)
  const width = computed(() => props.size)
  const translateX = computed(() => props.size * props.model.file + 'px')
  const translateY = computed(() => props.size * props.model.rank+ 'px')
</script>

<template>
  <div class="piece aspect-square flex items-center" :style="{transform: `translate(${translateX}, ${translateY})` }">
    <img :src="imgSrc" alt="" :width="width" :height="height" />
  </div>
</template>

<style scoped>

  .piece {
    position: absolute;
  }
</style>