<script setup lang="ts">
  import {computed} from "vue";
  import {PieceModel} from "@/components/chess/PieceModel";

  const props = defineProps({
    model: {type:PieceModel!!, required:true},
    size: {type:Number, required:true}
  })

  const height = computed(() => props.size)
  const width = computed(() => props.size)
  const translateX = computed(() => props.size * props.model.getRank() + 'px')
  const translateY = computed(() => props.size * props.model.getFile() + 'px')
</script>

<template>
  <div class="piece aspect-square flex items-center" :style="{transform: `translate(${translateX}, ${translateY})` }">
    <img :src="props.model.getImagePath()" alt="" :width="width" :height="height" />
  </div>
</template>

<style scoped>

  .piece {
    position: absolute;
  }
</style>